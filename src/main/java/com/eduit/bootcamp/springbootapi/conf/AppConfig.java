package com.eduit.bootcamp.springbootapi.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eduit.bootcamp.springbootapi.controller.ProductController;
import com.eduit.bootcamp.springbootapi.controller.TokenController;
import com.eduit.bootcamp.springbootapi.controller.UserController;
import com.eduit.bootcamp.springbootapi.db.repository.UserRepository;
import com.eduit.bootcamp.springbootapi.service.JWTService;
import com.eduit.bootcamp.springbootapi.service.JWTServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationService;
import com.eduit.bootcamp.springbootapi.service.UserAdministrationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationService;
import com.eduit.bootcamp.springbootapi.service.UserAuthenticationServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserDetailServiceImpl;
import com.eduit.bootcamp.springbootapi.service.UserMapper;
import com.eduit.bootcamp.springbootapi.service.UserMapperImpl;
import com.eduit.bootcamp.springbootapi.service.utils.JwtTokenUtil;

@Configuration
public class AppConfig {

	@Bean
	public UserAdministrationService getUserAdministrationService(UserMapper userMapper, 
			UserRepository userRepository) {
		return new UserAdministrationServiceImpl(userMapper, userRepository);
	}

	@Bean
	public UserMapper getUserMapper(PasswordEncoder encoder) {
		return new UserMapperImpl(encoder);
	}
	
	@Bean
	public UserController getUserController(UserAdministrationService userAdministrationService) {
		return new UserController(userAdministrationService);
	}
	
	@Bean
	public TokenController getTokenController(UserAuthenticationService userAuthenticationService, 
			JWTService jwtService) {
		return new TokenController(userAuthenticationService, jwtService);
	}
	
	@Bean
	public ProductController getProductController() {
		return new ProductController();
	}

	@Bean
	public JWTService getJwtService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
		return new JWTServiceImpl(userDetailsService, jwtTokenUtil);
	}
	
	@Bean
	public UserAuthenticationService getUserAuthenticationService(PasswordEncoder passwordEncoder,
			JWTService jwtService, UserDetailsService userDetailsService) {
		return new UserAuthenticationServiceImpl(passwordEncoder, jwtService, userDetailsService);
	}
	
	@Bean
	public UserDetailsService getUserDetailService(UserRepository userRepository) {
		return new UserDetailServiceImpl(userRepository);
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
