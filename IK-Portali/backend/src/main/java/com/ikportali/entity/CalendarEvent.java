package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "calendar_event")
@Check(constraints = "(event_type IN ('TRAINING', 'MONTH_2', 'MONTH_6', 'GENERAL')) AND (status IN ('PLANNED', 'COMPLETED', 'CANCELLED')) AND (ends_at > starts_at)")
@Getter
@Setter
@NoArgsConstructor
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_event_id", nullable = false)
    private Long calendarEventId;

    @Column(name = "title", nullable = false, length = 160)
    private String title;

    @Column(name = "event_type", nullable = false, length = 30)
    private String eventType;

    @Column(name = "starts_at", nullable = false)
    private Instant startsAt;

    @Column(name = "ends_at", nullable = false)
    private Instant endsAt;

    @Column(name = "location", nullable = true, length = 255)
    private String location;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "organizer_employee_id", nullable = true)
    private Employee organizerEmployee;

}
