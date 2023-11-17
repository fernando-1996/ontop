package com.ontop.challenge.model.base;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class ModelListener {

    @PrePersist
    public void prePersist(BaseModel baseModel) {
        baseModel.setDateCreated(LocalDateTime.now());
        baseModel.setDateUpdated(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(BaseModel baseModel) {
        baseModel.setDateUpdated(LocalDateTime.now());
    }
}
