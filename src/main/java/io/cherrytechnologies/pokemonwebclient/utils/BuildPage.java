package io.cherrytechnologies.pokemonwebclient.utils;

public class BuildPage {
    public static int pageNumber(int pageSize, int offset){
        return (int) Math.floor(offset/pageSize);
    }
}
