package se.lexicon.functional_lesson_three;

import se.lexicon.functional_lesson_three.model.Gender;
import se.lexicon.functional_lesson_three.model.Person;
import se.lexicon.functional_lesson_three.util.PersonGenerator;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static final List<Person> PEOPLE = PersonGenerator.getInstance().generate(100000);

    public static void main(String[] args ) {


        //collectToMap(people);
        //mapExample(PEOPLE).forEach(System.out::println);

        System.out.println(findById(2));


    }

    public static void countExample(List<Person> source){
        long count = source.stream()
                .count();
        System.out.println(count);
    }

    public static void minAndMax(List<Person> source){
        Optional<Person> result = source.stream()
                .min(Comparator.comparingInt(Person::getAge));

        System.out.println(result);

        System.out.println(source.stream()
            .max((p1, p2) -> p1.getLastName().length() - p2.getLastName().length())
        );

    }

    public static void findFirstAndAny(List<Person> source){
        Optional<Person> optional = source.stream()
                .findFirst();

        Optional<Person> optional2 = source.stream().parallel()
                .findAny();

        if(optional.isPresent()){
            System.out.println(optional.get());
        }

        if(optional2.isPresent()){
            System.out.println(optional2.get());
        }

    }

    public static void allAnyNoneMatch(List<Person> source){

        Predicate<Person> isFemale = p -> p.getGender() == Gender.FEMALE;

        System.out.println(source.stream().allMatch(isFemale));
        System.out.println(source.stream().noneMatch(isFemale));
        System.out.println(source.stream().anyMatch(isFemale));

    }

    public static void forEach(List<Person> source){
        source.stream().forEach(p -> System.out.println(p));

        System.out.println();

        source.stream()
                .forEach(person -> person.setFirstName("Domas")); //Potential bad habit - AVOID

        source.stream().forEach(System.out::println);

    }

    public static void reduce(List<Person> source){
        Integer sumOfAge = source.stream()
                .map(p -> p.getAge())
                .reduce(0, (Integer age1, Integer age2) -> age1 + age2);

        System.out.println(sumOfAge);
    }

    public static void collect(List<Person> source){
        Collection<String> everyUniqueFirstName = source.stream()
            .collect(HashSet::new, (set, p) -> set.add(p.getFirstName()), HashSet::addAll);

        System.out.println(everyUniqueFirstName);
    }

    public static void collectToMap(List<Person> source){
        Map<Integer, List<Person>> map = source.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        Map<Boolean, List<Person>> map2 = source.stream()
                .collect(Collectors.partitioningBy(p -> p.getGender() == Gender.FEMALE));

        map2.forEach((bool, list) -> {
            System.out.println(
                    bool == true ? "No of females:" + list.size() : "No of males:" + list.size()
            );
        });

        map.get(20).forEach(System.out::println);
    }

    public static <T> Collection<T> filterAndExtract(Collection<T> source, Supplier<Collection<T>> supplier, Predicate<T> filter){
        return source.stream()
                .filter(filter)
                .collect(Collectors.toCollection(supplier));

    }

    public static Set<Person> filterAndExtract(Collection<Person> source, Predicate<Person> filter){
        return source.stream()
                .filter(filter)
                .collect(Collectors.toCollection(HashSet::new));

    }

    public static LocalDate[] generateByYear(int year){
        LocalDate[] allDates = null;
        allDates = Stream.iterate(LocalDate.ofYearDay(year, 1), localDate -> localDate.plusDays(1))
                .limit(Year.of(year).isLeap() ? 366 : 365)
                .toArray(LocalDate[]::new);
        return allDates;
    }

    public static List<String> mapExample(List<Person> source){
        List<String> fullNames = source.stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .distinct()
                .collect(Collectors.toList());
        return fullNames;

    }

    public static Person findById(int id) throws RuntimeException{
        Optional<Person> person = Stream.of(id)
                .map(App::dbCall)
                .findFirst();

        return person.orElseThrow(() -> new RuntimeException("Sorry no can do"));

    }

    public static Person dbCall(int personId){
        return PEOPLE.stream().filter(p -> p.getId() == personId).findFirst().orElseThrow(() -> new RuntimeException("Sorry no can do"));
    }





    
}
