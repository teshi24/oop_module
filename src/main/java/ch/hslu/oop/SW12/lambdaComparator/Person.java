package ch.hslu.oop.SW12.lambdaComparator;

import java.util.Objects;

/**
 * It's a lovely Person.
 */
public class Person implements Comparable<Person> {

  private final long ahvNr;
  private String lastName;
  private String firstName;

  public Person(final long ahvNr, final String lastName, final String firstName) {
    this.ahvNr = ahvNr;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public long getAhvNr() {
    return ahvNr;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * The person is only the exact same person, if the ahvNr is the same.
   *
   * @param obj - the object which is checked if it is the same person
   * @return true if obj is a person with the same ahvNr of the caller person.
   */
  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Person)) {
      return false;
    }
    return ahvNr == ((Person) obj).getAhvNr();
  }

  @Override
  public final int hashCode() {
    return Objects.hash(ahvNr);
  }

  @Override
  public String toString() {
    return "Person{" + "ahvNr=" + ahvNr + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + '}';
  }

  /**
   * Comparing the names of the objects for sorting by LastName, FirstName.
   *
   * @param person The person which gets compared to the caller Person
   * @return 0 if the names are equals, -1 if the given persons name is after the alphabet, 1 if the given persons
   * name is before the alphabet
   */
  @Override
  public int compareTo(final Person person) {
    final int lastNameComparison = lastName.compareTo(person.getLastName());
    if (lastNameComparison > 0) {
      return 1;
    } else if (lastNameComparison < 0) {
      return -1;
    }
    final int firstNameComparison = firstName.compareTo(person.getFirstName());
    return Integer.compare(firstNameComparison, 0);
  }
}
