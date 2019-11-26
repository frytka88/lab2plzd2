package services;

import config.ProfileNames;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.RoleRepository;
import repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Profile(ProfileNames.DATABASE)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(models.User user) {
        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null); //wyzerowanie jest potrzebne ze względu na walidację
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean czyLoginJestUnikalny(String czyLoginJestUnikalny) {
        return userRepository.findByUsername(czyLoginJestUnikalny) == null;
    }

    @Override
    public void delete(Long id){userRepository.deleteById(id);}

    @Override
    public Page<models.User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<models.User> getAllUsersList() {
        return userRepository.findAll();
    }

    //bez adnotacji @Transactional sesja jest zamykana po wywołaniu findByUsername, co uniemożliwia dociągnięcie ról, gdyż fetch=EAGER.
    //ponadto, musi być włączone zarządzanie transakcjami @EnableTransactionManagement
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(models.User user) {
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream().
                map(r -> new SimpleGrantedAuthority(r.getType().toString())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }
}