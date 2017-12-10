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

    @PostConstruct
    public void categories() {
        if (categoryRepository.findByName("Vanhat") == null) {
            Category category1 = new Category();
            category1.setName("Vanhat");
            categoryRepository.save(category1);
        }
        if (categoryRepository.findByName("Uudet") == null) {
            Category category1 = new Category();
            category1.setName("Uudet");
            categoryRepository.save(category1);
        }
        if (categoryRepository.findByName("Hyvät") == null) {
            Category category1 = new Category();
            category1.setName("Hyvät");
            categoryRepository.save(category1);
        }
        if (categoryRepository.findByName("Huonot") == null) {
            Category category1 = new Category();
            category1.setName("Huonot");
            categoryRepository.save(category1);
        }
        if (categoryRepository.findByName("Jännät") == null) {
            Category category1 = new Category();
            category1.setName("Jännät");
            categoryRepository.save(category1);
        }
        if (categoryRepository.findByName("Tylsät") == null) {
            Category category1 = new Category();
            category1.setName("Tylsät");
            categoryRepository.save(category1);
        }
    }
}
