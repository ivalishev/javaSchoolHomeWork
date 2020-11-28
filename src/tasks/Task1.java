package tasks;

import common.Person;
import common.PersonService;
import common.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    long startTime = new Date().getTime();
    Set<Person> persons = PersonService.findPersons(personIds);
    List<Person> personList = new ArrayList<>(persons);
    List<Person> sortedPersons = new ArrayList<>();

    personIds.forEach(integer -> {
      sortedPersons.add(
          personList.stream()
              .filter(x -> x.getId().equals(integer))
              .findFirst().get());
    });
    /*
    Стартанули в : 1606581333110
    Закончили в : 1606581333112
    Потрачено  : 2
    */

    long endTimeTime = new Date().getTime();
    System.out.println("Стартанули в : " + startTime);
    System.out.println("Закончили в : " + endTimeTime);
    System.out.println("Потрачено  : " + (endTimeTime - startTime));
    return sortedPersons;
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
