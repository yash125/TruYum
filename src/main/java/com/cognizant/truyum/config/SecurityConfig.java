package com.cognizant.truyum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @throws Exception
     * http configure
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enabling basic http security and authorizing user and admin for token
        http.csrf().disable()
        //dont authenticate this request
        .authorizeRequests().antMatchers("/getToken").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
        // make sure we use stateless session; session won't be used to
		// store user's state.
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Add a filter to validate the tokens with every request
        http.addFilter(new JwtAuthFilter(authenticationManager()));
    }

    /**
     * @throws Exception
     * auth configure
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
            .and()
            .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }

    /**
     * Password Encoder
     * @return BCrypt password encoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
