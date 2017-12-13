package com.spring.mybatis.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/common")
    public String common() {
        logger.info("进入普通界面");
        return "permission/common";
    }

    @RequestMapping("/admin")
    public String admin() {
        logger.info("进入管理界面");
        return "permission/admin";
    }

    @RequestMapping("/self")
    public String self() {
        logger.info("进入self界面");
        return "permission/self";
    }

}
