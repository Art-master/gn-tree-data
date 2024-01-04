package com.history.tree.services

import com.history.tree.repositories.*
import org.springframework.stereotype.Service

@Service
class TreeTestDataGenerator(
    val personRepository: PersonRepository,
    val treeRepository: TreeRepository,
    val relationshipRepository: RelationshipRepository,
    //val passwordEncoder: PasswordEncoder,
) {

    companion object {
        private const val MAX_ONE_GENDER_PEOPLE_COUNT = 50
    }


    /*private val randomDate: LocalDate
        get() {
            val minDay = LocalDate.of(1970, 1, 1).toEpochDay()
            val maxDay = LocalDate.of(2015, 12, 31).toEpochDay()
            val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
            return LocalDate.ofEpochDay(randomDay)
        }

    @Transactional
    suspend fun generate(): Tree {
        val tree = Tree(name = "Дерево", description = "Описание")
        val savedTree = treeRepository.save(tree)
        val malePersons = generateMalePersons(savedTree)
        val allPersons = generateFemalePersons(savedTree).plus(malePersons)

        val persons = personRepository.saveAll(allPersons).toList()

        val mPersons = getPersonsByGender(persons, 'M')
        val fPersons = getPersonsByGender(persons, 'F')

        val relationships = getRelationships(mPersons, fPersons, AtomicInteger(0))

        relationshipRepository.saveAll(relationships)
        relationshipRepository.saveAll(relationships)

        return tree
    }

    private fun getPersonsByGender(persons: List<Person>, gender: Char): List<Person> {
        return persons.stream().filter { p: Person -> p.gender == gender }.collect(Collectors.toList())
    }

    private fun getRelationships(
        mPersons: List<Person>,
        fPersons: List<Person>,
        count: AtomicInteger
    ): List<Relationship> {

        val currentGroupCount = 2
        if (count.get() >= mPersons.size) {
            return emptyList()
        }
        val relatives: MutableList<Person> = java.util.ArrayList<Person>(currentGroupCount * 2)
        if (count.get() + currentGroupCount >= mPersons.size) {
            relatives.addAll(mPersons.subList(count.get(), mPersons.size - 1))
            relatives.addAll(fPersons.subList(count.get(), fPersons.size - 1))
        } else {
            relatives.addAll(mPersons.subList(count.get(), count.get() + currentGroupCount))
            relatives.addAll(fPersons.subList(count.get(), count.get() + currentGroupCount))
        }
        val father = relatives[0]
        val mather = relatives[relatives.size - 1]

        val marriage = Relationship(
            personId = father.id,
            relatedPersonId = mather.id,
            relationshipType = 1 //Marriage
        )

        count.set(count.get() + currentGroupCount)
        val relationship: MutableList<Relationship> = ArrayList()
        relationship.add(marriage)
        relatives.subList(1, relatives.size - 1).forEach { r: Person ->
            val relation = Relationship(
                personId = r.id,
                relatedPersonId = r.id,
                relationshipType = 2 //Children
            )
            relationship.add(relation)
            relationship.addAll(getRelationships(mPersons, fPersons, count))
        }
        return relationship
    }

    private fun generateMalePersons(tree: Tree): List<Person> {
        val names = listOf("Василий", "Иван", "Семен", "Александр", "Денис", "Тимофей")
        val lastNames = listOf("Коченев", "Польской", "Мельников", "Догов", "Литовский", "Исанов", "Сергеев")
        val patronymics = listOf("Иванович", "Сергеевич", "Андреевич", "Николаевич", "Григорьевич", "Евгеньевич")
        return generatePersons(tree, names, lastNames, patronymics, 'M')
    }

    private fun generateFemalePersons(tree: Tree): List<Person> {
        val names = listOf("Василиса", "Нелли", "Мария", "Таисия", "Селина", "Наталья")
        val lastNames = listOf("Коченева", "Польская", "Мельникова", "Догова", "Литовская", "Иванова", "Сергеева")
        val patronymics = listOf("Ивановна", "Сергеевна", "Андреевна", "Николаевна", "Григорьевна", "Евгеньевна")
        return generatePersons(tree, names, lastNames, patronymics, 'F')
    }

    private fun generatePersons(
        tree: Tree,
        names: List<String>,
        lastNames: List<String>,
        patronymics: List<String>,
        gender: Char
    ): List<Person> {
        val persons = ArrayList<Person>(MAX_ONE_GENDER_PEOPLE_COUNT)

        IntStream.range(0, 1000).forEach { e: Int ->

            val random = Random().nextInt(names.size.inc())
            val random2 = Random().nextInt(names.size.inc())
            val random3 = Random().nextInt(names.size.inc())

            val person = Person(
                treeId = tree.id,
                firstName = names[random],
                lastName = lastNames[random2],
                patronymic = patronymics[random3],
                gender = gender,
                birthDate = randomDate
            )
            persons.add(person)
        }
        return persons
    }

    fun createTestsUsers(): Flow<User> {
        val user = User().apply {
            name = "user"
            login = "user@mail.ru"
            //password = passwordEncoder.encode("user")

        }

        val userAdmin = User().apply {
            name = "admin"
            login = "admin@mail.ru"
            //password = passwordEncoder.encode("admin")
        }

        return userRepository.saveAll(listOf(userAdmin, user))
    }*/
}