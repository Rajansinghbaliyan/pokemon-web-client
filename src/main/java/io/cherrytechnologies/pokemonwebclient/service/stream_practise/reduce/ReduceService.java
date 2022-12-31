package io.cherrytechnologies.pokemonwebclient.service.stream_practise.reduce;

import io.cherrytechnologies.pokemonwebclient.dto.TypeDto;
import io.cherrytechnologies.pokemonwebclient.enums.PokemonTypes;
import io.cherrytechnologies.pokemonwebclient.service.PokemonService;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}


@Builder
@Data
@ToString
class TypeAndCountDto {
    public String type;
    public int count;
}
