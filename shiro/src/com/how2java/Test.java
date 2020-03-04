package com.how2java;

import java.util.ArrayList;
import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;



public class Test {
	public static void main(String[] args) {
		System.out.println("---����----");
		User tom = new User();
		tom.setName("tom");
		tom.setPassword("123");
 
        if(login(tom))
            System.out.println("��¼�ɹ�");
        else
            System.out.println("��¼ʧ��");
		
		//����Դ
		/*User user1 = new User("zhang3","12345");
		User user2 = new User("li4","abcde");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		String roleAdmin = "admin";
		String roleProductManager = "productManager";
		List<String> roles = new ArrayList<>();
		roles.add(roleAdmin);
		roles.add(roleProductManager);
		
		String permitAddProduct = "permitAddProduct";
		String permitAddOrder = "permitAddOrder";
		String addProduct = "addProduct";
		List<String> permits = new ArrayList<>();
    	permits.add(permitAddProduct);
    	permits.add(permitAddOrder);
    	permits.add(addProduct);
		//�Ƿ��¼�ɹ�
		for (User user : users) {
			if(login(user)){
				System.out.println(user.getName()+"��¼�ɹ���������"+user.getPassword());
			}else{
				System.out.println(user.getName()+"��¼ʧ�ܣ�������"+user.getPassword());
			}
		}
		System.out.println("--------�û�ӵ�н�ɫ-------------");
		//�û�ӵ�н�ɫ
		for (User user : users) {
			if(login(user)){
				for (String role : roles) {
					if(hasRole(role))
						System.out.println(user.getName()+"ӵ�н�ɫ"+role);
					else
						System.out.println(user.getName()+"��ӵ�н�ɫ"+role);
				}
			}
		}
		System.out.println("--------�û�ӵ��Ȩ��-------------");
		for (User user : users) {
			if(login(user)){
				for (String permit : permits) {
					if(isPermitted(permit))
						System.out.println(user.getName()+"ӵ��Ȩ��"+permit);
					else
						System.out.println(user.getName()+"��ӵ��Ȩ��"+permit);
				}
			}
		}*/
		
	}
	

	
	private static boolean login(User user){
		//��ȡ���ݿ������ Ȼ����бȶ�
		Subject subject = getSubject();
		//����Ѿ���¼���ˣ��˳�
		if(subject.isAuthenticated())
			subject.logout();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		//���û�������token ���մ��ݵ�Realm�н��жԱ�
		try{
			subject.login(token);
		}catch(AuthenticationException e){
			return false;
		}
		return subject.isAuthenticated();
	}
	
	private static Subject getSubject(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager sm = factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
		Subject subject = SecurityUtils.getSubject();
		return subject;
	}
	
	private static boolean hasRole(String role) {
		Subject subject = getSubject();
		return subject.hasRole(role);
	}
	private static boolean isPermitted(String permit) {
		Subject subject = getSubject();
		return subject.isPermitted(permit);
	}
	

     

}
