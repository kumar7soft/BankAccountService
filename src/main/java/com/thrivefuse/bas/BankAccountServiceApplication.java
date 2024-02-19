package com.thrivefuse.bas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy

public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

}
