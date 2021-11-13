package ch.hslu.oop.SW07.objectsEqualsCompare;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonTest {

  private Person person;

  private final String lastName = "Stadelmann";
  private final String firstName = "Nadja";
  private final long ahvNr = 1L;

  private final String lastNameBeforeDefaultInAlphabet = "Achermann";
  private final String lastNameAfterDefaultInAlphabet = "Walden";
  private final String firstNameBeforeDefaultInAlphabet = "Anna";
  private final String firstNameAfterDefaultInAlphabet = "Zinnia";

  @BeforeEach
  void setUp() {
    person = new Person(ahvNr, lastName, firstName);
  }

  private Person getDifferentPersonWithSameNamesAsDefault() {
    return new Person(person.getAhvNr() + 1, person.getLastName(), person.getFirstName());
  }

  private Person getPersonAsDuplicationOfDefault() {
    return new Person(person.getAhvNr(), person.getLastName(), person.getFirstName());
  }

  private Person getPersonWithDifferentLastNameThanDefaultPerson() {
    return new Person(person.getAhvNr(), "Other Last Name", person.getFirstName());
  }

  private Person getPersonWithDifferentFirstNameThanDefaultPerson() {
    return new Person(person.getAhvNr(), person.getLastName(), "Other First Name");
  }

  @Test
  void test_personCreation() {
    assertNotNull(person);
    assertEquals(ahvNr, person.getAhvNr());
    assertEquals(lastName, person.getLastName());
    assertEquals(firstName, person.getFirstName());
  }

  @Test
  void test_setLastName() {
    final String newLastName = "Stalder";
    person.setLastName(newLastName);
    assertEquals(newLastName, person.getLastName());
  }

  @Test
  void test_setFirstName() {
    final String newFirstName = "Natalie";
    person.setFirstName(newFirstName);
    assertEquals(newFirstName, person.getFirstName());
  }

  @Test
  void test_toString() {
    assertEquals("Person{ahvNr=" + person.getAhvNr() + ", lastName='" + person.getLastName() + "', firstName='" + person.getFirstName() + "'}", person.toString());
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(Person.class).withIgnoredFields("lastName", "firstName").verify();
  }

  @Test
  void equals_2PeopleSameNamesDifferentAhvNrs_returnFalse() {
    assertNotEquals(getDifferentPersonWithSameNamesAsDefault(), person);
  }

  @Test
  void equals_2PeopleSameAttributes_returnTrue() {
    assertEquals(getPersonAsDuplicationOfDefault(), person);
  }

  @Test
  void equals_2PeopleSameAhvNrsDifferentLastName_returnTrue() {
    assertEquals(getPersonWithDifferentLastNameThanDefaultPerson(), person);
  }

  @Test
  void equals_2PeopleSameAhvNrsDifferentFirstName_returnTrue() {
    assertEquals(getPersonWithDifferentFirstNameThanDefaultPerson(), person);
  }

  @Test
  void hashCode_2PeopleSameNamesDifferentAhvNrs_returnFalse() {
    assertNotEquals(getDifferentPersonWithSameNamesAsDefault().hashCode(), person.hashCode());
  }

  @Test
  void hashCode_2PeopleSameAttributes_returnTrue() {
    assertEquals(getPersonAsDuplicationOfDefault().hashCode(), person.hashCode());
  }

  @Test
  void hashCode_2PeopleSameAhvNrsDifferentLastName_returnTrue() {
    assertEquals(getPersonWithDifferentLastNameThanDefaultPerson().hashCode(), person.hashCode());
  }

  @Test
  void hashCode_2PeopleSameAhvNrsDifferentFirstName_returnTrue() {
    assertEquals(getPersonWithDifferentFirstNameThanDefaultPerson().hashCode(), person.hashCode());
  }

  @Test
  void compareTo_samePerson_return0() {
    assertEquals(0, person.compareTo(person));
  }

  @Test
  void compareTo_duplicatedPerson_return0() {
    assertEquals(0, person.compareTo(getPersonAsDuplicationOfDefault()));
  }

  @Test
  void compareTo_peopleWithSameNames_return0() {
    assertEquals(0, person.compareTo(getDifferentPersonWithSameNamesAsDefault()));
  }

  @Test
  void compareTo_personWithSameLastNamesAndFirstNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(-1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithSameLastNamesAndFirstNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithSameFirstNamesAndLastNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    assertEquals(-1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithSameFirstNamesAndLastNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    assertEquals(1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithLastNameBeforeTheOtherAndFirstNameBeforeTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithLastNameBeforeTheOtherAndFirstNameAfterTheOther_return1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameBeforeDefaultInAlphabet);
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithLastNameAfterTheOtherAndFirstNameAfterTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    comparePerson.setFirstName(firstNameAfterDefaultInAlphabet);
    assertEquals(-1, person.compareTo(comparePerson));
  }

  @Test
  void compareTo_personWithLastNameAfterTheOtherAndFirstNameBeforeTheOther_returnMinus1() {
    final Person comparePerson = getDifferentPersonWithSameNamesAsDefault();
    comparePerson.setLastName(lastNameAfterDefaultInAlphabet);
    comparePerson.setFirstName(firstNameBeforeDefaultInAlphabet);
    assertEquals(-1, person.compareTo(comparePerson));
  }
}