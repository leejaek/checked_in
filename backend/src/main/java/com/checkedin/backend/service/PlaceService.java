package com.checkedin.backend.service;

import com.checkedin.backend.form.KakaoCategoryResponse;
import com.checkedin.backend.form.KakaoPlaceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    @Autowired
    private Environment environment;

    private final String KAKAO_CAFE_CODE = "CE7";
    private final String KAKAO_RESTAURANT_CODE = "FD6";

    private static String mapToUrlParam(Map<String, String> params) {
        StringBuilder paramData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (paramData.length() != 0) {
                paramData.append('&');
            }
            paramData.append(param.getKey());
            paramData.append('=');
            paramData.append(String.valueOf(param.getValue()));
        }
        return paramData.toString();
    }

    public List<KakaoPlaceForm> GetKakaoPlace(String x, String y, String radius) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = environment.getProperty("app.kakao.host") + "/v2/local/search/category.json";

        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + environment.getProperty("app.kakao.apitoken"));

        Map<String, String> params = new HashMap<>();
        params.put("x", x);
        params.put("y", y);
        params.put("radius", radius);
//        params.put("sort", "distance");

        HttpEntity entity = new HttpEntity(headers);

        // Restaurant 요청
        ResponseEntity<KakaoCategoryResponse> restaurantResponse;
        params.put("category_group_code", KAKAO_RESTAURANT_CODE);

        restaurantResponse = restTemplate.exchange(
                uri + "?" + mapToUrlParam(params),
                HttpMethod.GET,
                entity,
                KakaoCategoryResponse.class
        );

        KakaoCategoryResponse restaurantResponseBody = restaurantResponse.getBody();
        List<KakaoPlaceForm> restaurantList = restaurantResponseBody.getDocuments();

        // Cafe 요청
        ResponseEntity<KakaoCategoryResponse> cafeResponse;
        params.put("category_group_code", KAKAO_CAFE_CODE);

        cafeResponse = restTemplate.exchange(
                uri + "?" + mapToUrlParam(params),
                HttpMethod.GET,
                entity,
                KakaoCategoryResponse.class
        );

        KakaoCategoryResponse cafeResponseBody = cafeResponse.getBody();
        List<KakaoPlaceForm> cafeList = cafeResponseBody.getDocuments();

        List<KakaoPlaceForm> resultList = Stream.concat(restaurantList.stream(), cafeList.stream()).collect(Collectors.toList());

        Collections.sort(resultList);

        return resultList;
    }
}
