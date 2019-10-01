package be.afelio.babell.tp_babell.api.service;


import be.afelio.babell.tp_babell.api.dto.jwt.UserDetailsDto;
import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import be.afelio.babell.tp_babell.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       return Optional.ofNullable(email)
               .map(personRepository::findOneByEmail)
               .map(UserDetailsDto::from)
               .orElseThrow(() -> new IllegalStateException("Aucun utilisateur correspondant."));
    }

}
