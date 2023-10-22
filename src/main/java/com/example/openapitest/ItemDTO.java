package com.example.openapitest;

import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
@Builder
public class ItemDTO {

    private String keyword;
    private String contents;

    public static ItemDTO jsonToDTO(JSONObject item) {
        return ItemDTO
                .builder()
                .keyword((String) item.get("keyword"))
                .contents((String) item.get("contents"))
                .build();
    }
}
