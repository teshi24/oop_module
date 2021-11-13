package ch.hslu.oop.SW07.objectsEqualsCompare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonComparatorTest {

  private final PersonComparator personComparator = new PersonComparator();
  
  private Person person;

  private final String lastName = "Stadelmann";
  private final String firstName = "Nadja";

  private final String lastNameBeforeDefaultInAlphabet = "Achermann";
  private final String lastNameAfterDefaultInAlphabet = "Walden";
  private final String firstNameBeforeDefaultInAlphabet = "Anna";
  private final String firstNameAfterDefaultInAlphabet = "Zinnia";

  @BeforeEach
  void setUp() {
    person = new Person(1L, lastName, firstName);
  }

  private Person getDifferentPersonWithSameNamesAsDefault() {
    return new Person(person.getAhvNr() + 1, person.getLastName(), person.getFirstName());
  }

  private Person getPersonAsDuplicationOfDefault() {
    return new Person(person.getAhvNr(), person.getLastName(), person.getFirstName());
  }
  
  @Test
  void compareTo_samePerson_return0() {
    assertEquals(0, personComparator.compare(person, person));
  }

  @Test
  void compareTo_duplicatedPerson_return0() {
    assertEquals(0, personComparator.compare(person, getPersonAsDuplicationOfDefault()));
  }

  @Test
  void compareTo_peopleWithSameNames_return0() {
    assertEquals(0, personComparator.compare(person, getDifferentPersonWithSameNamesAsDefault()));
  }

  @Test
  void compareTo_personWithSameLastNamesAndFirstNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(-1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithSameLastNamesAndFirstNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithSameFirstNamesAndLastNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    assertEquals(-1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithSameFirstNamesAndLastNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    assertEquals(1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithLastNameBeforeTheOtherAndFirstNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithLastNameBeforeTheOtherAndFirstNameAfterTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithLastNameAfterTheOtherAndFirstNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(-1, personComparator.compare(person, comparePerson));
  }

  @Test
  void compareTo_personWithLastNameAfterTheOtherAndFirstNameBeforeTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(-1, personComparator.compare(person, comparePerson));
  }
}