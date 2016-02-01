package cz.uhk.obrazkovaBanka;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.uhk.obrazkovaBanka.entity.User;
import cz.uhk.obrazkovaBanka.entity.services.CommentService;
import cz.uhk.obrazkovaBanka.entity.services.ImageService;
import cz.uhk.obrazkovaBanka.entity.services.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;

	
	@RequestMapping(params = "register")
	public String registerNew(Model model) {
		model.addAttribute(new User());
		return "user/register";
	}

	@RequestMapping(params = "login")
	public String loginNow() {
		return "user/login";
	}
	
	@RequestMapping(value = "/{userId}")
	public String getUserProfile(@PathVariable String userId, Map<String, Object> model, HttpSession session ){
		User user = userService.findUserByNickName(userId);
		model.put("user", user);
		
		return "user/home";
	}
	
	@RequestMapping(value = "/{userId}/edit")
	public String editUserProfile(@PathVariable String userId, Map<String, Object> model, HttpSession session ){
		User user = userService.findUserByNickName(userId);
		model.put("user", user);
		return "user/edit";
	}

	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Validated User user, BindingResult result, RedirectAttributes redirectAttributes)  {
		if (result.hasErrors()){
			redirectAttributes.addAttribute("ERROR", "All fields are requiered");
			return "user/register";
		}
		user.setRegisteredDate(new Date());
		System.out.println(user.toString());
		String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(passwordHash);
		userService.saveUser(user);
		return "user/userSaved";
	}

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(User user, Model model,Map<String, Object> mod , HttpSession session, RedirectAttributes redirectAttributes) {
		if (user.getEmail() == null || user.getPassword() == null){
			return "user/login";
		}
		String email = user.getEmail();
		
		User u = userService.loginUser(email, user.getPassword());
		if (u == null){
			redirectAttributes.addFlashAttribute("ERROR", "Bad email or password!" );
			return "redirect:/user?login";
		}
		else{
			session.setAttribute("loggedInUser", u.getNickName());
			System.out.println("Session Saved " + session.getAttribute("loggedInUser").toString());
			mod.put("user", u);
		return "home";
		}
	}
	
	@RequestMapping(params = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "user/login";
	}
	
	
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	public String updateUser(User user, @PathVariable String userId ,BindingResult result,
			HttpSession session,final RedirectAttributes redirectAttributes ){
		
		 Object uname = session.getAttribute("loggedInUser");
		if(uname == null || !(uname.toString().equalsIgnoreCase(userId))){
			
			redirectAttributes.addFlashAttribute("ERROR","Invalid parameters");
			System.out.println("Invalid parameters");
			return "redirect:/user/" + userId;
		}
		
		User u = userService.findUserByNickName(userId);
		System.out.println(user.toString());
		if(!(user.getEmail().equalsIgnoreCase(u.getEmail()) || user.getEmail().isEmpty())){
			u.setEmail(user.getEmail());
		}
		u.setName(user.getName());
		userService.updateUser(u);
		return "redirect:/user/" + u.getNickName();
	}
	
	@RequestMapping(value = "/{userId}/passchange", method = RequestMethod.POST)
	public String changeUserPassword( @RequestParam("curpasswrd") String currentPass, 
			@RequestParam("passwrd1") String newPass, @RequestParam("password2") String repeatPass, 
			@PathVariable String userId,HttpSession session, final RedirectAttributes redirectAttributes){
		 Object uname = session.getAttribute("loggedInUser");
		 String errorStr = null;
		if(uname == null || !(uname.toString().equalsIgnoreCase(userId)) || 
				currentPass.isEmpty() || newPass.isEmpty() || repeatPass.isEmpty()){
			
			errorStr = "All paramerers are requied";
			redirectAttributes.addFlashAttribute("ERROR",errorStr);
			
			return "redirect:/user/" + userId+ "/edit";
		}
		
		User u = userService.findUserByNickName(userId);
		if(userService.checkUser(u, currentPass) ){
			
			if (! newPass.equals(repeatPass)){
				
				errorStr = "New password and repeat password fields must be identical";
				redirectAttributes.addFlashAttribute("ERROR",errorStr);
			}
			userService.changeUserPassword(u, newPass);
			redirectAttributes.addFlashAttribute("OK", "Successfully saved.");

		}	
		else{
			errorStr="Bad Password";
			redirectAttributes.addFlashAttribute("ERROR",errorStr);
			}
		
		return "redirect:/user/" + userId+ "/edit";
	}
	
	
}

