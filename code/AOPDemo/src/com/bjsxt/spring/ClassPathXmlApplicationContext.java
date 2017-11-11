package com.bjsxt.spring;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

// 实现beanFactory interface
public class ClassPathXmlApplicationContext implements BeanFactory {
	
	// id -> 具体类的一个映射
	private Map<String , Object> beans = new HashMap<String, Object>();
	
	//IOC Inverse of Control DI Dependency Injection
	public ClassPathXmlApplicationContext() throws Exception {
		SAXBuilder sb=new SAXBuilder();
	    
	    Document doc=sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml")); //构造文档对象
	    Element root=doc.getRootElement(); //获取根元素HD
	    List list=root.getChildren("bean");//取名字为disk的所有元素
	    
	    for(int i=0;i<list.size();i++){
	       Element element=(Element)list.get(i);
	       String id=element.getAttributeValue("id");
	       String clazz=element.getAttributeValue("class");
	       
	       // 反射构造对象
	       Object o = Class.forName(clazz).newInstance();
	       System.out.println(id);
	       System.out.println(clazz);
	       beans.put(id, o); // id-对象的映射 存储到beans中
	       /*
	        <bean id="u" class="com.bjsxt.dao.impl.UserDAOImplMySQL" />
			<bean id="userService" class="com.bjsxt.service.UserService" >
				<property name="userDAO" bean="u"/>
			</bean>
	        */
	       
	       // property属性：需要注入相应的对象
	       for(Element propertyElement : (List<Element>)element.getChildren("property")) {
	    	   String name = propertyElement.getAttributeValue("name"); //userDAO
	    	   String bean = propertyElement.getAttributeValue("bean"); //u
	    	   
	    	   // bean属性，就是id,然后映射 就能得到对象
	    	   Object beanObject = beans.get(bean);//UserDAOImpl instance
	    	
	    	   // 对象的 setUserDAO方法, 先获取方法名，然后invoke,注入
	    	   String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	    	   System.out.println("method name = " + methodName);
	    	   // setUserDAO(UserDao u); 方法
	    	   
	    	   Method m = o.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
	    	   m.invoke(o, beanObject);
	       }
	       
	       
	    }  
	  
	}
	
	// 根据id得到对象
	public Object getBean(String id) {
		return beans.get(id);
	}

}
