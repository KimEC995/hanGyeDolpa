package com.koreait.hanGyeDolpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestAPITestController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
	// 일단은 하드코딩 해놨는데, 나중에 서비스 배포시 반드시 암호화할 것
	private static final String KAKAO_API_KEY = "bba502ffca79940b6b67288936813ffd";
	
	 @GetMapping("/restTest")
	    public void restTest(Model model) {
	        try {
	            // URL 인코딩 -> 카카오API는 인코딩 필요X -> 한글 그대로 입력할 것!!!!
//	            String encodedQuery = URLEncoder.encode("클라이밍", StandardCharsets.UTF_8);
//	            log.info("Encoded Query: {}", encodedQuery);

	        	String getKeyword = "클라이밍";
	        	
	            // 요청 URL 구성
	            String requestUrl = KAKAO_API_URL +
	            		"?page=1" +
	            		"&size=15" +
	            		"&sort=accuracy" +
	                    "&query=" + getKeyword +
	                    "&y=37.511509999823645" +   // 중심 좌표 위도
	                    "&x=126.9172826071842" +   // 중심 좌표 경도
	                    "&radius=20000"; // 반경 거리(m)

	            log.info("Request URL: {}", requestUrl);

	            // HTTP 요청 헤더 설정
	            HttpHeaders headers = new HttpHeaders();
	            headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
//	            log.info("Authorization Header: {}", headers.get("Authorization"));

	            HttpEntity<Void> entity = new HttpEntity<>(headers);
	            log.info("curl -X GET \"{}\" -H \"Authorization: {}\"", requestUrl, headers.get("Authorization"));
	            
	            // API 호출
	            ResponseEntity<Map> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Map.class);
	            
	            log.info("Resp-> " + response.toString());
	            
	            // 응답 데이터에서 place_url 추출
	            try {
	                // 응답 데이터에서 "documents" 추출
	                List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");

	                if (documents != null && !documents.isEmpty()) {
	                    // place_name 추출
	                    List<String> placeNames = documents.stream()
	                            .map(doc -> (String) doc.get("place_name"))
	                            .filter(placeName -> placeName != null) // null 값 방지
	                            .collect(Collectors.toList());

	                    // 로그 출력
	                    placeNames.forEach(placeName -> log.info("Place Name: {}", placeName));
	                } else {
	                    log.info("No documents found in the response.");
	                }
	            } catch (ClassCastException e) {
	                log.error("Failed to cast response documents to List<Map<String, Object>>", e);
	            } catch (Exception e) {
	                log.error("An error occurred while processing the response", e);
	            }

	        } catch (Exception e) {
	            log.error("Error while calling Kakao API", e);
	        }
	    }
	}