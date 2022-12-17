package com.mhawi.slider.business.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Version
    /*
     The version is used to ensure integrity when
     performing the merge operation and for optimistic
     concurrency control.
    */
    private long version;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDateTime;

    @PrePersist
    /*
     it is used to annotate model methods to indicate that the
     method should be called before the entity is inserted (persisted)
     into the database
     */
    protected void onCreate() {
        createdDateTime = LocalDateTime.now();
    }

    @PreUpdate
    /*
    to annotate methods in models to indicate an
    operation that should be triggered before an
    entity has been updated in the database.
     */
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }
}
