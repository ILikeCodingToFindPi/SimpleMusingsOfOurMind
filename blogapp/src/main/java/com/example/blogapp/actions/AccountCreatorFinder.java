package com.example.blogapp.actions;

import com.example.blogapp.models.Account;
import com.example.blogapp.models.Perms;
import com.example.blogapp.tempstorage.AccountRepository;

import com.example.blogapp.tempstorage.PermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AccountCreatorFinder {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final PermsRepository permsRepository;

    public Account save(Account account) {

        if (account.getId() == null) {
            if (account.getPerms().isEmpty()) {
                Set<Perms> perms = new HashSet<>();
                permsRepository.findById("ROLE_USER").ifPresent(perms::add);
                account.setPerms(perms);
            }
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findOneByEmailIgnoreCase(email);
    }

}
