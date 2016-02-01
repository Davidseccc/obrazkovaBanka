package cz.uhk.obrazkovaBanka;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.uhk.obrazkovaBanka.entity.Role;
import cz.uhk.obrazkovaBanka.entity.User;
import cz.uhk.obrazkovaBanka.entity.services.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	
	//private static final Logger logger = LoggerFactory.getLogger(CatogoryController.class);


	
	@RequestMapping(value="show", params="all")
	public String findAllRoles(Model model){
		List <Role> roles = roleService.getAllRoles();
		model.addAttribute("roleList", roles);
		//System.out.println(counts[0].toString());
		//model.addAttribute("counts",counts);
		return "role/roles";
	}
	
	@Transactional
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String updateCategory(@ModelAttribute("role") @Validated Role role, RedirectAttributes redirectAttributes, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			String str = bindingResult.getGlobalErrors().toString();
			redirectAttributes.addFlashAttribute("ERROR", str);
			return "redirect:show?all";
		}
		Role r = roleService.findRole(role.getId());
		//r.setName(role.getName());
		r.setDescription(role.getDescription());
		roleService.updateRole(r);
		redirectAttributes.addFlashAttribute("OK", "Succesfully saved");
		return "redirect:show?all";
	}
	
//	@Transactional
//	@RequestMapping(value="addNew", method = RequestMethod.POST)
//	public String addNewCategory(@RequestParam(value = "name") String name, RedirectAttributes redirectAttributes, BindingResult bindingResult){
//		Role r = new Role();
//		r.setName(name);
//		roleService.saveRole(r);
//		redirectAttributes.addFlashAttribute("OK", "Succesfully Added");
//		return "redirect:show?all";
//	}
}
