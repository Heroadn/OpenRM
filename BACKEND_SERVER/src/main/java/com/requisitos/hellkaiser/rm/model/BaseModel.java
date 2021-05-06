package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@MappedSuperclass
public class BaseModel extends ResourceSupport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT")
    public long ID;

    @Transient
    @JsonIgnore
    private String[] ignored;

    @JsonProperty("ID")
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String[] getIgnored() {
        return ignored;
    }

    public void setIgnored(String... ignored) {
        this.ignored = ignored;
    }
}
