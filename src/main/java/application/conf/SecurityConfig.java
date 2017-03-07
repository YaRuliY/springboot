package application.conf;
import application.security.UDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UDService udService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/main").hasRole("USER")
                .antMatchers("/add").hasRole("USER")
                .antMatchers("/edit").hasRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/failure")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/main")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=success")
                .and();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder amb) throws Exception {
        amb.userDetailsService(udService);
        amb.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UDService());
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return new UDService();
    }
}