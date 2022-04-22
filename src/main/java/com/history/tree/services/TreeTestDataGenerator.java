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
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
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

/*        return treeRepository.save(tree)
                .subscribeOn(Schedulers.boundedElastic())
                .delayUntil((data) -> {
                    var malePersons = generateMalePersons();
                    var femalePersons = generateFemalePersons();
                    malePersons.addAll(femalePersons);
                    return personRepository.saveAll(malePersons)
                            .collectList()
                            .delayUntil((persons) -> {
                                var mPersons = getPersonsByGender(persons, 'M');
                                var fPersons = getPersonsByGender(persons, 'F');
                                List<Relationship> relationships = getRelationships(mPersons, fPersons, new AtomicInteger(0));
                                return relationshipRepository.saveAll(relationships);
                            });
                });*/
        return null;
    }

    private List<Person> getPersonsByGender(List<Person> persons, char gender) {
        return persons.stream().filter(p -> p.getGender() == gender).collect(Collectors.toList());
    }

    private List<Relationship> getRelationships(List<Person> mPersons, List<Person> fPersons, AtomicInteger count) {

        var currentGroupCount = 2;

        if (count.get() >= mPersons.size()) {
            return Collections.emptyList();
        }
        List<Person> relatives = new ArrayList<>(currentGroupCount * 2);
        if (count.get() + currentGroupCount >= mPersons.size()) {
            relatives.addAll(mPersons.subList(count.get(), mPersons.size() - 1));
            relatives.addAll(fPersons.subList(count.get(), fPersons.size() - 1));
        } else {
            relatives.addAll(mPersons.subList(count.get(), count.get() + currentGroupCount));
            relatives.addAll(fPersons.subList(count.get(), count.get() + currentGroupCount));
        }

        Person father = relatives.get(0);
        Person mather = relatives.get(relatives.size() - 1);

        var marriage = new Relationship();
        //marriage.setPersonId(father.getId());
        marriage.setRelationPersonId(mather.getId());
        marriage.setRelationshipType(1); //Marriage

        count.set(count.get() + currentGroupCount);

        List<Relationship> relationship = new ArrayList<>();
        relationship.add(marriage);

        relatives.subList(1, relatives.size() - 1).forEach(r -> {
            var relation = new Relationship();
            //relation.setPersonId(r.getId());
            relation.setRelationPersonId(r.getId());
            relation.setRelationshipType(2); //Children
            relationship.add(relation);
            relationship.addAll(getRelationships(mPersons, fPersons, count));
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

    private LocalDate getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
