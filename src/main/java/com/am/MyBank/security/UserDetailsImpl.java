package com.am.MyBank.security;

import com.am.MyBank.model.Card;
import com.am.MyBank.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Data
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Card card;


    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserDetailsImpl(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhoneNumber(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getPassword(),
                user.getCard(),
                authorityList);
    }


    public UserDetailsImpl(Long id, String firstName, String lastName, String middleName, String phoneNumber, LocalDate dateOfBirth, String email, String password, Card card, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.card = card;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

