package com.bjsxt.spring;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

// ʵ��beanFactory interface
public class ClassPathXmlApplicationContext implements BeanFactory {
	
	// id -> �������һ��ӳ��
	private Map<String , Object> beans = new HashMap<String, Object>();
	
	//IOC Inverse of Control DI Dependency Injection
	public ClassPathXmlApplicationContext() throws Exception {
		SAXBuilder sb=new SAXBuilder();
	    
	    Document doc=sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml")); //�����ĵ�����
	    Element root=doc.getRootElement(); //��ȡ��Ԫ��HD
	    List list=root.getChildren("bean");//ȡ����Ϊdisk������Ԫ��
	    
	    for(int i=0;i<list.size();i++){
	       Element element=(Element)list.get(i);
	       String id=element.getAttributeValue("id");
	       String clazz=element.getAttributeValue("class");
	       
	       // ���乹�����
	       Object o = Class.forName(clazz).newInstance();
	       System.out.println(id);
	       System.out.println(clazz);
	       beans.put(id, o); // id-�����ӳ�� �洢��beans��
	       /*
	        <bean id="u" class="com.bjsxt.dao.impl.UserDAOImplMySQL" />
			<bean id="userService" class="com.bjsxt.service.UserService" >
				<property name="userDAO" bean="u"/>
			</bean>
	        */
	       
	       // property���ԣ���Ҫע����Ӧ�Ķ���
	       for(Element propertyElement : (List<Element>)element.getChildren("property")) {
	    	   String name = propertyElement.getAttributeValue("name"); //userDAO
	    	   String bean = propertyElement.getAttributeValue("bean"); //u
	    	   
	    	   // bean���ԣ�����id,Ȼ��ӳ�� ���ܵõ�����
	    	   Object beanObject = beans.get(bean);//UserDAOImpl instance
	    	
	    	   // ����� setUserDAO����, �Ȼ�ȡ��������Ȼ��invoke,ע��
	    	   String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	    	   System.out.println("method name = " + methodName);
	    	   // setUserDAO(UserDao u); ����
	    	   
	    	   Method m = o.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
	    	   m.invoke(o, beanObject);
	       }
	       
	       
	    }  
	  
	}
	
	// ����id�õ�����
	public Object getBean(String id) {
		return beans.get(id);
	}

}
