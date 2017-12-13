package com.spring.mybatis.realm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.spring.mybatis.model.User;
import com.spring.mybatis.service.RoleService;
import com.spring.mybatis.service.UserService;

/**
 * 用户授权信息域
 * 
 * @author coderhuang
 * 
 */
public class UserRealm extends CasRealm {
	
	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;

    private final Log logger = LogFactory.getLog(getClass());

	protected final Map<String, SimpleAuthorizationInfo> roles = new ConcurrentHashMap<String, SimpleAuthorizationInfo>();
	
	/**
	 * 设置角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String account = (String) principals.getPrimaryPrincipal();
        logger.debug("获取用户名："+account);
		SimpleAuthorizationInfo authorizationInfo = null;
		if (authorizationInfo == null) {
			authorizationInfo = new SimpleAuthorizationInfo();
            List<String> permissions = roleService.getPermissions(account);
            authorizationInfo.addStringPermissions(permissions);
            List<String> roles = roleService.getRoles(account);
            logger.debug("获取roles："+roles+"获取permissions："+permissions);
            authorizationInfo.addRoles(roles);
			this.roles.put(account, authorizationInfo);
		}

		return authorizationInfo;
	}
	
	
	/**
	 * 1、CAS认证 ,验证用户身份
	 * 2、将用户基本信息设置到会话中
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        if(authc!=null){
            String account = (String) authc.getPrincipals().getPrimaryPrincipal();

            User user = userService.getUserByAccount(account);
            logger.debug("获取user设置到会话中："+user.toString());
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
        }

		return authc;
	}
	

}
