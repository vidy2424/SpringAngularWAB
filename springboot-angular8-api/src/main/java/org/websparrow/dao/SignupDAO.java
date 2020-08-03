package org.websparrow.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.websparrow.entity.Info;
import org.websparrow.entity.SignUpUser;
import org.websparrow.entity.User;

 
@Service
public interface SignupDAO {

	SignUpUser get(String name);
	Info getid(int id);
 	static List<SignUpUser> list() {
		// TODO Auto-generated method stub
		return null;
	}
	boolean add(User user);
	boolean add(Info info);
	boolean update(Info info);
	boolean delete(int id);
 
	
	List<User> loginbyName(String username);
	List<User> getAllUser();
	
	List<Info> getAllInfo();
	
	//Object getByEmail(String username);
	User getByUsername(String username);
	List<Info> getbyID(int id);
}


