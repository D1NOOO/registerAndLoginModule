package com.dino.registermodule;

import com.dino.registermodule.config.MyBatisMapperScannerConfig;
import com.dino.registermodule.config.RedisConfig;
import com.dino.registermodule.config.SpringMybatisConfig;
import com.dino.registermodule.config.Swagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.dino.registermodule.domain.entity", "com.dino.registermodule.domain.param", "com.dino.registermodule.domain.result",
//		"com.dino.registermodule.component","com.dino.registermodule.service",
//		"com.dino.registermodule.dao", "com.dino.registermodule.dao.sql","com.dino.registermodule.base","com.dino.registermodule.handler"})
@ImportAutoConfiguration(
		classes = {
				SpringMybatisConfig.class,//Spring MyBatis Config
				MyBatisMapperScannerConfig.class,//MyBatis Config
				RedisConfig.class
		}
)
public class RegisterModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterModuleApplication.class, args);
	}
}
