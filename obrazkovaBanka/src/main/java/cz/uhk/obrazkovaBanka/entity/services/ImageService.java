package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.dao.ImageDao;


@Service
@Transactional
public class ImageService {

	@Autowired
	private ImageDao imageDao;
	
	public List<Image> findImagesByName(String name) {
		return imageDao.findImageByName(name);
	}
	
	public Image findImageByHash(String hash) {
		return imageDao.findImageByHash(hash);
	}
	public Image findImageByDeleteHash(String hash) {
		return imageDao.findImageByDeleteHash(hash);		
	}

	public long countAllImages() {
        return imageDao.countImages();
    }

	public void deleteImage(Image i) {
		imageDao.remove(i);
    }

	public Image findImage(Integer id) {
         Image i = imageDao.findImage(id);
         if (i == null) {
        	 throw new RuntimeException();
         }
         return i;
    }

	public List<Image> findAllImages() {
        return imageDao.findAllImages();
    }

	public List<Image> findImageEntries(int firstResult, int maxResults) {
        return imageDao.findImageEntries(firstResult, maxResults);
    }

	public void saveImage(Image i) {
		imageDao.persist(i);
    }

	public Image updateImage(Image i) {
        return imageDao.merge(i);
    }

	public List<Image> findImagesWithTag(String tag) {
		return imageDao.findImagesWithTag(tag);
	}

}
