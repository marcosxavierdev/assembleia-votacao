package com.marcosxavier.assembleia.global.utils.validadorCPF;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ValidadorCPF {

    private final String TOKEN = "17499|M5sYtgAI1BwE3olI6a2hx5xQFUYMBftG";
    private final String BASE_URL = "https://api.invertexto.com/v1/validator";

    public boolean consulaAPIValidaCPF (String cpf){

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
        ResponseEntity<ValidadorCPFResponse> response = restTemplate.exchange(url, HttpMethod.GET,entity,ValidadorCPFResponse.class);
        var cpfValidado =  response.getBody();
        log.info(String.valueOf(cpfValidado));

        if(cpfValidado.getValid().equalsIgnoreCase("true")){
            log.info("true");
            return true;
        }
        log.info("false");
        return false;
    }



    public static boolean cpfValido(String cpf) {

        String pattern = "^[0-9\\-\\.]+$";
        if(!cpf.matches(pattern)){
            return false;
        }

        // Remove any formatting from the CPF string
        cpf = cpf.replaceAll("[^0-9]", "");

        // Check that the CPF has 11 digits
        if (cpf.length() != 11) {
            return false;
        }

        // Check that all the digits are the same
        for (int i = 0; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                break;
            }
            if (i == 10) {
                return false;
            }
        }

        // Calculate the first digit verifier
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit == 10 || firstDigit == 11) {
            firstDigit = 0;
        }

        // Calculate the second digit verifier
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit == 10 || secondDigit == 11) {
            secondDigit = 0;
        }

        // Check that the calculated verifiers match the ones in the CPF
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit
                || Character.getNumericValue(cpf.charAt(10)) != secondDigit) {
            return false;
        }

        return true;
    }
}
