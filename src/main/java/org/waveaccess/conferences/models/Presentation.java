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
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "presentation_user",
            joinColumns = {@JoinColumn(name = "presentation_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")})
    public Set<User> participants;

    @OneToMany(mappedBy = "presentation", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    public Set<Schedule> schedules;
}
