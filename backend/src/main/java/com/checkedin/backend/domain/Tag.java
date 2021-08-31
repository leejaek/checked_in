package com.checkedin.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Tag {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Feeling feeling; // POSITIVE, NEGATIVE, NEUTRAL

    private String body;

    @ManyToMany(mappedBy = "tags")
    private List<CheckIn> checkIns = new ArrayList<>();

}
