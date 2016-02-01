package cz.uhk.obrazkovaBanka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Autowired
	ImageService imageService;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	//@Autowired
	//ImageService imageService;
		

	
	@RequestMapping(params="image")
	public String findImageByName(){
		
		return "search/form";
	}
	@RequestMapping(params="q", method = RequestMethod.GET)
	public String findImagesWithName(@RequestParam(value = "q") String name, Model model){
		if(name.length()< 1){
			String str = "You must specify the name.";
			model.addAttribute("ERROR", str);
			return "search/gallery";
		}
		List<Image> images = imageService.findImagesByName(name);
		model.addAttribute("images", images);
		return "search/gallery";
	}
		
	@RequestMapping(params="tag", method = RequestMethod.GET)
	public String searchImagesByTag(@RequestParam(value = "tag") String tag, Model model){
		List<Image> images = imageService.findImagesWithTag(tag);
		model.addAttribute("images", images);
		return "/search/gallery";
	}
	
	
}
