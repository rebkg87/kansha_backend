package com.example.kansha.controllers;

import com.example.kansha.dtos.UserResponse;
import com.example.kansha.models.User;
import com.example.kansha.services.AuthenticationService;
import com.example.kansha.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OAuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final RestTemplate restTemplate;

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/google/callback", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<UserResponse> googleCallback(@RequestParam("code") String code) {
        System.out.println("Received code: " + code);

        String accessToken =  exchangeCodeForAccessToken(code);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse("Unable to retrieve access token", null, null));
        }

        Map<String, Object> userAttributes = fetchUserAttributes(accessToken);
        if (userAttributes == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse("Unable to fetch user details", null,null));
        }

    UserResponse userResponse =  authenticationService.saveOrUpdateUser(userAttributes);

    return ResponseEntity.ok(userResponse);

    }

    private String exchangeCodeForAccessToken(String code){
        String url = "https://oauth2.googleapis.com/token";
        HttpHeaders headers =  new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "code", code,
                "redirect_uri", "http://localhost:4001/login/oauth2/code/google",
                "grant_type", "authorization_code"
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Map<String, String>>() {});
        System.out.println("Response Body: " + response.getBody());

        if (response.getBody() == null || !response.getBody().containsKey("access_token")){
            System.out.println("Error: la respuesta no contiene un access token válido");
            return null;
        }

        return response.getBody().get("access_token");

    }

    private Map<String, Object> fetchUserAttributes(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> greeting(@RequestParam(required = false, defaultValue = "World") String name){
        System.out.println("==== get greeting ====");
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
