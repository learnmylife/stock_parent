package com.itsun.stock.security.config;

//import com.itsun.stock.security.filter.MyAuthenticationFilter;
import com.itsun.stock.security.filter.MyAuthenticationFilter;
import com.itsun.stock.security.filter.MyUserNamePasswordAuthenticationFilter;
//import com.itsun.stock.security.handler.MyAccessDeniedHandler;
import com.itsun.stock.security.filter.MyUserNamePasswordAuthenticationFilter;
import com.itsun.stock.security.handler.StockAccessDeniedHandler;
import com.itsun.stock.security.handler.StockAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author by itheima
 * @Date 2022/1/22
 * @Description
 */
@Configuration
@EnableWebSecurity//开启webSecurity安全设置生效
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security pre/post注解的使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 密码加密器
     * BCryptPasswordEncoder方法采用SHA-256对密码进行加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 定义公共的无需被拦截的资源
     * @return
     */
    private String[] getPubPath(){
        //公共访问资源
        String[] urls = {
                "/**/*.css","/**/*.js","/favicon.ico","/doc.html",
                "/druid/**","/webjars/**","/v2/api-docs","/api/captcha",
                "/swagger/**","/swagger-resources/**","/swagger-ui.html"
        };
        return urls;
    }

    /**
     * 定义用户认证和授权的信息
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登出功能
        http.logout().logoutUrl("/api/logout").invalidateHttpSession(true);
        //开启允许iframe 嵌套。security默认禁用ifram跨域与缓存
        http.headers().frameOptions().disable().cacheControl().disable();
        //session禁用
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();//禁用跨站请求伪造
        http.authorizeRequests()//对资源进行认证处理
                .antMatchers(getPubPath()).permitAll()//公共资源都允许访问
                .anyRequest().authenticated();  //除了上述资源外，其它资源，只有 认证通过后，才能有权访问
        //自定义的过滤器
        http.addFilterBefore(myUserNamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(myAuthenticationFilter(), MyUserNamePasswordAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedHandler(new StockAccessDeniedHandler())
                .authenticationEntryPoint(new StockAuthentication());
    }


    /**
     * 定义登录拦截的bean
     * @return
     */
    @Bean
    public MyUserNamePasswordAuthenticationFilter myUserNamePasswordAuthenticationFilter() throws Exception {
        //构造器中传入的是指定的默认登录地址
        MyUserNamePasswordAuthenticationFilter myLoginFilter = new MyUserNamePasswordAuthenticationFilter("/api/login");
        myLoginFilter.setAuthenticationManager(authenticationManagerBean());
        myLoginFilter.setRedisTemplate(redisTemplate);
        return myLoginFilter;
    }
    @Bean
    public MyAuthenticationFilter myAuthenticationFilter(){
        return new MyAuthenticationFilter();
    }



}
