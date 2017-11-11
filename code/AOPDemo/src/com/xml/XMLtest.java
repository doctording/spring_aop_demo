package com.xml;


import java.util.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class XMLtest {
  
	public static void main(String[] args) throws Exception{ 
	    
		SAXBuilder sb=new SAXBuilder();
		    
		Document doc=sb.build(XMLtest.class.getClassLoader()
		    		.getResourceAsStream("beans.xml")); //�����ĵ�����
		
		Element root=doc.getRootElement(); //��ȡ��Ԫ��
		List list=root.getChildren("bean");//ȡ����Ϊbean������Ԫ��
		
		for(int i=0;i<list.size();i++){
		       Element element=(Element)list.get(i);
		       String id=element.getAttributeValue("id");
		       String clazz=element.getAttributeValue("class");
		       System.out.println(id);
		       System.out.println(clazz);
	    }  
	}
	
	/*
	public static void main(String[] args) throws Exception{ 
    
		SAXBuilder sb=new SAXBuilder();
	    
	    Document doc=sb.build(XMLtest.class.getClassLoader()
	    		.getResourceAsStream("test.xml")); //�����ĵ�����
	    Element root=doc.getRootElement(); //��ȡ��Ԫ��HD
	    List list = root.getChildren();
	    
	    for(int i=0;i<list.size();i++){
	       Element element = (Element) list.get(i);
	       
	       String capacity=element.getChildText("capacity");//ȡdisk��Ԫ��capacity������
	       String directories=element.getChildText("directories");
	       String files=element.getChildText("files");
	       
	       System.out.println("������Ϣ:");
	       System.out.println("�����̷�:"+element.getAttributeValue("name"));
	       System.out.println("��������:"+capacity);
	       System.out.println("Ŀ¼��:"+directories);
	       System.out.println("�ļ���:"+files);
	       System.out.println("-----------------------------------");
	    }  
	}*/
} 