package com.homework.simpleidus.domain.entity.order;

import com.homework.simpleidus.domain.entity.BaseEntity;
import com.homework.simpleidus.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
}
