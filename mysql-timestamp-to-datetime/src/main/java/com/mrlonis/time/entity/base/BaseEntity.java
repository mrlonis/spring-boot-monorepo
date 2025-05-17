package com.mrlonis.time.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity<T> {
    /**
     * Marked with {@link Nullable} is technically null when creating a new entity prior to persisting it. This field is
     * always created for us by the database, so it is not set by the application. After creation, the ID field will be
     * non-null.
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable private Long id;

    @Column(name = "NAME", nullable = false)
    @NonNull private String name;

    @Column(name = "TYPE", nullable = false, columnDefinition = "char(4)")
    @NonNull private String type;

    @Column(name = "CODE", columnDefinition = "char(4)")
    @Nullable private String code;

    @Column(name = "DATA")
    @Nullable private String data;

    /**
     * Marked with {@link Nullable} is technically null when creating a new entity prior to persisting it. This field is
     * always created for us by the database, so it is not set by the application. After creation, the ID field will be
     * non-null.
     */
    @Column(name = "CREATED_DATETIME", nullable = false, insertable = false, updatable = false)
    @CreationTimestamp
    @Nullable private T createdDatetime;

    @Column(name = "CREATED_USER", nullable = false, updatable = false)
    @NonNull private String createdUser;

    /**
     * Marked with {@link Nullable} is technically null when creating a new entity prior to persisting it. This field is
     * always created for us by the database, so it is not set by the application. After creation, the ID field will be
     * non-null.
     */
    @Column(name = "UPDATED_DATETIME", nullable = false, insertable = false)
    @UpdateTimestamp
    @Nullable private T updatedDatetime;

    @Column(name = "UPDATED_USER", nullable = false)
    @NonNull private String updatedUser;
}
