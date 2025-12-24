package koodali.repository;

import koodali.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends JpaRepository<T, String> {
    Optional<T> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(
            String firstName,
            String lastName
    );
    /** protected final HashMap<String, T> persons = new HashMap<>();

     public PersonRepository() {
     super(Person.class,);

     }


     public boolean contains(T Person) {
     return persons.containsValue(Person);
     }

     public boolean contains(String PersonID) {
     return persons.containsKey(PersonID);
     }


     /**
     * finds the person involved by their id. Preferable use this in backend
     *
     * @param personID the ID of the person
     * @return an Optional<Person> because we need to know if they exist or not!

    public Optional<T> findByID(String personID) {
    Optional<String> first = persons.keySet().stream().filter(s -> s.equals(personID)).findFirst();
    return first.map(persons::get);
    }

    /**
     * finds the person by their name. Not case sensitive, will be used in frontend much more
     *
     * @param name , can be any part of the persons name
     * @return an Optional Person

    public Optional<T> findByName(String name) {
    return persons
    .values()
    .stream()
    .filter(t -> t.getFirstName().equalsIgnoreCase(name) || t.getLastName().equalsIgnoreCase(name))
    .findFirst();
    }

    /**
     * deletes the Person from the System entirely, so be careful

    public T delete(String personID) {
    return persons
    .remove(personID);
    }

    /**
     * adds Person to the system
     *
     * @param person to be added
     * @return the person that was added - do NOT return persons.put esp since we use this methods to save/add people to the system

    public T save(T person) {
    persons
    .put(person.getID(), person);
    return person;
    }

    public List<T> getAll() {
    return persons.values().stream().toList();
    }

    public T update(T person) {
    return persons
    .put(person.getID(), person);
    }*/
}
