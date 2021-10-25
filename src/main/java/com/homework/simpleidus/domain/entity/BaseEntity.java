package com.homework.simpleidus.domain.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(nullable = false, columnDefinition = "bit", length = 1)
    private boolean deleted;

    public void delete() {
        this.deleted = true;
    }
}
