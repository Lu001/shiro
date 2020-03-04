package com.how2java.realm;
 
import java.util.Set;
 
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.how2java.service.PermissionService;
import com.how2java.service.RoleService;
import com.how2java.service.UserService;
 
public class DatabaseRealm extends AuthorizingRealm {
 
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
     
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //�ܽ��뵽�����ʾ�˺��Ѿ�ͨ����֤��
        String userName =(String) principalCollection.getPrimaryPrincipal();
        //ͨ��service��ȡ��ɫ��Ȩ��
        Set<String> permissions = permissionService.listPermissions(userName);
        Set<String> roles = roleService.listRoles(userName);
         
        //��Ȩ����
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //��ͨ��service��ȡ���Ľ�ɫ��Ȩ�޷Ž�ȥ
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }
 
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //��ȡ�˺�����
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName= token.getPrincipal().toString();
        String password= new String( t.getPassword());
        //��ȡ���ݿ��е�����
        String passwordInDB = userService.getPassword(userName);
 
        //���Ϊ�վ����˺Ų����ڣ��������ͬ����������󣬵��Ƕ��׳�AuthenticationException���������׳��������ԭ����ø��ƽ����ṩ������Ϣ
        if(null==passwordInDB || !passwordInDB.equals(password))
            throw new AuthenticationException();
         
        //��֤��Ϣ�����˺�����, getName() �ǵ�ǰRealm�ļ̳з���,ͨ�����ص�ǰ���� :databaseRealm
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,password,getName());
        return a;
    }
 
}