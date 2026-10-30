package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "document_submission")
@Check(constraints = "(status IN ('SUBMITTED', 'APPROVED', 'REJECTED')) AND (size_bytes > 0)")
@Getter
@Setter
@NoArgsConstructor
public class DocumentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_submission_id", nullable = false)
    private Long documentSubmissionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_requirement_id", nullable = false)
    private EmployeeDocumentRequirement documentRequirement;

    @Column(name = "storage_key", nullable = false, length = 512)
    private String storageKey;

    @Column(name = "mime_type", nullable = false, length = 100)
    private String mimeType;

    @Column(name = "size_bytes", nullable = false)
    private Long sizeBytes;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "uploaded_at", nullable = false)
    private Instant uploadedAt;

    @Column(name = "review_note", nullable = true, length = 500)
    private String reviewNote;

}
