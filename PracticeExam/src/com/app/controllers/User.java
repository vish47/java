package com.app.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.IStudentDao;
import com.app.pojo.Student;

@Controller
@RequestMapping("/user")
public class User {
	@Autowired
	IStudentDao std;

	public User() {
		super();
		System.out.println("In User Controller");
	}
	 
	@GetMapping("/list")
	public String ShowAllEmployee(Model map)
	{
		 map.addAttribute("EmployeeList" ,std.GetAllStudent());
		 
		return "/user/list";
		
		
	}
	
	@GetMapping("/login")
	public String showLoginform()
	{
		System.out.println("in login");
		return "/user/login"; 
	}
	@PostMapping("/login")
	public String processLoginForm(Model map,
			@RequestParam String email,
			@RequestParam String password,
			HttpSession hs)
	{
		System.out.println("process login form");
		try
		{
			Student s=std.validateUser(email, password);
			map.addAttribute("status","Successfull login");
			hs.setAttribute("StudentDetails",s);
			if(s.getName().equals("vishal"))
			{
				System.out.println(s);
				 map.addAttribute("EmployeeList" ,std.GetAllStudent());
				return "user/list";
			}
			
		}catch(RuntimeException e)
		{
			System.out.println("err in ucontr "+e);
			map.addAttribute("status","invalid login,plz retry!!! ");
			return "/user/login";
		}
		return "/vendor/details";
	}
	
	@GetMapping("/register")
	public String Register()
	{
		
		return "user/register";
	}
	
	
	@PostMapping("/register")
	public String Registration(Model map,@RequestParam  String name,@RequestParam String lastname,@RequestParam String Subject,@RequestParam String email,@RequestParam String pass,@RequestParam String Date)
	{
		System.out.println("Procees to registration");
		
		Student s1=new Student(name,lastname,email,pass,Subject,Date);
		String str=std.InserData(s1);
		return "user/register";
		
		
		
	}
	

}
