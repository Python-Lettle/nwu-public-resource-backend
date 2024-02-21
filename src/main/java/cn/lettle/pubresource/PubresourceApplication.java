package cn.lettle.pubresource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("cn.lettle.pubresource.mapper")
public class PubresourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubresourceApplication.class, args);
	}

}
