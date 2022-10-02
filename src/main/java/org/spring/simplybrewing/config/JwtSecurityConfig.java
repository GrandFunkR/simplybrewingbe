/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.config;

import org.spring.simplybrewing.authentication.JwtConfigurer;
import org.spring.simplybrewing.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * The type Jwt security config.
 */
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            /*"/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",*/
            // -- h2 database console
           /* "/h2-console/**",
            "/*.js",
            "/**",
            "/api/**",*/
            "/api/auth/login",
            "/api//auth/restore-pass",
             "/api//auth/sign-up",
              "/api//auth/request-pass",
               "/api//auth/sign-out",
                "/api//auth/refresh-token"
    };

    /**
     * Instantiates a new Jwt security config.
     *
     * @param tokenService the token service
     */
    @Autowired
    public JwtSecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Instantiates a new Jwt security config.
     *
     * @param tokenService    the token service
     * @param disableDefaults the disable defaults
     */
    public JwtSecurityConfig(TokenService tokenService, boolean disableDefaults) {
        super(disableDefaults);
        this.tokenService = tokenService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();

        // TODO enable csrf
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();

        http.apply(new JwtConfigurer(tokenService));

        http.cors().and();
    }

    /**
     * Cors configuration source cors configuration source.
     *
     * @return the cors configuration source
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
