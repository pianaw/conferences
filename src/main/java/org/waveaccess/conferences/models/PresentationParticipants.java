package org.waveaccess.conferences.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;


@Entity
@Table(name = "presentation_user")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PresentationParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "presentation_id")
    public Presentation presentation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    public User user;
}
