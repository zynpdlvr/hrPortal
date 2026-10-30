package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "training_session")
@Check(constraints = "(status IN ('DRAFT', 'SCHEDULED', 'HELD', 'CANCELLED')) AND (capacity BETWEEN 1 AND 32767)")
@Getter
@Setter
@NoArgsConstructor
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_session_id", nullable = false)
    private Long trainingSessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "calendar_event_id", nullable = true, unique = true)
    private CalendarEvent calendarEvent;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "capacity", nullable = true)
    private Short capacity;

}
