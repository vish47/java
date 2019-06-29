package com.app.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojo.Student;


@Repository
@Transactional
public class StudentDao implements IStudentDao{
	Student s;
	@Autowired
	private SessionFactory sf;

	@Override
	public Student validateUser(String email, String pass) {
		System.out.println(email);
		System.out.println(pass);
		String jpql="select v from Student v where email=:em and pass=:pass";
		return sf.getCurrentSession().createQuery(jpql,Student.class)
				.setParameter("em", email).setParameter("pass",pass).getSingleResult();
	}

	@Override
	public String InserData(Student s) {
		Session hs=sf.getCurrentSession();
	     hs.save(s);
		return "Data inserted Successfully";
	}

	@Override
	
		// TODO Auto-generated method stub
		public List<Student> GetAllStudent() {
			
			String Jpql="select v from Student v";
			
			return sf.getCurrentSession().createQuery(Jpql,Student.class).getResultList();
		    

	}

	@Override
	public String Registration(Student s) { 
		
		
		Session hs=sf.getCurrentSession();
		hs.save(s);
		
		return "data inserted sussesfully";
		// TODO Auto-generated method stub
		
	}

}
