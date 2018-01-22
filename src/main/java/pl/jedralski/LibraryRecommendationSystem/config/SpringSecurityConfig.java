package pl.jedralski.LibraryRecommendationSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


public class SpringSecurityConfig {

    private DataSource dataSource;

    @Autowired
    public SpringSecurityConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(
                        "select username, hash, 1 as active from users u where u.username = ?")
                .dataSource(dataSource);
    }


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/").hasAnyAuthority("user", "admin")
                .and().formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
                .usernameParameter("username").passwordParameter("password")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/access-denied");
    }

}


