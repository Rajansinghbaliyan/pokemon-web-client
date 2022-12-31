package io.cherrytechnologies.pokemonwebclient.service.stream_practise.reduce;

import io.cherrytechnologies.pokemonwebclient.dto.TypeDto;
import io.cherrytechnologies.pokemonwebclient.enums.PokemonTypes;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ReduceService {

    @Autowired
    private PokemonService service;

    /*
    int sum = numbers.stream().reduce(0, (a, b) -> a + b);

    reduce takes two arguments:
    1. An initial value, here 0.
    2. A BinaryOperator<T> to combine two elements and produce a new value; here
        you use the lambda (a, b) -> a + b.


    PdfPTable myTable =
    Stream.of(Labels.ITEM_NAME,Labels.QUANTITY,Labels.PRICE)
          .map(s -> new PdfPCell(new Phrase(s)))
          .collect(() -> new PdfPTable(new float[]{ 100.0f }), // supplier
                   (table, cell) -> table.addCell(cell), // accumulator
                   (table1,table2) -> table1.addAllCells(table2.getCells())); // combiner
    */

    public TypeAndCountDto typeAndCount(PokemonTypes typeEnum) {
        log.debug(String.format("Class Name: %s, getting the types and count", getClass().getName()));
        return service
                .findAll("id", "asc")
                .stream()
                .flatMap(pokemonDto -> pokemonDto.getTypes().stream())
                .map(TypeDto::getName)
                .filter(type -> type.equals(typeEnum.toString()))
                .collect(() -> new TypeAndCountDto(typeEnum.toString(), 0),
                        (typeAndCount, typesName) ->
                                typeAndCount.setCount(typeAndCount.count + 1),
                        (type1, type2) ->
                                type1.setCount(type2.count)
                );
    }

    public TypeAndCountDto typeAndCountSimple(PokemonTypes typeEnum) {
        log.debug(String.format("Class Name: %s, getting the types and count using simple", getClass().getName()));
        long count = service
                .findAll("id", "asc")
                .stream()
                .flatMap(pokemonDto -> pokemonDto.getTypes().stream())
                .map(TypeDto::getName)
                .filter(type -> type.equals(typeEnum.toString()))
                .count();

        return TypeAndCountDto
                .builder()
                .type(typeEnum.toString())
                .count((int) count)
                .build();
    }


    public List<TypeAndCountDto> typeAndCountAll() {
        return service
                .findAll("id", "asc")
                .stream()
                .flatMap(pokemonDto -> pokemonDto
                        .getTypes()
                        .stream()
                        .map(typeDto -> new Pair<>(pokemonDto.getName(), typeDto.name))
                )
                .collect(
                        () -> new HashMap<String, TypeAndCountDto>(),
                        (HashMap<String, TypeAndCountDto> map, Pair<String, String> pair) -> {
                            if (map.containsKey(pair.getValue1())) {
                                TypeAndCountDto typeAndCountDto = map.get(pair.getValue1());
                                typeAndCountDto.setCount(typeAndCountDto.count + 1);
                            } else
                                map.put(pair.getValue1(),
                                        TypeAndCountDto
                                                .builder()
                                                .type(pair.getValue1())
                                                .count(1)
                                                .build()
                                );
                        },
                        (map, map1) -> map.putAll(map1)
                )
                .values()
                .stream()
                .sorted(Comparator.comparingInt(type -> type.count * -1))
                .toList();
    }

    public Map<String, List<String>> getPokemonByType() {
        return service
                .findAll("id", "asc")
                .stream()
                .flatMap(pokemonDto -> pokemonDto
                        .getTypes()
                        .stream()
                        .map(typeDto -> new Pair<>(typeDto.name, pokemonDto.getName()))
                )
                .collect(groupingBy(Pair::getValue0,
                        Collectors.mapping(Pair::getValue1, toList()))
                );
    }

    public Map<String, List<String>> getPokemonByParticularTypeSet() {
        return service
                .findAll("id", "asc")
                .stream()
                .map(pokemonDto -> {
                            String collect = pokemonDto
                                    .getTypes()
                                    .stream()
                                    .map(typeDto -> typeDto.name)
                                    .sorted()
                                    .collect(Collectors.joining(","));
                            return new Pair<>(collect, pokemonDto.getName());
                        }
                )
                .collect(groupingBy(Pair::getValue0,
                        Collectors.mapping(Pair::getValue1, toList())
                ));
    }

}


@Builder
@Data
@ToString
class TypeAndCountDto {
    public String type;
    public int count;
}
