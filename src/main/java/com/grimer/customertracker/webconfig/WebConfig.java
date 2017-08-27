package com.grimer.customertracker.webconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan("com.grimer.customertracker")
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	
	 private static final String UTF8 = "UTF-8";

	 private ApplicationContext applicationContext;
		
	 @Override
	 public void setApplicationContext(ApplicationContext applicationContext) {
		 this.applicationContext = applicationContext;
	 }
	 
//	 @Bean
//	 public InternalResourceViewResolver resolver() {
//	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	      resolver.setViewClass(JstlView.class);
//	      resolver.setPrefix("/WEB-INF/JSPviews/");
//	      resolver.setSuffix(".jsp");
//	      return resolver;
//	 }
	
	 @Bean
	 public ViewResolver viewResolver() {
		 ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	     resolver.setTemplateEngine(templateEngine());
	     resolver.setCharacterEncoding(UTF8);
	     return resolver;
	 }

	 private TemplateEngine templateEngine() {
		 SpringTemplateEngine engine = new SpringTemplateEngine();
	     engine.setTemplateResolver(templateResolver());
	     engine.setTemplateEngineMessageSource(messageSource());
	     return engine;
	 }
	    
	 @Bean
	 @Description("Spring Message Resolver")
	 public ResourceBundleMessageSource messageSource() {
		 ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	     messageSource.setBasename("messages");
	     return messageSource;
	 }

	 private ITemplateResolver templateResolver() {
		 SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	     resolver.setApplicationContext(applicationContext);
	     resolver.setPrefix("/WEB-INF/views/");
	     resolver.setTemplateMode(TemplateMode.HTML);
	     return resolver;
	 }
	 
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	 }
}
