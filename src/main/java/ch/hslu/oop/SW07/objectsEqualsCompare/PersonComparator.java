package ch.hslu.oop.SW07.objectsEqualsCompare;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

  @Override
  public int compare(final Person person, final Person comparePerson) {
    return person.compareTo(comparePerson);
  }
}
