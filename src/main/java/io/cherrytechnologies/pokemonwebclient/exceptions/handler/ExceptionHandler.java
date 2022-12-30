package io.cherrytechnologies.pokemonwebclient.exceptions.handler;

import io.cherrytechnologies.pokemonwebclient.dto.Response;
import io.cherrytechnologies.pokemonwebclient.exceptions.NotAbleToGet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotAbleToGet.class)
    public ResponseEntity<Response<String>> handleOnNotAbleToGet(NotAbleToGet e){
        return ResponseEntity.internalServerError().body(new Response<>(e.getMessage()));
    }
}
