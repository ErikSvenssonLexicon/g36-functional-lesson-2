package se.lexicon.functional_lesson_three;

import java.util.List;

import se.lexicon.functional_lesson_three.model.Person;
import se.lexicon.functional_lesson_three.util.PersonGenerator;

public class App {
	
    public static void main( String[] args ){
        List<Person> persons = PersonGenerator.getInstance().generate(1000);
        
    }
    
}
