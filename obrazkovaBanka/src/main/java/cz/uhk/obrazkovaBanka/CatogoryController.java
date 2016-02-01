package cz.uhk.obrazkovaBanka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.uhk.obrazkovaBanka.entity.Category;
import cz.uhk.obrazkovaBanka.entity.Image;
import cz.uhk.obrazkovaBanka.entity.services.CategoryService;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;

@Controller
@RequestMapping(value="/category")
public class CatogoryController {
	
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(CatogoryController.class);


	
	@RequestMapping(value="show", params="all")
	public String findAllCategories(Model model){
		List <Category> categories = categoryService.getAllCategories();
		model.addAttribute("categoryList", categories);
		//System.out.println(counts[0].toString());
		//model.addAttribute("counts",counts);
		return "category/categories";
	}
	@Transactional
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String updateCategory(@RequestParam(value = "id") int id,@RequestParam(value = "name") String name, RedirectAttributes redirectAttributes){
		Category c = categoryService.findCategory(id);
		c.setName(name);
		categoryService.updateCategory(c);
		redirectAttributes.addFlashAttribute("OK", "Succesfully saved");
		return "redirect:show?all";
	}
	
	@Transactional
	@RequestMapping(value="addNew", method = RequestMethod.POST)
	public String addNewCategory(@RequestParam(value = "name") String name, RedirectAttributes redirectAttributes){
		Category c = new Category();
		c.setName(name);
		categoryService.saveCategory(c);
		redirectAttributes.addFlashAttribute("OK", "Succesfully Added");
		return "redirect:show?all";
	}
}
