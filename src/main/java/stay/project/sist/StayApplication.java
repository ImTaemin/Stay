package stay.project.sist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"stay.data.*", "stay.project.sist"})
@MapperScan("stay.data.*")
public class StayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayApplication.class, args);
	}

}