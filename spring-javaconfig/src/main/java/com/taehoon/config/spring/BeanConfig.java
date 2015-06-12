package com.taehoon.config.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class BeanConfig {
//	@Value("${view.path}")
//    private String viewPath;
//	@Value("${view.execute}")
//	private String viewExeute;
//	
//	@Value("${jdbc.driverClassName}")
//	private String jdbcDriverClassName;
// 
//    @Value("${jdbc.url}")
//    private String jdbcUrl;
// 
//    @Value("${jdbc.username}")
//    private String jdbcUsername;
// 
//    @Value("${jdbc.password}")
//    private String jdbcPassword;
// 
//    private static final String APP_CONFIG_FILE_PATH = "/resources/application.xml";
 
    /**
     * 프로퍼티 홀더는 다른 빈들이 사용하는 프로퍼티들을 로딩하기 때문에, static 메소드로 실행된다.
     * 다른 일반 빈들이 만들어지기전에 먼저 만들어져야 한다.
     * @return
     */
//    @Bean
//    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer()
//    {
//        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//        ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
//        return ppc;
//    }
 
//    @Bean
//    public DataSource dataSource()
//    {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(this.jdbcDriverClassName);
//        dataSource.setUrl(this.jdbcUrl);
//        dataSource.setUsername(this.jdbcUsername);
//        dataSource.setPassword(this.jdbcPassword);
//        return dataSource;
//    }
    
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
