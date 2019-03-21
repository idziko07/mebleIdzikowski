package pl.idzikowski.meble;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, role from user_role where username=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/carousel/**", "/css/**", "/img/**", "/js/**","/kitchen/**", "/producer/**", "/rooms/**","/wardrobes/**","/","/#info","/#offer","/#realizations","/#contact").permitAll()
				.antMatchers("img/**").permitAll()
				.antMatchers("/myPage").hasRole("ADMIN")
				.antMatchers("/upload").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/mylogin").permitAll()
				.failureUrl("/mylogin-error")
				.defaultSuccessUrl("/myPage/")
		    .and()
		    .logout()
		    	.logoutUrl("/logMeOut")
		    	.logoutSuccessUrl("/");
	}	 
}

