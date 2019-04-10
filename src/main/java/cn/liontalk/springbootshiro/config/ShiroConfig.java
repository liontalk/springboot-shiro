package cn.liontalk.springbootshiro.config;


import cn.liontalk.springbootshiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * ApacheShiro核心通过Filter来实现权限控制和拦截 , 就好像SpringMVC通过DispachServlet来主控制请求分发一样 .
 * 既然是使用Filter , 即是通过URL规则来进行过滤和权限校验 , 所以我们需要定义一系列关于URL的规则和访问权限
 */
@Configuration
public class ShiroConfig {
    /**
     * DefaultAdvisorAutoProxyCreator , Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * ShiroFilterFactoryBean : 为了生成ShiroFilter , 处理拦截资源文件问题 .
     * 它主要保持了三项数据 , securityManager , filters , filterChainDefinitionManager .
     * 注意 : 单独一个ShiroFilterFactoryBean配置是或报错的 , 因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * FilterChain定义说明
     * 1 . 一个URL可以配置多个Filter , 使用逗号分隔
     * 2 . 当设置多个过滤器时 , 全部验证通过 , 才视为通过
     * 3 . 部分过滤器可指定参数 , 如perms , roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");//不设置默认找web工程根目录下的login.jsp页面
        shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功之后要跳转的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");//未授权跳转页面

        /* //自定义拦截器 , 多个filter的设置 */
//        Map<String, Filter> filters = new LinkedHashMap<>();
//        LogoutFilter logoutFilter = new LogoutFilter();//限制同一帐号同时在线的个数。或单点登录等
//        logoutFilter.setRedirectUrl("/login");
//        filters.put("logout",null);
//        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //filterChainDefinitionManager必须是LinkedHashMap因为它必须保证有序
        filterChainDefinitionMap.put("/css/**", "anon");//静态资源不要求权限 , 若有其他目录下文件(如js,img等)也依此设置

        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");//配置不需要权限访问的部分url

        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/user/**", "authc,roles[ROLE_USER]");//用户为ROLE_USER 角色可以访问 . 由用户角色控制用户行为 .
        filterChainDefinitionMap.put("/events/**", "authc,roles[ROLE_ADMIN]");
        //        filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试 , 固定写死的值 , 也可以从数据库或其他配置中读取 , 此处是用权限控制

        filterChainDefinitionMap.put("/**", "authc");//需要登录访问的资源 , 一般将/**放在最下边

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //region Cookie及Session
    // ==================== Cookie及Session管理 begin ====================
    private static final String COOKIE_NAME = "rememberMe";

    /**
     * cookie对象管理
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie(COOKIE_NAME);
        simpleCookie.setMaxAge(604800);//记住我cookie生效时间7天 ,单位秒
        return simpleCookie;
    }

    /**
     * cookie管理对象 : 记住我功能
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        return cookieRememberMeManager;
    }

    @Bean
    SessionDAO sessionDAO() {
        return new MemorySessionDAO();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        //listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
    // ==================== Cookie及Session管理 end ====================
    //endregion

    /**
     * SecurityManager : 核心安全事务管理器 , 权限管理
     * 这个类组合了登陆 , 登出 , 权限 , session的处理 . 是个比较重要的类 .
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm(null));
        securityManager.setCacheManager(ehCacheManager());////用户授权/认证信息Cache, 采用EhCache 缓存

        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * ShiroRealm , 这是个自定义的认证类 , 继承自AuthorizingRealm ,
     * 负责用户的认证和权限的处理 , 可以参考JdbcRealm的实现 .
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm(CredentialsMatcher matcher) {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(matcher);//密码校验实现
        return realm;
    }

    /**
     * EhCacheManager , 缓存管理
     * 用户登陆成功后 , 把用户信息和权限信息缓存起来 , 然后每次用户请求时 , 放入用户的session中 , 如果不设置这个bean , 每个请求都会查询一次数据库 .
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:config/ehcache.xml");//配置文件路径
        return em;
    }

    /**
     * LifecycleBeanPostProcessor , 这是个DestructionAwareBeanPostProcessor的子类 ,
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的 , 初始化和销毁 .
     * 主要是AuthorizingRealm类的子类 , 以及EhCacheManager类 .
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * HashedCredentialsMatcher , 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * AuthorizationAttributeSourceAdvisor , shiro里实现的Advisor类 ,
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法 .
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    //thymeleaf模板引擎和shiro整合时使用
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
}

