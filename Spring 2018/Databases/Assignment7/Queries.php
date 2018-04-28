<?php
    function getMembersByLastName($lastName){
        global $db;
        try {
            $query = "SELECT firstName, middleName, lastName FROM Person 
                WHERE lastName = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$lastName]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            db_disconnect();
            exit("Aborting: Error while listing members");
        }
    }

    function getParentById($id){
        global $db;
        try {
            $query = "SELECT p1.firstName, p1.middleName, p1.lastName FROM Person p1
                JOIN (SELECT * FROM Person WHERE Id = ?) p2 ON p2.parent1 = p1.id OR p2.parent2 = p1.id";
            $stmt = $db->prepare($query);
            $stmt->execute([$id]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing parents");
        }
    }

    function getChildrenById($id){
        global $db;
        try {
            $query = "SELECT firstName, middleName, lastName FROM Person
                WHERE parent1 = ? OR parent2 = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing children");
        }
    }

    function getSiblingsById($id){
        global $db;
        try {
            $query = "SELECT firstName, middleName, lastName FROM Person p1
            JOIN (SELECT parent1, parent2 FROM Person WHERE id = ?) p2 ON p2.parent1 = p1.parent1 AND p2.parent2 = p1.parent2
            WHERE id <> ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing siblings");
        }
    }

    function getGrandParentsById($id){
        global $db;
        try {
            $view = "CREATE VIEW getParents AS SELECT * FROM Person
                WHERE id = (SELECT parent1 FROM Person WHERE id = ?) OR
                id = (SELECT parent2 FROM Person WHERE id = ?)";
            $stmt = $db->prepare($view);
            $stmt->execute([$id, $id]);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while creating view");
        } 
        try {
            $query = "SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
                JOIN getParents ON getParents.parent1 = Person.id or getParents.parent2 = Person.id";
            $stmt = $db->prepare($query);
            $stmt->execute([]);
            $succ = $stmt->fetchALL(PDO::FETCH_ASSOC);
            $clean = "DROP VIEW getParents";
            $destroy = $db->prepare($clean);
            $destroy->execute([]);
            return $succ;
        } catch (PDOException $e){
            db_disconnect();
            $clean = "DROP VIEW getParents";
            $destroy = $db->prepare($clean);
            $destroy->execute([]);
            exit("Aborting: Error while listing grandparents");
        } 
    }

    function getGrandChildrenById($id){
        global $db;
        try{
            $view = "CREATE VIEW getChildren AS
            SELECT * FROM Person
            WHERE parent1 = ? or parent2 = ?";
            $stmt = $db->prepare($view);
            $stmt->execute([$id, $id]);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while creating view");
        }
        try {
            $query = "SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
            JOIN getChildren ON getChildren.id = Person.parent1 or getChildren.id = Person.parent2";
            $stmt = $db->prepare($query);
            $stmt->execute([]);
            $succ = $stmt->fetchALL(PDO::FETCH_ASSOC);
            $clean = "DROP VIEW getChildren";
            $destroy = $db->prepare($clean);
            $destroy->execute([]);
            return $succ;
        } catch (PDOException $e){
            db_disconnect();
            $clean = "DROP VIEW getChildren";
            $destroy = $db->prepare($clean);
            $destroy->execute([]);
            exit("Aborting: error while listing grandchildren");
        }
    }

    function getSpouseById($id){
        global $db;
        try{
            $query = "SELECT firstName, middleName, lastName FROM Person
            JOIN Relation ON Person.id = pid1 OR Person.id = pid2
            WHERE (pid1 = ? or pid2 = ?) AND isArchived = 0 AND description = 'married' AND Person.id <> ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id, $id]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing spouse");
        }
    }

    function getEventById($id){
        global $db;
        try{
            $query = "SELECT date, name, location FROM Event
                    WHERE personId1 = ? OR personId2 = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing events");
        }
    }

    function getPersonByKeyword($keyword){
        global $db;
        try{
            $like = '%'.$keyword.'%';
            $query = "SELECT firstName, middleName, lastName FROM Person
                    WHERE bio LIKE ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$like]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing person");
        }
    }

    function getEventByDate($date){
        global $db;
        try{
            $query = "SELECT date, name, location FROM Event 
                    WHERE date = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$date]);
            return $stmt->fetchALL(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_discconect();
            exit("Aborting: error while listing events");
        }
    }

    /*********************************************************************************/

    /*********************** Person ***********************/

    function insertPerson($bio, $gender, $firstName, $middleName, $lastName, $lastKnownAddress, $lastKnownPhoneNumber,
    $birth, $birthLocation, $death, $deathLocation, $parent1, $parent2){
        global $db;
        try{
            $query = "INSERT INTO Person
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            $stmt = $db->prepare($query);
            $stmt->execute([null, $bio, $gender, $firstName, $middleName, $lastName, $lastKnownAddress, $lastKnownPhoneNumber,
                $birth, $birthLocation, $death, $deathLocation, $parent1, $parent2]);
            return true;
        } catch (PDOException $e) {
            db_disconnect();
            exit("Aborting: There was a database error when inserting Person");
        }
    }

    function updatePerson($id, $bio, $gender, $firstName, $middleName, $lastName, $lastKnownAddress, $lastKnownPhoneNumber,
        $birth, $birthLocation, $death, $deathLocation, $parent1, $parent2){
        global $db;
        try{
            $query = "UPDATE Person
                SET bio = ?
                ,gender = ?
                ,firstName = ?
                ,middleName = ?
                ,lastName = ?
                ,lastKnownAddress = ?
                ,lastKnownPhoneNumber = ?
                ,birth = ?
                ,birthLocation = ?
                ,death = ?
                ,deathLocation = ?
                ,parent1 = ?
                ,parent2 = ?
                WHERE id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$bio, $gender, $firstName, $middleName, $lastName, $lastKnownAddress, $lastKnownPhoneNumber,
                $birth, $birthLocation, $death, $deathLocation, $parent1, $parent2, $id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when updating Person");
        }
    }

    function deletePerson($id){
        global $db;
        try{
            $query = "DELETE FROM Person WHERE Id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when deleting Person");
        }
    }

    /************************ Event ***************************/

    function insertEvent($date, $name, $location, $personId1, $personId2){
        global $db;
        try{
            $query = "INSERT INTO Event
                VALUES (?, ?, ?, ?, ?, ?)";
            $stmt = $db->prepare($query);
            $stmt->execute([null, $date, $name, $location, $personId1, $personId2]);
            return true;
        } catch (PDOException $e) {
            db_disconnect();
            exit("Aborting: There was a database error when inserting Event");
        }
    }

    function updateEvent($id, $date, $name, $location, $personId1, $personId2){
        global $db;
        try{
            $query = "UPDATE Event
                SET date = ?
                ,name = ?
                ,location = ?
                ,personId1 = ?
                ,personId2 = ?
                WHERE id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$date, $name, $location, $personId1, $personId2, $id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when updating Event");
        }
    }

    function deleteEvent($id){
        global $db;
        try{
            $query = "DELETE FROM Event WHERE Id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when deleting Event");
        }
    }

    /*************************** Relation *************************/

    function insertRelation($pid1, $pid2, $description){
        global $db;
        try{
            $query = "INSERT INTO Relation
                VALUES (?, ?, ?, ?, 0)";
            $stmt = $db->prepare($query);
            $stmt->execute([null, 1, 2, "test"]);
            return true;
        } catch (PDOException $e) {
            db_disconnect();
            exit("Aborting: There was a database error when inserting Relation");
        }
    }

    function updateRelation($id, $pid1, $pid2, $description, $isArchived){
        global $db;
        try{
            $query = "UPDATE Relation
                SET pid1 = ?
                ,pid2 = ?
                ,description = ?
                ,isArchived = ?
                WHERE id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$pid1, $pid2, $description, $isArchived, $id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when updating Relation");
        }
    }

    function archiveRelation($id){
        global $db;
        try{
            $query = "UPDATE Relation 
                SET isArchived = 1
                WHERE id = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when archiving Relation");
        }
    }

    /****************** User ******************/

    function createUser($username, $password, $permission){
        global $db;
        try{
            $query = "INSERT INTO User
                VALUES(?, ?, ?)";
            $stmt = $db->prepare($query);
            $stmt->execute([$username, $password, $permission]);
            return true;
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when inserting User");
        }
    }

    function getLogin($username, $password){
        global $db;
        try{
            $query = "SELECT * FROM User
                WHERE  username = ? AND password = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$username, $password]);  
            //will do check in front end if returned array length is 0 
            return $stmt->fetchALL(PDO::FETCH_ASSOC); 
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when getting Login");
        }
    }

    function getPermission($username){
        global $db;
        try{
            $query = "SELECT permission FROM User
                WHERE  username = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$username]);  
            //will do check in front end if returned array length is 0 
            $result = $stmt->fetch(PDO::FETCH_ASSOC); 
            return $result['permission'];
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: there was a database error when getting Permission");
        }
    }
?>