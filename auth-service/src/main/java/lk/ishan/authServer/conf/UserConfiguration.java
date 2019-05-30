package lk.ishan.authServer.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
	PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	public void init(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication().withUser("tharindu")
		.password(passwordEncoder.encode("tharindu"))
		.roles("ADMIN","USER")
		.authorities("CAN_CREATE","CAN_READ")
	   .and()
	   	.withUser("saman")
	   	.password(passwordEncoder.encode("tharindu"))
		.roles("USER")
		.authorities("CAN_CREATE","CAN_READ");
			
	}
	

}
