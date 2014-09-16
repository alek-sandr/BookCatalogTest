package com.testexercise.bookcatalog.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 3, max = 30)
    @Column(name = "LOGIN", unique = true, nullable = false, length = 30)
    private String login;

    @NotBlank
    @Length(min = 8, max = 30)
    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean admin = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>(3);
        roles.add(Role.ROLE_USER);
        if (admin) roles.add(Role.ROLE_ADMIN);
        return roles;
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

    private static class Role implements GrantedAuthority {
        public static final Role ROLE_USER = new Role("ROLE_USER");
        public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");

        private String role;

        private Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        @Override
        public String getAuthority() {
            return role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Role role1 = (Role) o;

            if (!role.equals(role1.role)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return role.hashCode();
        }
    }
}
