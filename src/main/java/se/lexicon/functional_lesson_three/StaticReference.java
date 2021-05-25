package se.lexicon.functional_lesson_three;

import se.lexicon.functional_lesson_three.model.Person;
import se.lexicon.functional_lesson_three.model.PersonDTO;

public class StaticReference {

    public static PersonDTO convert(Person person){
        return new PersonDTO(
                person.getId(), person.getFirstName(), person.getLastName()
        );
    }

}
