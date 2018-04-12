-- 01
SELECT firstName, middleName, lastName FROM Person
WHERE lastName = 'May';

-- 02
SELECT p1.firstName, p1.lastName, p1.lastName FROM Person p1
JOIN (SELECT * FROM Person WHERE id = 17) p2 ON p2.parent1 = p1.id OR p2.parent2 = p1.id;

-- 03
SELECT firstName, middleName, lastName FROM Person
WHERE parent1 = 17 OR parent2 = 17;

-- 04
SELECT firstName, middleName, lastName FROM Person p1
JOIN (SELECT parent1, parent2 FROM Person WHERE id = 17) p2 ON p2.parent1 = p1.parent1 AND p2.parent2 = p1.parent2
WHERE id <> 17;

-- 05
SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
JOIN getParents ON getParents.parent1 = Person.id or getParents.parent2 = Person.id;

-- 06
SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
JOIN getChildren ON getChildren.id = Person.parent1 or getChildren.id = Person.parent2;

-- 07
SELECT firstName, middleName, lastName FROM Person
JOIN Relation ON Person.id = pid1 OR Person.id = pid2
WHERE (pid1 = 9 or pid2 = 9) AND isArchived = 0 AND description = 'married' AND Person.id <> 9;

-- 08
SELECT date, name, location FROM Event
WHERE personId1 = 1 OR personId2 = 1;

-- 09
SELECT firstName, middleName, lastName FROM Person
WHERE bio LIKE "%farmer%";

-- 10
SELECT date, name, location FROM Event WHERE
date = '2010-6-1';
