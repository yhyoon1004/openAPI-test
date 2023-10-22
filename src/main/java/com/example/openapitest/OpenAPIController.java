package com.example.openapitest;

import com.example.openapitest.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/**")
@RequiredArgsConstructor
public class OpenAPIController {

    private final OpenApiService openApiService;

    @GetMapping("test")
    @ResponseBody
    public List<ItemDTO> test() throws UnsupportedEncodingException, ParseException {
        return openApiService.callAPI();
    }

    @GetMapping("cache")
    @ResponseBody
    public String cache() {
        return openApiService.cacheTest1();
    }

    @GetMapping("cache/test")
    @ResponseBody
    public ResponseEntity cachingTest() {
        return openApiService.cacheTest2();
    }

}
