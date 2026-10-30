package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "event_participant", uniqueConstraints = {@UniqueConstraint(columnNames = {"calendar_event_id", "employee_id"})})
@Check(constraints = "(response_status IN ('INVITED', 'ACCEPTED', 'DECLINED'))")
@Getter
@Setter
@NoArgsConstructor
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_participant_id", nullable = false)
    private Long eventParticipantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "calendar_event_id", nullable = false)
    private CalendarEvent calendarEvent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "response_status", nullable = false, length = 20)
    private String responseStatus;

}
