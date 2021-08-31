package com.checkedin.backend.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoPlaceForm implements Comparable {
    private String id;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String place_name;
    private int distance;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String x;
    private String y;
    private String place_url;

    @Override
    public int compareTo(Object o) {
        if (o instanceof KakaoPlaceForm) {
            KakaoPlaceForm kakaoPlaceForm = (KakaoPlaceForm) o;
            return this.distance < kakaoPlaceForm.distance ? -1 : 0;
        } else return -1;
    }
}
