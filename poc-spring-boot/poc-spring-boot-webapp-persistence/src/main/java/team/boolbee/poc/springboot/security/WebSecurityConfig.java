package team.boolbee.poc.springboot.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(
				"/bootstrap/**",
				"/css/**",
				"/images/**",
				"/js/**",
				"/tinymce/**",
				"/logos/**").permitAll()
			.antMatchers(
				"/",
				"/signup",
				"/search",
				"/jobs/detail/**",
				"/encode/**").permitAll()
			.antMatchers("/jobs/**").hasAnyAuthority("MANAGER","ADMINISTRATOR")
			.antMatchers("/categories/**").hasAnyAuthority("MANAGER","ADMINISTRATOR") 
			.antMatchers("/users/**").hasAnyAuthority("ADMINISTRATOR")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll();
			//.and().formLogin().permitAll(); // Formulario de login por defecto
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.jdbcAuthentication().dataSource(dataSource) // Configuración de tablas por defecto
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, CASE WHEN status = 'ACTIVE' THEN 1 ELSE 0 END AS enabled "
					+ " from USERS where username = ?")
			.authoritiesByUsernameQuery("select u.username, p.name from USER_PROFILES up "
					+ "inner join USERS u on up.userId = u.id "
					+ "inner join PROFILES p on up.profileId = p.id "
					+ "where u.username = ?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
