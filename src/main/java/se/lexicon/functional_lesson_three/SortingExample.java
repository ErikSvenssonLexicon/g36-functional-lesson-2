package se.lexicon.functional_lesson_three;

import se.lexicon.functional_lesson_three.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SortingExample {

    public static List<Person> sortAndReturn(final List<Person> source, Predicate<Person> predicate, Comparator<Person> comparator){
        List<Person> people = new ArrayList<>();
        for(Person person : source){
            if(predicate.test(person)){
                people.add(person);
            }
        }
        people.sort(comparator);
        return people;
    }

}
