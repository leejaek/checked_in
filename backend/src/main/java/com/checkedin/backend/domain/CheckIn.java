package com.checkedin.backend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checkins")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckIn {

    @Id @GeneratedValue
    @Column(name = "checkin_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime checkinDate;

    @ManyToMany
    @JoinTable(name = "checkin_tag",
            joinColumns = @JoinColumn(name = "checkin_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    //== 생성 메서드 ==//
    public static CheckIn createCheckIn(Member member, Place place) {
        CheckIn checkIn = new CheckIn();
        checkIn.setMember(member);
        checkIn.setPlace(place);
        checkIn.setCheckinDate(LocalDateTime.now());

        return checkIn;
    }
}
