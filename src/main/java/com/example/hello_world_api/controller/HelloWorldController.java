package com.example.hello_world_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Hello World", description = "Simple Hello World API")
@RestController
public class HelloWorldController {

    @Operation(summary = "Get Hello World message")
    @GetMapping("/")
    public Map<String, String> helloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        return response;
    }
}
