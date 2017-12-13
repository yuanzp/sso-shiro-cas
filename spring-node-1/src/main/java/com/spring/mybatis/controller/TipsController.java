package com.spring.mybatis.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 相关提示
 */
@Controller
@RequestMapping("/tips")
public class TipsController {

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/403")
    public String code403() {
        logger.info("没有权限");
        return "tips/403";
    }

    @RequestMapping("/404")
    public String code404() {
        logger.debug("界面没找到");
        return "tips/404";
    }

}
