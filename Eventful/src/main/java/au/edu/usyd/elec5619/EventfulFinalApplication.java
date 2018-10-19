package au.edu.usyd.elec5619;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EventfulFinalApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EventfulFinalApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EventfulFinalApplication.class, args);
	}
	
}
