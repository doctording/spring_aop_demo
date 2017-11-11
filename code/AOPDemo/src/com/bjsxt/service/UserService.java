package com.bjsxt.service;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


// serverce 业务逻辑，除了数据库操作，还可以操作其它的
public class UserService {
	
	//引用 UserDao(具体实现不一样)
	private UserDAO userDAO;  
	
	public void add(User user) {
		
		userDAO.save(user);
		
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
