package com.login.login.services;

import com.login.login.repositories.RegisterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RegisterRepository registerRepository;

    public UserDetailsServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return registerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User dengan email " + email + " tidak ditemukan"));
    }
}
