package config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails user = userBuilder
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails admin = userBuilder
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails both = userBuilder
                .username("test")
                .password("test")
                .roles("USER", "ADMIN")
                .build();

        inMemoryUserDetailsManager.createUser(user);
        inMemoryUserDetailsManager.createUser(admin);
        inMemoryUserDetailsManager.createUser(both);

        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/statics/**", "/webjars/**", "/", "/vehicleList.html").permitAll()
                .antMatchers( "/vehicle.html").hasRole("USER")
                .antMatchers( "/vehicle**").hasRole("ADMIN")
                .anyRequest().authenticated();//każde żądanie ma być uwierzytelnione
        http
                .formLogin() //pozwól użytkownikom uwierzytelniać się poprzez formularz
                .loginPage("/login") //formularz logowania dostępny jest pod URL
                .permitAll(); //przyznaj dostęp wszystkim użytkownikom dla wszystkich URL powiązanych z logowaniem opartym na formularzu.
        http
                .logout() //pozwól wszystkim użykownikom się wylogować
                .permitAll();//
    }
}