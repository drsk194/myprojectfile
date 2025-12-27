package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class AccountStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "old_status")
    private String oldStatus;
    @Column(name = "new_status")
    private String newStatus;
    @Column(name = "changed_at")
    private LocalDateTime changedAt;
    @Column(name = "changed_by")
    private String changedBy;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOldStatus() {
        return oldStatus;
    }
    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }
    public String getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    public LocalDateTime getChangedAt() {
        return changedAt;
    }
    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
    public String getChangedBy() {
        return changedBy;
    }
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
    public AccountStatusLog() {
    }
    public AccountStatusLog(String oldStatus, String newStatus, LocalDateTime changedAt, String changedBy) {
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedAt = changedAt;
        this.changedBy = changedBy;
    }
    

}
