package com.example.blogapp.actions;

import com.example.blogapp.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AccountCreatorFinder accountCreatorFinder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountCreatorFinder.findOneByEmail(email);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Account not found");
        }
        Account account = optionalAccount.get();
        List<GrantedAuthority> grantedAuthorities = account.getPerms().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }
}
