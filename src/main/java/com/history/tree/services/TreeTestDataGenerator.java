package com.history.tree.services;

import com.history.tree.model.Person;
import com.history.tree.model.Relationship;
import com.history.tree.model.Tree;
import com.history.tree.repositories.PersonRepository;
import com.history.tree.repositories.RelationshipRepository;
import com.history.tree.repositories.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TreeTestDataGenerator {

    private final PersonRepository personRepository;
    private final TreeRepository treeRepository;
    private final RelationshipRepository relationshipRepository;

    private static final int MAX_ONE_GENDER_PEOPLE_COUNT = 1000;

    public Mono<Tree> generate() {
        Tree tree = new Tree();
        tree.setName("Test");

        return treeRepository.save(tree).doOnNext((e) -> {
            var malePersons = generateMalePersons();
            var femalePersons = generateFemalePersons();
            malePersons.addAll(femalePersons);
            personRepository.saveAll(malePersons)
                    .collectList()
                    .doOnNext(persons -> {
                        var mPersons = persons.stream().filter(p -> p.getGender() == 'M').collect(Collectors.toList());
                        var fPersons = persons.stream().filter(p -> p.getGender() == 'F').collect(Collectors.toList());
                        List<Relationship> relationships = getRelationships(mPersons, fPersons, 0);
                        relationshipRepository.saveAll(relationships).subscribe();
                    });
        });
    }

    private List<Relationship> getRelationships(List<Person> mPersons, List<Person> fPersons, int count) {


        var currentGroupCount = 4;

        if (mPersons.size() == count) {
            return Collections.emptyList();
        }
        List<Person> relatives;
        if (mPersons.size() >= count + currentGroupCount) {
            relatives = mPersons.subList(count, mPersons.size() - 1);
            relatives.addAll(fPersons.subList(count, fPersons.size() - 1));
        } else {
            relatives = mPersons.subList(count, currentGroupCount / 2);
            relatives.addAll(mPersons.subList(count, currentGroupCount / 2));
        }

        List<Relationship> relationship = new ArrayList<>();


        Person father = relatives.remove(0);
        Person mather = relatives.remove(relatives.size() - 1);

        var marriage = new Relationship();
        marriage.setPersonId(father.getId());
        marriage.setRelationPersonId(mather.getId());
        marriage.setRelationshipType(1); //Marriage

        count = count + currentGroupCount;
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

        return generatePersons(names, lastNames, patonymics);
    }

    private List<Person> generateFemalePersons() {
        var names = Arrays.asList("Василиса", "Нелли", "Мария", "Таисия", "Селина", "Наталья");
        var lastNames = Arrays.asList("Коченева", "Польская", "Мельникова", "Догова", "Литовская", "Иванова", "Сергеева");
        var patonymics = Arrays.asList("Ивановна", "Сергеевна", "Андреевна", "Николаевна", "Григорьевна", "Евгеньевна");

        return generatePersons(names, lastNames, patonymics);
    }

    private List<Person> generatePersons(List<String> names, List<String> lastNames, List<String> patonymics) {
        var persons = new ArrayList<Person>(MAX_ONE_GENDER_PEOPLE_COUNT);

        IntStream.range(0, 1000).forEach(e -> {
            var random = new Random().nextInt(names.size() - 1);
            var person = new Person();
            person.setFirstName(names.get(random));

            random = new Random().nextInt(lastNames.size() - 1);
            person.setLastName(lastNames.get(random));

            random = new Random().nextInt(patonymics.size() - 1);
            person.setPatronymic(patonymics.get(random));

            person.setFirstName("");
            persons.add(person);
        });

        return persons;
    }
}
