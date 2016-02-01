package cz.uhk.obrazkovaBanka;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.User;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;
import cz.uhk.obrazkovaBanka.entity.services.UserService;

@Controller
@RequestMapping("/controller")
public class FileController {

	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	
	private LinkedList<Image> files = null;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	public static int MAX_FILE_SIZE = 2048; // File size in Kb
	//public static final String PATH = "/home/pi/images/"; // PATH TO IMAGES
	public static final String PATH = "C:/java/images/"; // PATH TO IMAGES
	public static List<String> SUPPORTED_FORMATS = new ArrayList<String>() {
		 // There are all supported formats
		private static final long serialVersionUID = 1L;
		{
			add("image/bmp");	add("image/jpeg");	add("image/png");
	}};

 
    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public @ResponseBody LinkedList<Image> upload(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    	files = new LinkedList<Image>();
 
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 

         while(itr.hasNext()){
        	 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
             
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10 || (! SUPPORTED_FORMATS.contains(mpf.getContentType()))){
                 files.pop();
                 
             }
             //2.3 create new fileMeta
                      
             String deleteHash = mpf.getOriginalFilename() + new Date() + Long.toHexString(Double.doubleToLongBits(Math.random()));
             deleteHash = MD5(deleteHash);
             String hash = MD5(new Date().toString()).concat(DigestUtils.md5DigestAsHex(mpf.getBytes()));
             
             Image image = new Image();
             image.setName(mpf.getOriginalFilename());
             image.setFileSize(mpf.getSize()/1024);
             String path = PATH + hash + "/";
             image.setPath(path + mpf.getOriginalFilename());
             image.setPublicFile(true);
             image.setHash(hash);
             image.setFileType(mpf.getContentType());
             image.setDeleteHash(deleteHash);
             image.setUploadDate(new Date());
             String nick = (String) session.getAttribute("loggedInUser");
             if(nick != null){
            	 System.out.println(nick);
            	 User user = userService.findUserByNickName(nick);
            	 image.setUser(user);
             }
             logger.info("HASH: " + hash + "\n" + "DELETE HASH: " + deleteHash );
             
             try {
                //fileMeta.setBytes(mpf.getBytes());
                Files.createDirectories(Paths.get(path));
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path +mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             image.setThumbnailPaht(path + "thumbnail."+ mpf.getOriginalFilename());
             image.resizeImage(image.getPath());
             imageService.saveImage(image);

             files.add(image);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) {
		System.out.println("hledám obrázek: " + value);
		Image image = imageService.findImageByHash(value);	
		//FileMeta getFile = files.get(Integer.parseInt(value));
		try {
			response.setContentType(image.getFileType());
			response.setHeader("Content-disposition", "attachment; filename=\"" + image.getName() + "\"");
			FileCopyUtils.copy(Files.readAllBytes(new File(image.getPath()).toPath()), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***************************************************
	 * URL: /rest/controller/getImage/{imageId} get(): get file as byte[]
	 * 
	 * @param response
	 *            : passed by the server
	 * @param imageId
	 *            : imageId from the URL
	 * @return byte[]
	 ****************************************************/
	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageId, HttpServletRequest request) {
		System.out.println("hledám obrázek: " + imageId);
		Image image = imageService.findImageByHash(imageId);
		String nrpath = image.getPath();
		Path path = Paths.get(nrpath);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	
	/***************************************************
	 * URL: /rest/controller/thumbnail/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/thumbnail/get/{value}", method = RequestMethod.GET)
	public void thumbGet(HttpServletResponse response, @PathVariable String value) {
		System.out.println("hledám obrázek: " + value);
		Image image = imageService.findImageByHash(value);	
		//FileMeta getFile = files.get(Integer.parseInt(value));
		try {
			response.setContentType(image.getFileType());
			response.setHeader("Content-disposition", "attachment; filename=\"" + image.getName() + "\"");
			FileCopyUtils.copy(Files.readAllBytes(new File(image.getThumbnailPaht()).toPath()), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***************************************************
	 * URL: /controller/thumbnail/getImage/{imageId} get(): get file as byte[]
	 * 
	 * @param response
	 *            : passed by the server
	 * @param imageId
	 *            : imageId from the URL
	 * @return byte[]
	 ****************************************************/
	@RequestMapping(value = "/thumbnail/getImage/{imageId}")
	@ResponseBody
	public byte[] getThumbImage(@PathVariable String imageId, HttpServletRequest request) {
		System.out.println("hledám obrázek: " + imageId);
		Image image = imageService.findImageByHash(imageId);
		String nrpath = image.getThumbnailPaht();
		Path path = Paths.get(nrpath);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
	
	public static void deleteRecursive(File path){
        path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    pathname.listFiles(this);
                    pathname.delete();
                } else {
                    pathname.delete();
                }
                return false;
            }
        });
        path.delete();
    }
}
