package om.esi2.mini_projet;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "om.esi2.mini_projet")
@EntityScan(basePackages = "om.esi2.mini_projet.models")
@EnableJpaRepositories(basePackages = "om.esi2.mini_projet.repositories")
public class MiniProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProjetApplication.class, args);
	}

		@PostConstruct
	public void testEntityScan() {
		System.out.println("Entity scan OK");
	}
}
