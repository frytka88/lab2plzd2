package services;

import config.ProfileNames;
import models.Role;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.RoleRepository;
import repositories.UserRepository;

import java.util.Set;


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
    public void save(User user) {
        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        user.setRoles((Set<Role>) userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);
        user.setEnabled(false);
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean czyLoginJestUnikalny(String czyLoginJestUnikalny) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
