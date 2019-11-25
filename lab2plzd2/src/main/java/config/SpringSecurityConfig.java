package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Profile({ProfileNames.DATABASE}) //, ProfileNames.INMEMORY
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile(ProfileNames.INMEMORY)
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = userBuilder
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails test = userBuilder
                .username("test")
                .password(passwordEncoder.encode("test"))
                .roles("USER", "ADMIN")
                .build();

        manager.createUser(user);
        manager.createUser(admin);
        manager.createUser(test);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/statics/**", "/webjars/**", "/", "/vehicleList.html", "/registerForm.html").permitAll()
                .antMatchers( "/vehicle.html").hasRole("USER")
                .antMatchers( "/vehicle**").hasRole("ADMIN")
                .anyRequest().authenticated(); //każde żądanie ma być uwierzytelnione
        http
                .formLogin()//pozwól użytkownikom uwierzytelniać się poprzez formularz
                .loginPage("/login")//formularz logowania dostępny jest pod URL
                .permitAll();//przyznaj dostęp wszystkim użytkownikom dla wszystkich URL powiązanych z logowaniem opartym na formularzu.
        http
                .logout() //pozwól wszystkim użykownikom się wylogować
                .permitAll();//
    }
}


