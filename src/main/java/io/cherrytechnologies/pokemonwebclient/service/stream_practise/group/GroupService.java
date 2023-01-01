package io.cherrytechnologies.pokemonwebclient.service.stream_practise.group;

public class GroupService {
    /* This is how we could group our result
        Map<Dish.Type, List<Dish>> dishesByType =
        menu.stream().collect(groupingBy(Dish::getType));

        Could also create our own classification lambda (The lambda used to group)

        public enum CaloricLevel { DIET, NORMAL, FAT }
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                    } ));


        Could also able to manipulate the result of the grouped items:

        Map<Dish.Type, List<Dish>> caloricDishesByType =
         menu.stream()
                .collect(groupingBy(Dish::getType,
                         filtering(dish -> dish.getCalories() > 500, toList())));

        Map<Dish.Type, List<String>> dishNamesByType =
             menu.stream()
             .collect(groupingBy(Dish::getType,
             mapping(Dish::getName, toList())));


        Map<Dish.Type, Set<String>> dishNamesByType =
             menu.stream()
             .collect(groupingBy(Dish::getType,
             flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
             toSet())));


        Could also do multi level grouping

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
            menu.stream().collect(
             groupingBy(Dish::getType,
             groupingBy(dish -> {
             if (dish.getCalories() <= 400) return CaloricLevel.DIET;
             else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
             else return CaloricLevel.FAT;
             } )
             )
            );

        Could also be able to collect data according to the groups

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
            groupingBy(Dish::getType, counting()));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
             menu.stream()
             .collect(groupingBy(Dish::getType,
             maxBy(comparingInt(Dish::getCalories))));


        Collecting and then :
        Map<Dish.Type, Dish> mostCaloricByType =
                 menu.stream()
                 .collect(groupingBy(Dish::getType,
                 collectingAndThen(
                 maxBy(comparingInt(Dish::getCalories)),
                 Optional::get)));


        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
        menu.stream().collect(
         groupingBy(Dish::getType, mapping(dish -> {
         if (dish.getCalories() <= 400) return CaloricLevel.DIET;
         else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
         else return CaloricLevel.FAT; },
         toCollection(HashSet::new) )));


         Partitioning:
         Partitioning is a special case of grouping: having a predicate called a partitioning function as a classification function.
         The fact that the partitioning function returns a boolean means the resulting grouping Map will have a Boolean as a key type,
         and therefore,
         there can be at most two different groups—one for true and one for false. For
         instance, if you’re vegetarian or have invited a vegetarian friend to have dinner with
         you, you may be interested in partitioning the menu into vegetarian and nonvegetarian dishes:

         Map<Boolean, List<Dish>> partitionedMenu =
          menu.stream().collect(partitioningBy(Dish::isVegetarian));
    */


}
