package com.cskaoyan.shiro;

import com.cskaoyan.domain.authority.SysPermission;
import com.cskaoyan.service.SuperService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 借助spring {@link FactoryBean} 对apache shiro的premission进行动态创建 动态的从数据库中读取权限信息
 *
 * @author wangt
 */
@Component
public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {

    public static int i;
    // shiro默认的链接定义 写在xml上的。
    private String filterChainDefinitions;

    @Autowired
    private SuperService superService;

    /**
     * 通过filterChainDefinitions对默认的链接过滤定义
     *
     * @param filterChainDefinitions 默认的接过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    @Override
    public Section getObject() throws BeansException {
        Ini ini = new Ini();
        // 加载默认的url
        ini.load(filterChainDefinitions);
        System.out.println(filterChainDefinitions);
        /*1加载类似以下的信息
          /login.do = authc
                /favicon.ico = anon
                /logout.do = logout
                /selectOption.do = roles[index]
                /index.jsp = perms[index:index]
        /testDwr.jsp = perms[index:testdwr]

                 2
                 循环数据库资源的url
                for (Resource resource : resourceDao.getAll()) {
        if(StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {
                section.put(resource.getValue(), resource.getPermission());
                }
                }
        加载数据库t_permission 的 value 和 permission组成类似1的格式 ，
        若要这样使用， permission 需要--->  perms[permission]
         */
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //查询数据库中所有的  路径对应需要的权限.
        try {
            List list = (List) superService.findAll("sysPermission");
            for (Object o : list) {
                SysPermission per = (SysPermission) o;

                //访问某一路径，需要对应的权限
                section.put(per.getUrl(), "perms[" + per.getPercode() + "]");
            }
            //section.put("/testDwr.jsp", "perms[index:testdwr]");///testDwr.jsp = perms[index:testdwr]
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*//因为顺序原因, 把/**放到最后
         *   [上面的配置覆盖下面的配置]
         *  把("/**", "authc") 放在  ("/testDwr.jsp", "perms[index:testdwr]")  上面,
         *  /testDwr.jsp 就只需要登录, 不需要perms[index:testdwr]权限了
         */
        section.put("/**", "authc");
        for (String s : section.keySet()) {
            System.out.println(s + "----" + section.get(s) + "-----------section");
        }
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return Section.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
