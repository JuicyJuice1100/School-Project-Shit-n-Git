<?php
    function getMembersByLastName($lastName){
        global $db;
        try {
            $query = "SELECT firstName, middleName, lastName FROM Person 
                WHERE lastName = ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$lastName]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
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
            $stmt = $db->perpare($query);
            $stmt->execute([$id]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
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
            $stmt->execute([$id]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing childrens");
        }
    }

    function getSiblingsById($id){
        global $db;
        try {
            $query = "SELECT firstName, middleName, lastName FROM Person p1
                JOIN (SELECT parent1, parent2 FROM Person WHERE id = ?) p2 ON p2.parent1 = p1.parent1 
                AND p2.parent = p1.parent2"
            $stmt = $db->prepare($query);
            $stmt->execute([$id]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing siblings");
        }
    }

    function getGrandParentsById($id){
        global $db;
        try {
            $view = "CREATE VIEW getParents AS SELECT * FROM Person
                WHERE id = (SELECT parent1 FROM Person WHERE id = ? OR
                id = (SELECT parent2 FROM Person WHERE id = ?);"
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while creating view");
        } 
        try {
            $query = "SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
                JOIN getParents ON getParents.parent1 = Person.id or getParents.parent2 = Person.id"
            $stmt = $db->prepare($query);
            $stmt->execute([]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while listing grandparents")
        } finally {
            $clean = "DROP VIEW getParents";
            $stmt = $db->prepare($clean);
            $stmt->execute([]);
        }
    }

    function getGrandChildrenById($id){
        global $db;
        try{
            $view = "CREATE VIEW getChildren AS SELECT * FROM Person
                WHERE parent1 = ? OR parent2 = ?;"
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: Error while creating view");
        }
        try {
            $query = "SELECT Person.firstName, Person.middleName, Person.lastName FROM Person
                JOIN getChildren ON getChildren.id = Person.parent1 or getchildren.id = Person.parent2";
            $stmt = $db->prepare($query);
            $stmt->execute([]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing grandchildren");
        } finally {
            $clean = "DROP VIEW getChildren";
            $stmt = $db->prepare($clean);
            $stmt->execute([])
        }
    }

    function getSpouseById($id){
        global $db;
        try{
            $query = "SELECT firstName, middleName, lastName FROM Person
                JOIN Relation ON Person.id = pid1 OR Person.id = pid2
                WHERE (pid1 = ? or pid2 = ?) AND isArchived = 0 AND description = 'married AND Person.id <> ?";
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id, $id]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing spouse");
        }
    }

    function getEventById($id){
        global $db;
        try{
            $query = "SELECT date, name, location FROM Event
                    WHERE personId1 = ? OR personId2 = ?"
            $stmt = $db->prepare($query);
            $stmt->execute([$id, $id]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing events");
        }
    }

    function getPersonByKeyword($keyword){
        global $db;
        try{
            $like = '%'.$keyword.'%'
            $query = "SELECT firstName, middleName, lastName FROM Person
                    WHERE bio LIKE ?"
            $stmt = $db->prepare($query);
            $stmt->execute([$like]);
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_disconnect();
            exit("Aborting: error while listing person");
        }
    }

    function getEventByDate($date){
        global $db;
        try{
            $query = "SELECT date, name, location FROM Event 
                    WHERE date = ?"
            $stmt = $db->prepare($query);
            $stmt->execut([$date])
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e){
            db_discconect();
            exit("Aborting: error while listing events");
        }
    }

    /*********************************************************************************/

    
?>