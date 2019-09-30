package be.afelio.babell.tp_babell.api.service;

import be.afelio.babell.tp_babell.api.dto.CreatePersonDto;
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

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public PersonEntity save(CreatePersonDto person) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setEmail(person.getEmail());
        newPerson.setPassword(bcryptEncoder.encode(person.getPassword()));
        newPerson.setFirstname(person.getFirstname());
        newPerson.setLastname(person.getLastname());
        return personRepository.save(newPerson);
    }

}
