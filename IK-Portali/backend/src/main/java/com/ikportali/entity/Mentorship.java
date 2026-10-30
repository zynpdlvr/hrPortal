package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "mentorship")
@Check(constraints = "(status IN ('ACTIVE', 'ENDED', 'CANCELLED')) AND (ended_on IS NULL OR ended_on >= started_on)")
@Getter
@Setter
@NoArgsConstructor
public class Mentorship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentorship_id", nullable = false)
    private Long mentorshipId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentee_employee_id", nullable = false)
    private Employee menteeEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentor_employee_id", nullable = false)
    private Employee mentorEmployee;

    @Column(name = "started_on", nullable = false)
    private LocalDate startedOn;

    @Column(name = "ended_on", nullable = true)
    private LocalDate endedOn;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

}
