package com.homework.simpleidus.domain.entity.order;

import com.homework.simpleidus.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable = false, updatable = false)
    private String uuid;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private OffsetDateTime paidAt;
}
