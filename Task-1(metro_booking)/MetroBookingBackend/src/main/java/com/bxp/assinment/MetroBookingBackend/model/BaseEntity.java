package com.bxp.assinment.MetroBookingBackend.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    @Id
    private Long id;

    @Column(
            name = "created_at"
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;

    public BaseEntity() {
    }

    @PrePersist
    void createDate() {
        if (this.createdAt == null) {
            this.setCreatedAt(LocalDateTime.now());
        }

    }

    @PreUpdate
    void updatedAt() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && Hibernate.getClass(this) == Hibernate.getClass(o)) {
            BaseEntity that = (BaseEntity) o;
            return this.id != null && Objects.equals(this.id, that.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "BaseEntity(id=" + var10000 + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }

}
