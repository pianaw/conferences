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
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany(mappedBy = "presentation", cascade = {CascadeType.DETACH}, orphanRemoval = true)
    public Set<PresentationParticipants> participants;

    @OneToMany(mappedBy = "presentation", cascade = {CascadeType.DETACH}, orphanRemoval = true)
    public Set<Schedule> schedules;
}

