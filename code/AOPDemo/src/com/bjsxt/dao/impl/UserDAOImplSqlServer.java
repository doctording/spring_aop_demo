package com.bjsxt.dao.impl;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;

// ��������ݿ�洢����ʵ��
public class UserDAOImplSqlServer implements UserDAO {

	public void save(User user) {
		//Hibernate
		//JDBC
		//XML
		//NetWork
		System.out.println("use Sql Server: user saved!");
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

}
