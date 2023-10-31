package com.datingacademia.datingacademia.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {
    @GetMapping("/")
    public String home(){
        return "login";
    }

    //카카오 인가 코드 리턴받은 주소
    @GetMapping("/auth/kakao/callback") //Data를 리턴해주는 컨트롤러 함수
    @ResponseBody
    public String kakaoCallback(String code) {
        //HttpHeader 오브젝트 생성
        RestTemplate rt = new RestTemplate(); //HTTP 요청 라이브러리
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "d09f0231bc26e125ec67ac84c83587be");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);
        //values 값들은 변수화 시켜서 사용하는게 좋다? -> application.yml 에서 저장한 것을 갖고와야 함

        //HttpHeader와 HttpBody 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers); //엔티티 완성

        //Http 요청하기 - POST
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, //요청 메소드
                kakaoTokenRequest,
                String.class //응답 받을 데이터의 값
        );


        return "카카오 인증 완료. 토큰 요청에 대한 응답 : " + response;
    }
}
