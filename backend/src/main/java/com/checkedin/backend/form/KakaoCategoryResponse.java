package com.checkedin.backend.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class KakaoCategoryResponse {

    public static class KakaoCategoryMeta {
        private KakaoSameName same_name;
        private int pageable_count;
        private int total_count;
        private boolean is_end;
    }

    private KakaoCategoryMeta meta;
    private List<KakaoPlaceForm> documents;
}

