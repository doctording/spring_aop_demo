package com.bjsxt.service;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;


// serverce ҵ���߼����������ݿ�����������Բ���������
public class UserService {
	
	//���� UserDao(����ʵ�ֲ�һ��)
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
