package com.spring.mybatis.shiro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 角色权限认证过滤器
 */
public class RolePermissionAuthorizationFilter extends AuthorizationFilter {
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[]) o;
        if (rolesArray == null || rolesArray.length == 0) {
            logger.debug("未限制角色访问，有权限。。。");
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) {
                logger.debug(subject.getPrincipals().getPrimaryPrincipal() + "拥有的角色：" + rolesArray[i] + "，有权限访问");
                return true;
            }
        }
        logger.debug(subject.getPrincipals().getPrimaryPrincipal() + ",没有权限访问");
        return false;
    }
}
