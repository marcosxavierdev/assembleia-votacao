package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.application.ports.in.usecases.CPFValidationUsecase;
import com.marcosxavier.assembleia.domain.dto.CPFValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CPFValidationService implements CPFValidationUsecase {

    private final String TOKEN = "17499|M5sYtgAI1BwE3olI6a2hx5xQFUYMBftG";
    private final String BASE_URL = "https://api.invertexto.com/v1/validator";

    public boolean consultaAPIValidaCPF(String cpf){

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?token=");
        urlBuilder.append(TOKEN);
        urlBuilder.append("&value=");
        urlBuilder.append(cpf);
        urlBuilder.append("&type=cpf");

        RestTemplate restTemplate =  new RestTemplate();
        HttpHeaders header =  new HttpHeaders();
        HttpEntity<?> entity =  new HttpEntity<>(header);

        String url = urlBuilder.toString();
        ResponseEntity<CPFValidationResponse> response = restTemplate.exchange(url, HttpMethod.GET,entity, CPFValidationResponse.class);
        var cpfValidado =  response.getBody();
        log.info(String.valueOf(cpfValidado));

        if(cpfValidado.getValid().equalsIgnoreCase("true")){
            log.info("true");
            return true;
        }
        log.info("false");
        return false;
    }
}
