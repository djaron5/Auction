package com.example.Auction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(exclude = "subscriptions")
@ToString(exclude = "subscriptions")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please enter your name")
//    @Length(max = 255, message = "Too long!")
//    @Length(min = 4, message = "Too short!")
    private String username;
    @NotBlank(message = "Please enter password")
//    @Length(max = 255, message = "Too long!")
//    @Length(min = 6, message = "Too short!")
    private String password;
    @Transient
//    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;
    private boolean active;
    private BigDecimal balance;
    @OneToOne
    private UserStatistic userStatistic;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Please enter email")
    private String email;
    private String activationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auction_participants",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "auction_id")}
    )
    private Set<Auction> subscriptions = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
}
