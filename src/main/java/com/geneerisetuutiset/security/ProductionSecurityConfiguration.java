package com.geneerisetuutiset.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("production")
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
@Configuration
@EnableWebSecurity
public class ProductionSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Value("admin.password")
    private String adminPassword;
    @Value("user.password")
    private String userPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/").authenticated();
        http.formLogin()
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/news/{id}").permitAll()
                .antMatchers("/pictures/{id}").permitAll()
                .antMatchers("/news/category/{name}").permitAll()
                .antMatchers("/news/filtered/{filterBy}/{title}").permitAll()
                .antMatchers("/control").hasRole("ADMIN")
                .antMatchers("/news/new").hasRole("ADMIN")
                .antMatchers("/edit/{id}").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .formLogin().permitAll().and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("jack").password(userPassword).roles("USER");
        auth.inMemoryAuthentication()
                .withUser("will").password(adminPassword).roles("ADMIN");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
