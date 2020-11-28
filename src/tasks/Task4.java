package tasks;

import common.ApiPersonDto;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Задача 4
Список персон класса Person необходимо сконвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны уже предоставлен
FYI - DTO = Data Transfer Object - распространенный паттерн, можно погуглить
 */
public class Task4 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<ApiPersonDto> convert(List<Person> persons) {
    long startTime = new Date().getTime();

    List<ApiPersonDto> apiPersonDtos = new ArrayList<>();

    persons.forEach(
        person -> {
          apiPersonDtos.add(convert(person));
        }
    );

    long endTime = new Date().getTime();
    System.out.println("Стартанули в : " + startTime);
    System.out.println("Закончили в : " + endTime);
    System.out.println("Потрачено  : " + (endTime - startTime));

    return apiPersonDtos;
  }

  private static ApiPersonDto convert(Person person) {
    ApiPersonDto dto = new ApiPersonDto();
    dto.setCreated(person.getCreatedAt().toEpochMilli());
    dto.setId(person.getId().toString());
    dto.setName(person.getFirstName());
    return dto;
  }

  @Override
  public boolean check() {
    Person person1 = new Person(1, "Name", Instant.now());
    Person person2 = new Person(2, "Name", Instant.now());
    return List.of(convert(person1), convert(person2))
        .equals(convert(List.of(person1, person2)));
  }
}
