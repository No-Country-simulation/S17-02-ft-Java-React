package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId=UUID.randomUUID();

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user-role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role>roles;
}
