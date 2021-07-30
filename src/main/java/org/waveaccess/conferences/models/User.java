package org.waveaccess.conferences.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @Column(unique = true)
    public String email;

    @Column(name = "hash_password")
    public String hashPassword;

    @Enumerated(value = EnumType.STRING)
    public Role role;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public Set<Presentation> presentations;

    public enum Role {
        ADMIN, PRESENTER, LISTENER
    }
}
