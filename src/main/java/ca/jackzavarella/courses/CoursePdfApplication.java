package ca.jackzavarella.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class CoursePdfApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CoursePdfApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter("CourseApi.pid"));
	    springApplication.run(args);
	}
}
