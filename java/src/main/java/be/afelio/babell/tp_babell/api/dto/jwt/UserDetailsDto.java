package be.afelio.babell.tp_babell.api.dto.jwt;

import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsDto implements UserDetails {

    private String username;
    private String password;

    public static UserDetailsDto from(PersonEntity person) {
        final UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.username = person.getEmail();
        userDetailsDto.password = person.getPassword();
        return userDetailsDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
