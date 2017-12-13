package com.spring.mybatis.shiro.factory;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Shiro权限资源配置拓展
 */
public class ShiroFilterChainDefinitionsBuilder {
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    private String filterChainDefinitions;

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    private Map<String, String> extendShiroFilterChainDefinitions() {
        // 配置结果
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        Ini ini = new Ini();
        //加载xml中的配置
        ini.load(filterChainDefinitions);
        //获取配置
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        shiroFilterFactoryBean.getFilterChainDefinitionMap();
        Set<Map.Entry<String, String>> entries = section.entrySet();
        // 放入结果中
        if (entries != null) {
            for (Map.Entry<String, String> entry : entries) {
                filterChainDefinitionMap.put(entry.getKey(), entry.getValue());
            }
        }
        // 获取数据库权限url配置 TODO
        loadFromDataBase(filterChainDefinitionMap);
        return filterChainDefinitionMap;
    }

    private void loadFromDataBase(Map<String, String> filterChainDefinitionMap) {
        filterChainDefinitionMap.put("/permission/common", "rolePermission[普通,管理员]");
        filterChainDefinitionMap.put("/permission/admin", "rolePermission[管理员]");
    }
}
