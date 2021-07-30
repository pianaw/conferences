package org.waveaccess.conferences.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    public Room room;

    @Temporal(value = TemporalType.DATE)
    public Date date;

    public Time start;

    public Time end;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "presentation_id")
    public Presentation presentation;

}
