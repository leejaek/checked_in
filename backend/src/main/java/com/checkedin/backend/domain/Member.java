package com.checkedin.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    private URL profile;

    @OneToMany(mappedBy = "member")
    private List<CheckIn> checkins = new ArrayList<>();

}
