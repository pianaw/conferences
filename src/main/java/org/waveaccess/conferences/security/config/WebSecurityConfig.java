package org.waveaccess.conferences.security.config;


import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.waveaccess.conferences.security.jwt.auth.JwtAuthenticationProvider;
import org.waveaccess.conferences.security.jwt.filters.AuthAccessTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Algorithm algorithm;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers(HttpMethod.PUT,"/api/schedule/presentations/*").hasAuthority("PRESENTER")
//                .antMatchers(HttpMethod.GET,"/api/schedule/presentations").hasAnyAuthority("PRESENTER", "LISTENER")
//                .antMatchers(HttpMethod.DELETE,"/api/schedule/presentations/*").hasAuthority("PRESENTER")
//                .antMatchers(HttpMethod.PUT, "/api/schedule/{id}").hasAnyAuthority("PRESENTER")
//                .antMatchers(HttpMethod.GET,"/api/users").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/users").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/users").hasAuthority("ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterAfter(authenticationJwtTokenFilter(), BasicAuthenticationFilter.class);

    }

    @Bean
    public AuthAccessTokenFilter authenticationJwtTokenFilter() {
        return new AuthAccessTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    @Bean(name="myAuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
