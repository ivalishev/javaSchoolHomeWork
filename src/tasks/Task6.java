package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

  private Set<String> getPersonDescriptions(Collection<Person> persons,
                                            Map<Integer, Set<Integer>> personAreaIds,
                                            Collection<Area> areas) {
    long startTime = new Date().getTime();
    Set<String> nameArea = new HashSet<>();

    personAreaIds
        .forEach((integerKey, integerSet) -> integerSet
            .forEach(integer -> {
              nameArea.add(String.join(" - ",
                  persons.stream().filter(person -> integerKey.equals(person.getId())).findFirst().get().getFirstName(),
                  areas.stream().filter(area -> integer.equals(area.getId())).findFirst().get().getName())
              );
            })
        );
    //Стартанули в : 1606598940871
    //Закончили в : 1606598940873
    //Потрачено  : 2

    long endTime = new Date().getTime();
    System.out.println("Стартанули в : " + startTime);
    System.out.println("Закончили в : " + endTime);
    System.out.println("Потрачено  : " + (endTime - startTime));

    return nameArea;
  }

  @Override
  public boolean check() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", Instant.now()),
        new Person(2, "Vasya", Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    return getPersonDescriptions(persons, personAreaIds, areas)
        .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
  }
}
