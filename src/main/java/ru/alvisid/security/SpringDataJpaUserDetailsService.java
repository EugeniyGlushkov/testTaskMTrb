package ru.alvisid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.alvisid.repository.UserRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {
    private final UserRepository repository;
    @Autowired
    public SpringDataJpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        ru.alvisid.model.User user = this.repository.getByEmail(name);
        return new User(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.ROLE));
    }
}
