package com.checkedin.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Place {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String kakaoPlaceId;

    private String name;

    private String roadAddressName;

    private String x;

    private String y;

    @OneToMany(mappedBy = "place")
    private List<CheckIn> checkins = new ArrayList<>();


}
