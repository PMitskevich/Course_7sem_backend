package com.mitskevich.course_7sem.security;

import com.mitskevich.course_7sem.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public WebSecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/logout").authenticated()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/user/addAdmin").permitAll()
                .antMatchers(HttpMethod.GET, "/user/{\\w+}").authenticated()
                .antMatchers(HttpMethod.PUT, "/user/{\\w+}").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.DELETE, "/user/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers("/owner/addOwner").permitAll()
                .antMatchers(HttpMethod.GET, "/owner/{\\w+}").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.PUT, "/owner/{\\w+}").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.DELETE, "/owner/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers("/specialization/all").permitAll()
                .antMatchers(HttpMethod.POST, "/specialization/addSpecialization").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/specialization/{\\w+}").permitAll()
                .antMatchers(HttpMethod.PUT, "/specialization/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/specialization/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers("/doctor/all").permitAll()
                .antMatchers(HttpMethod.POST, "/doctor/addDoctor").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/doctor/{\\w+}").permitAll()
                .antMatchers(HttpMethod.PUT, "/doctor/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/doctor/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/appointment/addAppointment").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.GET, "/appointment/{\\w+}").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.GET, "/appointment/myAppointments/{\\w+}").hasAuthority(Role.USER.name())
                .antMatchers("/review/all").permitAll()
                .antMatchers("/review/user/{\\w+}").authenticated()
                .antMatchers("/review/addReview").authenticated()
                .antMatchers(HttpMethod.DELETE, "/review/{\\w+}").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/statistics/lineGraphNotes").hasAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }
}
