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
		// Spring�Զ�װ��
		// ��ȡ�����ļ���Ȼ��õ�����ӳ���ϵ
		// �����ʼ��������Щ�����ŵ�һ��������
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
