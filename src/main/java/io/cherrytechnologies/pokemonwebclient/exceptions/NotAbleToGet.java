package io.cherrytechnologies.pokemonwebclient.exceptions;

public class NotAbleToGet extends RuntimeException{
    public NotAbleToGet() {
        super();
    }

    public NotAbleToGet(String message) {
        super(message);
    }

    public NotAbleToGet(Throwable cause) {
        super(cause);
    }

    public NotAbleToGet(String message, Throwable cause) {
        super(message, cause);
    }
}
