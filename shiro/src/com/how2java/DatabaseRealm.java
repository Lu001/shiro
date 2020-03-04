package com.how2java;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class DatabaseRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// �û���Ȩ
		String userName =  (String)principalCollection.getPrimaryPrincipal();
		Set<String> permissions = new DAO().listRole(userName);
		Set<String> roles = new DAO().listPermissions(userName);
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		s.setStringPermissions(permissions);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		// ��ȡ�˺�����
		//UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = token.getPrincipal().toString();
		//String password = new String(t.getPassword());
		
		//��ȡ���ݿ������
		User user = new DAO().getUser(userName);
		String passwordInDB = user.getPassword();
		String salt = user.getSalt();
		
		//String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();
		/*if(null == user || !passwordEncoded.equals(passwordInDB))
			throw new AuthenticationException();*/
	       //��֤��Ϣ
		
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,passwordInDB,ByteSource.Util.bytes(salt),getName());
			//SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,password,getName());
		return a;
	}

}