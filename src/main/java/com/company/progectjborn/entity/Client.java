package com.company.progectjborn.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
@Entity
public class Client extends User {
    @InstanceName
    @DependsOnProperties({"firstName"})
    public String getInstanceName() {
        return String.format("%s", getFirstName());
    }
}