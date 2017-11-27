/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geneerisetuutiset;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class GeneerisetUutisetApp {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(GeneerisetUutisetApp.class, args);
    }

    @Configuration
    @Profile("production")
    public class ProductionProfile {

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }
    }
}
