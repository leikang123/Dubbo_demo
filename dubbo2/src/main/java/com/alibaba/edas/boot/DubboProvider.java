package com.alibaba.edas.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 开发一个启动类
 * @author mac1094
 *
 */
@SpringBootApplication
public class DubboProvider {
	public static void main(String[] args) {
		SpringApplication.run(DubboProvider.class, args);
	}

}
