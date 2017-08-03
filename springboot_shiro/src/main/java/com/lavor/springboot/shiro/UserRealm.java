package com.lavor.springboot.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/** 身份校验核心类,Shiro是使用realm进行身份验证的
 * Created by lei.zeng on 2017/8/3.
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 权限信息(授权):
     * 如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache.xml中配置），
     * 超过这个时间间隔再刷新页面，该方法会被执行。
     * 如果不设置缓存每次访问需授权资源时都会执行该方法。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //这里获取doGetAuthenticationInfo(AuthenticationToken authenticationToken)方法返回对象中设置的Principal
        UserInfo userInfo  = (UserInfo) principalCollection.getPrimaryPrincipal();
        //根据用户所拥有的的角色与角色中所拥有的的权限为用户添加角色和权限
        for(UserRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(UserPermission p:role.getPermissions()){
                System.out.println(role.getPermissions());
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证信息(身份验证)
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份验证");
        //获取用户的输入的账号，这里获取的用户名与密码是在登录时UsernamePasswordToken中设置的
        String username = (String)authenticationToken.getPrincipal();
        //获取用户输入密码，不要直接使用(String)authenticationToken.getCredentials()，
        // 否则会出错，导致身份验证失败
        String password = new String((char[])authenticationToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername("root");
        if(userInfo == null){
            return null;
        }
        //设置认证信息，我们可以在doGetAuthorizationInfo(PrincipalCollection principalCollection)方法中获取这里设置的信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户
                userInfo.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
