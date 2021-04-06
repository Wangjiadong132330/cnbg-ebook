package com.cnbg.zs.ebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cnbg.zs.ebook.api.mapper")
public class EbookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbookApiApplication.class, args);
	}

}
