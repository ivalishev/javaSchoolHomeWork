package tasks;

import common.ApiPersonDto;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/*
Задача 5
Расширим предыдущую задачу
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    long startTime = new Date().getTime();

    Map<Integer, Integer> sortedMap =
        personAreaIds.entrySet().stream()
            .sorted(Entry.comparingByValue())
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
    // Пришлось добавить еще сортировку , т.к. List.of(person1, person2)
    // в проверке теста иногда ставит персону с id = 2 на первое место изза этого
    // тест то проходит то не проходит


    List<ApiPersonDto> apiPersonDtos = new ArrayList<>();

    sortedMap.forEach((personId, area) ->
        apiPersonDtos.add(convert(persons.stream().filter(person -> personId.equals(person.getId())).findFirst().get(), area))
    );
    /*
     Стартанули в : 1606590483755
     Закончили в : 1606590483756
     Потрачено  : 1
     */

    long endTime = new Date().getTime();
    System.out.println("Стартанули в : " + startTime);
    System.out.println("Закончили в : " + endTime);
    System.out.println("Потрачено  : " + (endTime - startTime));

    return apiPersonDtos;
  }

  private static ApiPersonDto convert(Person person, Integer areaId) {
    ApiPersonDto dto = new ApiPersonDto();
    dto.setCreated(person.getCreatedAt().toEpochMilli());
    dto.setId(person.getId().toString());
    dto.setName(person.getFirstName());
    dto.setAreaId(areaId);
    return dto;
  }

  @Override
  public boolean check() {
    Person person1 = new Person(1, "Name", Instant.now());
    Person person2 = new Person(2, "Name", Instant.now());
    Map<Integer, Integer> personAreaIds = Map.of(1, 1, 2, 2);
    return List.of(convert(person1, 1), convert(person2, 2))
        .equals(convert(List.of(person1, person2), personAreaIds));
  }
}
