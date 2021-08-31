package com.checkedin.backend.controller;

import com.checkedin.backend.form.KakaoPlaceForm;
import com.checkedin.backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    private final static String CHECKIN_RADIUS = "50";
    private final static String NEARBY_RADIUS = "500";

    @GetMapping("/places/nearby")
    public ResponseEntity<List<KakaoPlaceForm>> getNearbyPlaces(String x, String y) throws URISyntaxException {
        List<KakaoPlaceForm> kakaoPlaceForms = placeService.GetKakaoPlace(x, y, NEARBY_RADIUS);

        System.out.println(kakaoPlaceForms);
        return ResponseEntity.ok(kakaoPlaceForms);
    }
}
