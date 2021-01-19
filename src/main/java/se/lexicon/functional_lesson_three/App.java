package se.lexicon.functional_lesson_three;

import se.lexicon.functional_lesson_three.model.Person;
import se.lexicon.functional_lesson_three.util.PersonGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class App {
	
    public static void main( String[] args ) {
        List<Person> people = PersonGenerator.getInstance().generate(10000);


        findFirstAndAny(people);


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



    
}
