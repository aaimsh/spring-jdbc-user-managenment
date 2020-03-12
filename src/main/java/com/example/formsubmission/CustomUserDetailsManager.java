package com.example.formsubmission;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CustomUserDetailsManager extends JdbcUserDetailsManager {

    public CustomUserDetailsManager(DataSource dataSource) {
        super(dataSource);
    }

    public List<UserDetails> loadAllUsers() {
        List<String> usernames = getUserNames();
        List<UserDetails> users = new ArrayList<>();
        usernames.forEach(u -> users.add(this.loadUserByUsername(u)));
        return users;
    }

    public List<String> getUserNames() {
        return getJdbcTemplate().queryForList("select username from users", String.class);
    }

    public void updateDto(UserDto dto) {
       UserDetails details =  loadUserByUsername(dto.getUsername());

       List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
       dto.getAuthorities().forEach(s -> grantedAuthorities.add(new SimpleGrantedAuthority(s)));

       UserDetails updated = User.withUserDetails(details).authorities(grantedAuthorities).disabled(!dto.isEnabled()).build();
       updateUser(updated);
    }

}
