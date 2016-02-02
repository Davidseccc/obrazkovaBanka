package cz.uhk.obrazkovaBanka;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import cz.uhk.obrazkovaBanka.entity.Comment;
import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.User;
import cz.uhk.obrazkovaBanka.entity.services.CommentService;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;
import cz.uhk.obrazkovaBanka.entity.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Image Not Found")
@RequestMapping(value = "/comment")
public class CommentController {
	
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/add/{imageId}", method = RequestMethod.POST)
	public String rateImage(@PathVariable Integer imageId, @Validated Comment comment, Model model,HttpSession session ,BindingResult result) {
		if (result.hasErrors()){
			logger.info("Comment not inserted. Parameters are not valid");
			
		}
		Image image = imageService.findImage(imageId);
		if(image == null){
			logger.info("IMAGE NOT FOUND. Image with id " + imageId +  " not found.");
			throw new ResourceAccessException("IMAGE NOT FOUND."); 
		}
		User user = userService.findUserByNickName(session.getAttribute("loggedInUser").toString());
		
		if (!AccesController.checkPermission(session, AccesController.REGISTERED_USERS, user)){
			model.addAttribute("ERROR", "You do not have permission to do that.");
			return "error";
		}
		
		comment.setDate(new Date());
		comment.setImage(image);
		comment.setUser(user);
		commentService.saveComment(comment);
		

		return "redirect:/image/" + image.getHash();
		
	}
}
