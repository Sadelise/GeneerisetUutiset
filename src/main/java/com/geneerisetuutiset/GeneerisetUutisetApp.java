/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geneerisetuutiset;

import com.geneerisetuutiset.domain.Account;
import com.geneerisetuutiset.domain.Category;
import com.geneerisetuutiset.domain.Role;
import com.geneerisetuutiset.repositories.AccountRepository;
import com.geneerisetuutiset.repositories.CategoryRepository;
import com.geneerisetuutiset.repositories.RoleRepository;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

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
        Role role = new Role();
        role.setRole("ADMIN");
        roleRepository.save(role);
        Role role2 = new Role();
        role2.setRole("USER");
        roleRepository.save(role2);
        Account account = new Account();
        account.setUsername("will");
        account.setPassword("adminadmin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);
        Account account2 = new Account();
        account2.setUsername("jack");
        account2.setPassword("useruser");
        List<Role> roles2 = new ArrayList<>();
        roles2.add(role2);
        account2.setRoles(roles2);
        accountRepository.save(account2);

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
