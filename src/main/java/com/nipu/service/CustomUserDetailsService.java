package com.nipu.service;

import com.nipu.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private com.nipu.repository.UserRepository UserRepository;

    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException
    {

        com.nipu.domain.User domainUser = UserRepository.findByEmail(login);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                domainUser.getEmail(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRoles())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles)
    {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
        return authList;
    }

    public List<String> getRoles(List<Role> roles)
    {

        List<String> rolesNames = new ArrayList<String>();
        for (Role role : roles)
        {
            rolesNames.add(role.getName());
        }
        return rolesNames;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> rolesNames)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String roleName : rolesNames)
        {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        return authorities;
    }

}