package io.cherrytechnologies.pokemonwebclient.dto;

import java.util.Date;

public class Response<T> {
    public Date date = new Date();

    public T data;

    public Response(T data) {
        this.data = data;
    }
}
