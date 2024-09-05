package com.nocountry.telemedicina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
<<<<<<< HEAD
public class User extends Auditable{
=======
public class User extends Auditable implements UserDetails {
>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();

    @Column(unique = true,nullable = false)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private ClinicalHistory clinicalHistory;
<<<<<<< HEAD
=======

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = this.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRoleName())).collect(Collectors.toList());
        return authorities;
    }
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;
>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338
}
