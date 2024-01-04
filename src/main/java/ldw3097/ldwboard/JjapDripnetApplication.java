package ldw3097.ldwboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class JjapDripnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(JjapDripnetApplication.class, args);
	}

}
