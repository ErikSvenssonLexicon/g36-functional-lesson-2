package se.lexicon.functional_lesson_three;

import se.lexicon.functional_lesson_three.model.Person;
import se.lexicon.functional_lesson_three.model.PersonDTO;
import se.lexicon.functional_lesson_three.util.PersonGenerator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class App {

    public static final List<Person> PEOPLE = PersonGenerator.getInstance().generate(10);

    public static void main(String[] args ) {
        Predicate<Person> predicate = person -> person.getAge() > 30;
        Function<Person, PersonDTO> toPersonDTO = StaticReference::convert;

        Supplier<Collection<PersonDTO>> supplier = HashSet::new;

        Consumer<Person> personConsumer = System.out::println;

        SortingExample.sortAndReturn(PEOPLE, person -> person.getLastName().equals("Svensson"), Comparator.comparing(Person::getAge))
                .forEach(System.out::println);


        //userPredicate(PEOPLE, predicate, personConsumer);
        //userPredicate(PEOPLE, predicate, person -> System.out.println(person.getFirstName() + " " + person.getLastName()));

        /*
        mapToDTO(PEOPLE, Person::toString, HashSet::new)
                .forEach(System.out::println);
         */


        /*
        mapToDTONonGeneric(PEOPLE, toPersonDTO)
                .forEach(System.out::println);


         */


    }

    public static void userPredicate(List<Person> people, Predicate<Person> filter, Consumer<Person> consumer){
        for(Person person : people){
            if(filter.test(person)){
                consumer.accept(person);
            }
        }
    }

    public static List<PersonDTO> mapToDTONonGeneric(List<Person> people, Function<Person, PersonDTO> dtoFunction){
        List<PersonDTO> result = new ArrayList<>();
        for(Person person : people){
            result.add(dtoFunction.apply(person));
        }
        return result;
    }

    public static <T,R> Collection<R> mapToDTO(List<T> list, Function<T, R> mapper, Supplier<Collection<R>> collectionSupplier){
        Collection<R> result = collectionSupplier.get();
        for(T t : list){
            result.add(mapper.apply(t));
        }
        return result;
    }

    public static Person findAndReturn(List<Person> people, Predicate<Person> filter){
        for (Person person : people){
            if(filter.test(person)){
                return person;
            }
        }
        return null;
    }
}
