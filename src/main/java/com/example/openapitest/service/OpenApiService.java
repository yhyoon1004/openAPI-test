package com.example.openapitest.service;

import com.example.openapitest.InfoOpenAPI;
import com.example.openapitest.ItemDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OpenApiService {

    RestTemplate restTemplate = new RestTemplate();

    public String cacheTest1() {

        String headerValue = CacheControl
                .maxAge(Duration.ofDays(1))
                .mustRevalidate()
                .getHeaderValue();

        System.out.println("headerValue = " + headerValue);

        return headerValue;
    }

    public ResponseEntity cacheTest2() {
        CacheControl cacheControl = CacheControl
                .maxAge(Duration.ofSeconds(20));
        return ResponseEntity.ok().cacheControl(cacheControl).body("_____\n"+UUID.randomUUID().toString()+"\n_____");
    }


    public List<ItemDTO> callAPI() throws ParseException {
        String reqURL = InfoOpenAPI.ENDPOINT+"?serviceKey="+InfoOpenAPI.DecKey+
                        "&pageNo=1"+"&numOfRows=12";

        String res = restTemplate.getForObject(reqURL, String.class);

//        return res;
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

        JSONParser jsonParser = new JSONParser();
        JSONObject res_JSON = (JSONObject) jsonParser.parse(res);
        JSONObject res_body_JSON = (JSONObject) res_JSON.get("body");
        JSONObject res_body_items_JSON = (JSONObject) res_body_JSON.get("items");
        JSONArray res_body_items_item_ARR = (JSONArray) res_body_items_JSON.get("item");

        List<ItemDTO> result = new ArrayList<>();
        for (Object o : res_body_items_item_ARR) {
            JSONObject item = (JSONObject) o;
            result.add(ItemDTO.jsonToDTO(item));
        }
        return result;

    }
}
