package com.history.tree.services;

import com.history.tree.model.Person;
import com.history.tree.model.Relationship;
import com.history.tree.model.Tree;
import com.history.tree.repositories.PersonRepository;
import com.history.tree.repositories.RelationshipRepository;
import com.history.tree.repositories.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TreeTestDataGenerator {

    private final PersonRepository personRepository;
    private final TreeRepository treeRepository;
    private final RelationshipRepository relationshipRepository;

    private static final int MAX_ONE_GENDER_PEOPLE_COUNT = 50;

    @Transactional
    public Mono<Tree> generate() {
        Tree tree = new Tree();
        tree.setName("Дерево");

        return treeRepository.save(tree)
                .delayUntil((data) -> {
                    var malePersons = generateMalePersons();
                    var femalePersons = generateFemalePersons();
                    malePersons.addAll(femalePersons);
                    return personRepository.saveAll(malePersons)
                            .collectList()
                            .delayUntil((persons) -> {
                                var mPersons = getPersonsByGender(persons, 'M');
                                var fPersons = getPersonsByGender(persons, 'F');
                                List<Relationship> relationships = getRelationships(mPersons, fPersons, 0);
                                return relationshipRepository.saveAll(relationships);
                            });
                });
    }

    private List<Person> getPersonsByGender(List<Person> persons, char gender) {
        return persons.stream().filter(p -> p.getGender() == gender).collect(Collectors.toList());
    }

    private List<Relationship> getRelationships(List<Person> mPersons, List<Person> fPersons, int count) {

        var currentGroupCount = 2;

        if (mPersons.size() == count) {
            return Collections.emptyList();
        }
        List<Person> relatives;
        if (count + currentGroupCount >= mPersons.size()) {
            relatives = mPersons.subList(count, mPersons.size() - 1);
            relatives.addAll(fPersons.subList(count, fPersons.size() - 1));
        } else {
            relatives = mPersons.subList(count, count + currentGroupCount);
            relatives.addAll(mPersons.subList(count, count + currentGroupCount));
        }

        List<Relationship> relationship = new ArrayList<>();


        Person father = relatives.remove(0);
        Person mather = relatives.remove(relatives.size() - 1);

        var marriage = new Relationship();
        marriage.setPersonId(father.getId());
        marriage.setRelationPersonId(mather.getId());
        marriage.setRelationshipType(1); //Marriage

        count = count + (currentGroupCount * 2);
        int finalCount = count;

        relatives.forEach(r -> {
            var relation = new Relationship();
            relation.setPersonId(r.getId());
            relation.setRelationPersonId(r.getId());
            relation.setRelationshipType(2); //Children
            relationship.addAll(getRelationships(mPersons, fPersons, finalCount));
        });

        return relationship;
    }

    private List<Person> generateMalePersons() {
        var names = Arrays.asList("Василий", "Иван", "Семен", "Александр", "Денис", "Тимофей");
        var lastNames = Arrays.asList("Коченев", "Польской", "Мельников", "Догов", "Литовский", "Исанов", "Сергеев");
        var patonymics = Arrays.asList("Иванович", "Сергеевич", "Андреевич", "Николаевич", "Григорьевич", "Евгеньевич");

        return generatePersons(names, lastNames, patonymics, 'M');
    }

    private List<Person> generateFemalePersons() {
        var names = Arrays.asList("Василиса", "Нелли", "Мария", "Таисия", "Селина", "Наталья");
        var lastNames = Arrays.asList("Коченева", "Польская", "Мельникова", "Догова", "Литовская", "Иванова", "Сергеева");
        var patonymics = Arrays.asList("Ивановна", "Сергеевна", "Андреевна", "Николаевна", "Григорьевна", "Евгеньевна");

        return generatePersons(names, lastNames, patonymics, 'F');
    }

    private List<Person> generatePersons(List<String> names, List<String> lastNames, List<String> patonymics, Character gender) {
        var persons = new ArrayList<Person>(MAX_ONE_GENDER_PEOPLE_COUNT);

        IntStream.range(0, 1000).forEach(e -> {
            var random = new Random().nextInt(names.size() - 1);
            var person = new Person();
            person.setFirstName(names.get(random));

            random = new Random().nextInt(lastNames.size() - 1);
            person.setLastName(lastNames.get(random));

            random = new Random().nextInt(patonymics.size() - 1);
            person.setPatronymic(patonymics.get(random));

            person.setGender(gender);

            person.setBirthDate(getRandomDate());

            persons.add(person);
        });

        return persons;
    }

    private LocalDateTime getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.of(LocalDate.ofEpochDay(randomDay), LocalTime.now());
    }

}
