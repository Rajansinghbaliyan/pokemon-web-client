package io.cherrytechnologies.pokemonwebclient.utils;

import org.springframework.data.domain.Sort;

public class BuildSort {
    public static Sort.Direction buildSort(SortOrder order) {
        return switch (order) {
            case asc:
                yield Sort.Direction.ASC;
            case dsc:
                yield Sort.Direction.DESC;
        };
    }
}
