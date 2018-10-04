package com.acme.acmetrade.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()              
                .antMatchers(HttpMethod.GET, "/securities/**").permitAll()   
                .antMatchers(HttpMethod.GET, "/sectors/**").permitAll()
                .antMatchers(HttpMethod.GET, "/traders/**").permitAll()
                .antMatchers(HttpMethod.GET, "/orders/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/securities/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/sectors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/traders/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/orders/**").hasRole("ADMIN")
     	        .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable()
            .headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()               
                .withUser("bill").password("bill").roles("USER")
                .and()
                .withUser("ram").password("ram").roles("USER")
                .and()
                .withUser("justin").password("justin").roles("USER")
                .and()
                .withUser("lalit").password("lalit").roles("USER")
                .and()
                .withUser("florence").password("florence").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("USER", "ADMIN");
    }
}

   