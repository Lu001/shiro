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
		System.out.println("---检验----");
		User tom = new User();
		tom.setName("tom");
		tom.setPassword("123");
 
        if(login(tom))
            System.out.println("登录成功");
        else
            System.out.println("登录失败");
		
		//数据源
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
		//是否登录成功
		for (User user : users) {
			if(login(user)){
				System.out.println(user.getName()+"登录成功，密码是"+user.getPassword());
			}else{
				System.out.println(user.getName()+"登录失败，密码是"+user.getPassword());
			}
		}
		System.out.println("--------用户拥有角色-------------");
		//用户拥有角色
		for (User user : users) {
			if(login(user)){
				for (String role : roles) {
					if(hasRole(role))
						System.out.println(user.getName()+"拥有角色"+role);
					else
						System.out.println(user.getName()+"不拥有角色"+role);
				}
			}
		}
		System.out.println("--------用户拥有权限-------------");
		for (User user : users) {
			if(login(user)){
				for (String permit : permits) {
					if(isPermitted(permit))
						System.out.println(user.getName()+"拥有权限"+permit);
					else
						System.out.println(user.getName()+"不拥有权限"+permit);
				}
			}
		}*/
		
	}
	

	
	private static boolean login(User user){
		//获取数据库的数据 然后进行比对
		Subject subject = getSubject();
		//如果已经登录过了，退出
		if(subject.isAuthenticated())
			subject.logout();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		//将用户的数据token 最终传递到Realm中进行对比
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
