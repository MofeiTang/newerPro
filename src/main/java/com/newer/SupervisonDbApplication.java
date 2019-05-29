package com.newer;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.mysql.fabric.xmlrpc.base.Array;

@SpringBootApplication
@MapperScan("com.newer.**.mapper")
public class SupervisonDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupervisonDbApplication.class, args);
		
	}
}
