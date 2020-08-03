package org.websparrow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.websparrow.dao.SignupDAO;
import org.websparrow.entity.Info;
import org.websparrow.entity.User;
import org.websparrow.repository.JwtTokenRepository;
import org.websparrow.service.StudentService;

 
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private SignupDAO signupDAO;

	@Autowired
	private JwtTokenRepository jwtTokenRepository;

	
	
	@CrossOrigin(origins = "http://localhost:4200")

	/*
	 * @GetMapping public ResponseEntity<Response> get() {
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(new
	 * Response(studentService.get(), new Date())); }
	 */

	@PostMapping(value = "/Signup")
	public List<User> register(@RequestBody User user) {
		// System.out.println(user);

		signupDAO.add(user);

		signupDAO.get(user.getUsername());
		// System.out.println(user.getUsername().toString());

		List<User> sign2 = signupDAO.getAllUser();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		List<User> sign1 = new ArrayList<>();

		System.out.println(sign1.toString());
		return sign1;
	}

	@PostMapping(value = "/info")
	public List<Info> register(@RequestBody Info info) {
		// System.out.println(user);

		signupDAO.add(info);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		signupDAO.get(info.getPlanName());
		// System.out.println(info.getPlanName().toString());
		List<Info> sign2 = signupDAO.getAllInfo();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(sign2.toString());
		return sign2;
	}

	@PostMapping(value = "/login1")
	public List<User> register1(@RequestBody User user) {
		// System.out.println(user.toString());
		// signupDAO.add(signUp);

		signupDAO.get(user.getUsername());
		// System.out.println(user.getUsername().toString());
		List<User> sign = signupDAO.getAllUser();

		List<User> sign1 = signupDAO.loginbyName(user.getUsername());
		// System.out.println(sign1.toString());
		return sign1;
	}

	
	@RequestMapping(value = "/login")
	public ModelAndView login(
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout,
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("login");
		HttpSession session= request.getSession(false);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		  
		SecurityContextHolder.clearContext();
         session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
         
		System.out.println("auth ===" + auth);
		System.out.println("logout ===" + logout);

		Map<String, Object> mapproducts = new HashMap<String, Object>();
		System.out.println("Login");
		mv.addObject("title", "Login");
		mapproducts.put("title", "Login");
		if (error != null) { 
			mv.addObject("message", "Username and Password is invalid!");
			mapproducts.put("message", "Username and Password is invalid!");
		}
		if (logout != null) {
			mv.addObject("logout", "You have logged out successfully!");
			mapproducts.put("logout", "You have logged out successfully!");
		}
		if(request.getHeader("Authorization")!= null){
			System.out.println("request ===" + request.getHeader("Authorization"));
			String blackListToken = request.getHeader("Authorization");
			jwtTokenRepository.save(blackListToken);
			System.out.println("exist ===" + jwtTokenRepository.exist(blackListToken));
		}
		return mv; 
	}


	@RequestMapping(value = "/loginaa")
	public String logina(
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout,
			HttpServletRequest request,
			HttpServletResponse response) {
		//ModelAndView mv = new ModelAndView("login");
		HttpSession session= request.getSession(false);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		  
		SecurityContextHolder.clearContext();
         session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
         
		System.out.println("auth ===" + auth.toString());
		System.out.println("logout ===" + logout);

		Map<String, Object> mapproducts = new HashMap<String, Object>();
		System.out.println("Login");
		String sign = auth.toString();
		
		/*mv.addObject("title", "Login");
		mapproducts.put("title", "Login");
		if (error != null) { 
			mv.addObject("message", "Username and Password is invalid!");
			mapproducts.put("message", "Username and Password is invalid!");
		}
		if (logout != null) {
			mv.addObject("logout", "You have logged out successfully!");
			mapproducts.put("logout", "You have logged out successfully!");
		}*/
		 if(request.getHeader("Authorization")!= null){
			System.out.println("request ===" + request.getHeader("Authorization"));
			String blackListToken = request.getHeader("Authorization");
			jwtTokenRepository.save(blackListToken);
			System.out.println("exist ===" + jwtTokenRepository.exist(blackListToken));
		}
		return sign;		 
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
		// Removes the authentication from securitycontext
		HttpSession session= request.getSession(false);
		SecurityContextHolder.clearContext();
         session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        System.out.println("logout....");
    return "logout";
	}
	
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Aha! Caught You.");
		mv.addObject("errorDescription",
				"You are not authorized to view this page!");
		mv.addObject("title", "403 Access Denied");
		return mv;
	}
	
	@RequestMapping(value = "/plan/info")
	public List<Info> getAllInfo() {
  
		List<Info> planinfo = signupDAO.getAllInfo();
		// System.out.println(planinfo.toString());
		System.out.println("this is getmapping");
		return planinfo;
	}

	@RequestMapping(value = "/infos/{id}")
	String showSingleProduct(@RequestBody Info info, @PathVariable int id) {

		System.out.println("this is id");
		// Info info = signupDAO.get(id);
		// System.out.println(info.getId());
		// signupDAO.update(info);

		return "";

	}

	@PutMapping("/infos/{id}")
	public String updateStudent(@RequestBody Info info, @PathVariable int id) {

		//List<Info> information = signupDAO.getbyID(id);

		System.out.println("this is editid");

		info.setId(id);

		signupDAO.update(info);

		// info1.update(info1);

		return "info";
	}
	
	
	
	
	
	@RequestMapping(value = "/infDelete/{id}")
	public void deleteStudent(@PathVariable int id) {
		System.out.println("this is deleteid");
		 	signupDAO.delete(id);	 
	}

	@DeleteMapping("/infDelete1/{id}")  
	private void deleteBook(@PathVariable int id)   
	{  
		System.out.println("this is deleteid");
		signupDAO.delete(id);  
	}
	
	/*
	 * @PostMapping public ResponseEntity<Response> save(@RequestBody Student
	 * student) {//
	 * 
	 * System.out.println("123"); System.out.println("d" + student); return
	 * ResponseEntity.status(HttpSqtatus.OK).body(new
	 * Response(studentService.save(student), new Date())); }
	 * 
	 * @PutMapping public ResponseEntity<Response> update(@RequestBody Student
	 * student) { return ResponseEntity.status(HttpStatus.OK).body(new
	 * Response(studentService.save(student), new Date())); }
	 * 
	 * @DeleteMapping public ResponseEntity<Response> delete(@RequestParam("id") int
	 * id) { studentService.delete(id); return
	 * ResponseEntity.status(HttpStatus.OK).body(new Response(true, new Date())); }
	 */
}
