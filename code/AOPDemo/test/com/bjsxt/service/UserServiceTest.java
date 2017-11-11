package com.bjsxt.service;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.aop.LogInterceptor;
import com.aop.LogInterceptorProxy;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.dao.impl.UserDAOImplMySQL;
import com.bjsxt.model.User;
import com.bjsxt.spring.BeanFactory;
import com.bjsxt.spring.ClassPathXmlApplicationContext;
import com.sun.org.glassfish.external.probe.provider.annotations.Probe;


public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {
		// Spring自动装配
		// 读取配置文件，然后得到各个映射关系
		// 程序初始化，把这些东西放到一个容器中
		BeanFactory applicationContext = new ClassPathXmlApplicationContext();
		
		UserService service = (UserService)applicationContext.getBean("userService");

		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");
		service.add(u);
	}

	@Test
	public void testProxy() throws Exception {
		
		UserDAO userDAO = new UserDAOImplMySQL();
		LogInterceptorProxy logproxy = new LogInterceptorProxy();
		logproxy.setTarget(userDAO);
		
		UserDAO userDAOProxy = 
			(UserDAO)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(),
				new Class[]{UserDAO.class}, logproxy);
			
		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");
		
		userDAOProxy.save(u);
		userDAOProxy.delete(u);
	}
}
