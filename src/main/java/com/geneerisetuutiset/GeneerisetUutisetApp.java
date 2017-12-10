/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geneerisetuutiset;

import com.geneerisetuutiset.domain.Category;
import com.geneerisetuutiset.repositories.CategoryRepository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class GeneerisetUutisetApp {

    @Autowired
    private CategoryRepository categoryRepository;

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

//    @PostConstruct
//    public void categories() {
//        Category category1 = new Category();
//        Category category2 = new Category();
//        Category category3 = new Category();
//        Category category4 = new Category();
//        Category category5 = new Category();
//        Category category6 = new Category();
//        category1.setName("Vanhat");
//        category2.setName("Uudet");
//        category3.setName("Hyvät");
//        category4.setName("Huonot");
//        category5.setName("Jännät");
//        category6.setName("Tylsät");
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//        categoryRepository.save(category4);
//        categoryRepository.save(category5);
//        categoryRepository.save(category6);
//    }
}
