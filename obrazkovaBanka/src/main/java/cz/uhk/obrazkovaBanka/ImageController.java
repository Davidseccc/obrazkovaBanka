package cz.uhk.obrazkovaBanka;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import cz.uhk.obrazkovaBanka.entity.Category;
import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.Rating;
import cz.uhk.obrazkovaBanka.entity.Tag;
import cz.uhk.obrazkovaBanka.entity.services.CategoryService;
import cz.uhk.obrazkovaBanka.entity.services.CommentService;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;
import cz.uhk.obrazkovaBanka.entity.services.RatingService;
import cz.uhk.obrazkovaBanka.entity.services.TagService;
import cz.uhk.obrazkovaBanka.entity.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Image Not Found")
@RequestMapping(value = "/image")
public class ImageController {

	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;
	@Autowired
	TagService tagService;
	@Autowired
	RatingService ratingService;
	@Autowired
	CategoryService categoryService;

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String noImage(Model model) {
		String str = "No image specified.</br>Try to <a href=\"/obrazkovaBanka/search?image\">find</a> someone.";
		model.addAttribute("ERROR", str);
		return "error";
	}

	@RequestMapping(value = "/{imageId}")
	public String showImage(@PathVariable String imageId, Model model) {
		Image image = imageService.findImageByHash(imageId);

		if (image == null) {
			logger.info("IMAGE NOT FOUND. Image with hash " + imageId + " not found.");
			throw new ResourceAccessException("IMAGE NOT FOUND.");
		}
		List<Tag> tags = tagService.findTagsByImageId(image.getId());
		double avgRating = ratingService.findAvgRatingbyImageId(image.getId());
		model.addAttribute("image", image);
		model.addAttribute("avgRating", String.format("%.2f", avgRating));
		model.addAttribute("TagList", tags);
		return "image/show";
	}

	@RequestMapping(value = "/{imageId}", params = "edit", method = RequestMethod.GET)
	public String editImage(@PathVariable String imageId, Model model) {
		Image image = imageService.findImageByHash(imageId);

		if (image == null) {
			logger.info("IMAGE NOT FOUND. Image with hash " + imageId + " not found.");
			throw new ResourceAccessException("IMAGE NOT FOUND.");
		}
		List<Tag> tags = tagService.findTagsByImageId(image.getId());
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("image", image);
		model.addAttribute("TagList", tags);
		model.addAttribute("categoryList", categories);
		return "image/edit";
	}
	@Transactional
	@RequestMapping(value = "/{imageId}", params = "save", method = RequestMethod.POST)
	public String saveEditedImage(@ModelAttribute("image") @Validated Image image, @PathVariable String imageId,
			@RequestParam(value = "tagList") String tags, @RequestParam(value = "cat") String category, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.info("IMAGE is NOT VALID");
			// return "redirect:/image/" + imageId;
		}
		Image i = imageService.findImageByHash(imageId);
		if (i == null) {
			logger.info("IMAGE is NOT FOUND");
		}
		String[] items = tags.split(";");
		System.out.println("items lenght " + items.length);
		if (tags != null && !tags.isEmpty()) {
			List<Tag> tagList = new ArrayList<Tag>();
			// TODO nefunguje korektnì
			for (String item : items) {
				Tag t = new Tag();
				t.setName(item);
				//tagService.saveTag(t);
				System.out.println("ading " + t.toString() + "to taglist");
				tagList.add(t);
			}
			i.setTags(tagList);
		}
		Category cat = categoryService.findCategoryByName(category);
		i.setName(image.getName());
		i.setDescription(image.getDescription());
		i.setCategory(cat);
		imageService.updateImage(i);
		return "redirect:/image/" + imageId;
	}

	@RequestMapping(value = "/rate/{imageId}/{rating}", method = RequestMethod.GET)
	public String rateImage(@PathVariable Integer imageId, @PathVariable Integer rating, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Image image = imageService.findImage(imageId);
		if (image == null) {
			logger.info("IMAGE NOT FOUND. Image with id " + imageId + " not found.");
			throw new ResourceAccessException("IMAGE NOT FOUND.");
		}
		Cookie cookies[] = request.getCookies();
		Cookie cook;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cook = cookies[i];
				if (cook.getName().equalsIgnoreCase(imageId.toString())) {
					System.out.println("Already voted " + cook.getValue());
					String str = "You already VOTED this image. </br> You can vote once per 24 hours.";
					model.addAttribute("ERROR", str);
					return "error";
				}

			}
		}
		rate(imageId, rating, response, image);

		return "redirect:/image/" + image.getHash();
	}

	private void rate(Integer imageId, Integer rating, HttpServletResponse response, Image image) {
		Rating r = new Rating();
		r.setDate(new Date());
		r.setValue(rating);
		r.setImage(image);
		ratingService.saveRating(r);
		Cookie cookie = new Cookie(imageId.toString(), rating.toString()); // bake
																			// cookie
		cookie.setMaxAge(86400); // set expire time to 24 hours
		response.addCookie(cookie); // put cookie in response
	}

	@Transactional
	@RequestMapping(value = "/delete/{hash}", method = RequestMethod.GET)
	public String deleteImage(@PathVariable String hash, HttpServletResponse response, Model model) {
		System.out.println("Delete image with hash " + hash);
		Image i = imageService.findImageByDeleteHash(hash);
		if (i == null) {
			logger.info("No image found");
			noImage(model);
		}
		try {
			String path = FileController.PATH.concat(i.getHash());
			FileController.deleteRecursive(new File(path));
			imageService.deleteImage(i);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("vymazání se nezdaøilo");

		}

		return "image/deletedSucesfully";
	}
}
