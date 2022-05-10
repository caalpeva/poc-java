package team.boolbee.poc.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${team.boolbee.poc.jobs.location.images}")
	private String imageLocation;
	
	@Value("${team.boolbee.poc.jobs.location.files}")
	private String fileLocation;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + imageLocation);
		registry.addResourceHandler("/files/**").addResourceLocations("file:" + fileLocation);
		
	}
}
