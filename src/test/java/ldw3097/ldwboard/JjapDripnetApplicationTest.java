package ldw3097.ldwboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class JjapDripnetApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(JjapDripnetApplicationTest.class, args);
	}

}
