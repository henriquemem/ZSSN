package br.com.zg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication
@ComponentScan(basePackages={"br.com.zg"})
@EntityScan(basePackages={"br.com.zg"})
public class ZssnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZssnApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new OpenEntityManagerInViewFilter());
	    registration.addUrlPatterns("/*");
	    registration.setName("openEntityManagerInViewFilter");
	    registration.setOrder(1);
	    return registration;
	} 
}
