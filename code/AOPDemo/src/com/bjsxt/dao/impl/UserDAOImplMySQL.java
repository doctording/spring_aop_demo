package com.bjsxt.dao.impl;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;

// 具体的数据库存储操作实现
public class UserDAOImplMySQL implements UserDAO {

	public void save(User user) {
		//Hibernate
		//JDBC
		//XML
		//NetWork
		System.out.println("use MySQL: user saved!");
	}

	@Override
	public void delete(User user) {
		System.out.println("use MySQL: user deleted!");
	}

}
