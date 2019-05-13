package cn.liontalk.springbootshiro.realm;

import cn.liontalk.springbootshiro.constant.SysConstant;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.LoginService;
import cn.liontalk.springbootshiro.service.MenuService;
import cn.liontalk.springbootshiro.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    LoginService loginService;

    @Autowired
    MenuService menuService;


    /**
     * 登录认证 一般情况下 , 登录成功之后就给当前用户进行权限赋予了
     * 根据用户的权限信息做授权判断，这一步是以doGetAuthenticationInfo为基础的，只有在有用户信息后才能根据用户的角色和授权信息做判断是否给用户授权，因此这里的Roles和Permissions是用户的两个重点判断依据
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getCredentials() == null) {
            return null;
        }
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        ManagerEntity managerEntity = loginService.findManagerByName(username);
        // 账号不存在
        if (managerEntity == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 密码错误
        if (!password.equals(managerEntity.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (managerEntity.getStatus().equals(SysConstant.LOCK_STATUS)) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(managerEntity, password, getName());
        return info;

    }

    /**
     * 权限认证
     * 获取用户的权限信息，这是为下一步的授权做判断，获取当前用户的角色和这些角色所拥有的权限信息
     *
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //获取登录用户名
//        String name = (String) principalCollection.getPrimaryPrincipal();
//        //查询用户名称
//        ManagerEntity user = loginService.findManagerByName(name);
//        //添加角色和权限
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for (RoleEntity role : user.getRoleEntityList()) {
//            //添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (MenuEntity permission : role.getMenuEntityList()) {
//                //添加权限
//                simpleAuthorizationInfo.addStringPermission(permission.getPerms());
//            }
//        }
//        return simpleAuthorizationInfo;

        Integer userId = ShiroUtils.getManagerId();
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }



}
