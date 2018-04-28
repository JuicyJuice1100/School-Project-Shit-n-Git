<?php
    require_once 'Queries.php';
    require_once 'DbCredentials.php';
    require_once 'Initialize.php';
    $data = [];
    $error = '';

    if(isset($_POST["query1"])){
        $lastName = $_POST["query1"];
        $data = getMembersByLastName($lastName);
    } else if(isset($_POST["query2"])){
        $id = $_POST["query2"];
        $data = getParentById($id);
    } else if(isset($_POST["query3"])){
        $id = $_POST["query3"];
        $data = getChildrenById($id);
    } else if(isset($_POST["query4"])){
        $id = $_POST["query4"];
        $data = getSiblingsById($id);
    } else if(isset($_POST["query5"])){
        $id = $_POST["query5"];
        $data = getGrandParentsById($id);
    } else if(isset($_POST["query6"])){
        $id = $_POST["query6"];
        $data = getGrandChildrenById($id);
    } else if(isset($_POST["query7"])){
        $id = $_POST["query7"];
        $data = getSpouseById($id);
    } else if(isset($_POST["query8"])){
        $id = $_POST["query8"];
        $data = getEventById($id);
    } else if(isset($_POST["query9"])){
        $input = $_POST["query9"];
        $data = getPersonbyKeyword($input);
    } else if(isset($_POST["query10"])){
        $date = $_POST["query10"];
        $date = strtotime($date);
        $date = date('Y-m-d', $date);
        $data = getEventByDate($date);
    } else if (isset($_POST["insertPerson"])){
        $insertPersongender                 = $_POST["insertPersongender"];
        $insertPersonbio                    = $_POST["insertPersonbio"];
        $insertPersonfirstName              = $_POST["insertPersonfirstName"];
        $insertPersonmiddleName             = $_POST["insertPersonmiddleName"];
        $insertPersonlastName               = $_POST["insertPersonlastName"];
        $insertPersonlastKnownAddress       = $_POST["insertPersonlastKnownAddress"];
        $insertPersonlastKnownPhonenumber   = $_POST["insertPersonlastKnownPhonenumber"];
        $insertPersonbirthDate              = $_POST["insertPersonbirthDate"];
        if($insertPersonbirthDate != NULL){
            $insertPersonbirthDate = strtotime($insertPersonbirthDate);
            $insertPersonbirthDate = date('Y-m-d', $insertPersonbirthDate);
        }
        $insertPersonbirthLocation          = $_POST["insertPersonbirthLocation"];
        $insertPersondeathDate              = $_POST["insertPersondeathDate"];
        if($insertPersondeathDate != NULL){
            $insertPersondeathDate = strtotime($insertPersondeathDate);
            $insertPersondeathDate = date('Y-m-d', $insertPersondeathDate);
        }
        $insertPersondeathLocation          = $_POST["insertPersondeathLocation"];
        $insertPersonparent1                = $_POST["insertPersonparent1"] != NULL ? $_POST["insertPersonparent1"] : NULL;
        $insertPersonparent2                = $_POST["insertPersonparent2"] != NULL ? $_POST["insertPersonparent2"] : NULL;
        $data = insertPerson( $insertPersonbio,
        $insertPersongender,            
        $insertPersonfirstName,   
        $insertPersonmiddleName,     
        $insertPersonlastName,    
        $insertPersonlastKnownAddress,
        $insertPersonlastKnownPhonenumber,
        $insertPersonbirthDate,      
        $insertPersonbirthLocation,   
        $insertPersondeathDate,     
        $insertPersondeathLocation,    
        $insertPersonparent1,    
        $insertPersonparent2);

    } else if (isset($_POST["updatePerson"])){
        $updatePersonpersonId               = $_POST["updatePersonpersonId"];
        $updatePersongender                 = $_POST["updatePersongender"];
        $updatePersonbio                    = $_POST["updatePersonbio" ];
        $updatePersonfirstName              = $_POST["updatePersonfirstName" ];
        $updatePersonmiddleName             = $_POST["updatePersonmiddleName" ];
        $updatePersonlastName               = $_POST["updatePersonlastName" ];
        $updatePersonlastKnownAddress       = $_POST["updatePersonlastKnownAddress" ];
        $updatePersonlastKnownPhonenumber   = $_POST["updatePersonlastKnownPhonenumber" ];
        $updatePersonbirthDate              = $_POST["updatePersonbirthDate" ];
        if($updatePersonbirthDate != NULL){
            $updatePersonbirthDate = strtotime($updatePersonbirthDate);
            $updatePersonbirthDate = date('Y-m-d', $updatePersonbirthDate);
        }
        $updatePersonbirthLocation          = $_POST["updatePersonbirthLocation" ];
        $updatePersondeathDate              = $_POST["updatePersondeathDate" ];
        if($updatePersondeathDate != NULL){
            $updatePersondeathDate = strtotime($updatePersondeathDate);
            $updatePersondeathDate = date('Y-m-d', $updatePersondeathDate);
        }
        $updatePersondeathLocation          = $_POST["updatePersondeathLocation" ];
        $updatePersonparent1                = $_POST["updatePersonparent1" ] != NULL ? $_POST["updatePersonparent1" ] : NULL;
        $updatePersonparent2                = $_POST["updatePersonparent2" ] != NULL ? $_POST["updatePersonparent2" ] : NULL;
        $data = updatePerson($updatePersonpersonId,
        $updatePersongender,            
        $updatePersonbio,        
        $updatePersonfirstName,   
        $updatePersonmiddleName,     
        $updatePersonlastName,    
        $updatePersonlastKnownAddress,
        $updatePersonlastKnownPhonenumber,
        $updatePersonbirthDate,      
        $updatePersonbirthLocation,   
        $updatePersondeathDate,     
        $updatePersondeathLocation,    
        $updatePersonparent1,    
        $updatePersonparent2);
        
    } else if (isset($_POST["deletePerson"])){
        $deletePersonId = $_POST["deletePersonId"];
        $data = deletePerson($deletePersonId);

    } else if (isset($_POST["insertEvent"])){
        $insertEventdate = $_POST["insertEventdate"];
            if($insertEventdate != NULL){
                $insertEventdate = strtotime($insertEventdate);
                $insertEventdate = date('Y-m-d', $insertEventdate);
            }
        $insertEventname = $_POST["insertEventname"];
        $insertEventlocation = $_POST["insertEventlocation"];
        $insertEventPersonId1 = $_POST["insertEventPersonId1"];
        $insertEventPersonId2 = $_POST["insertEventPersonId2"] != NULL ? $_POST["insertEventPersonId2"] : NULL;
        $data = insertEvent($insertEventdate, $insertEventname, 
        $insertEventlocation, $insertEventPersonId1, $insertEventPersonId2);

    } else if (isset($_POST["updateEvent"])){
        $updateEventId = $_POST["updateEventId"];
        $updateEventdate = $_POST["updateEventdate"];
        if($updateEventdate != NULL){
            $updateEventdate= strtotime($updateEventdate);
            $updateEventdate = date('Y-m-d', $updateEventdate);
        }
        $updateEventname = $_POST["updateEventname"];
        $updateEventlocation = $_POST["updateEventlocation"];
        $updateEventPersonId1 = $_POST["updateEventPersonId1"];
        $updateEventPersonId2 = $_POST["updateEventPersonId2"] != NULL ? $_POST["updateEventPersonId2"] : NULL;
        $data = updateEvent($updateEventId, $updateEventdate, $updateEventname, 
        $updateEventlocation, $updateEventPersonId1, $updateEventPersonId2);
        
    } else if (isset($_POST["deleteEvent"])){
        $deleteEventId = $_POST["deleteEventId"];
        $data = deleteEvent($deleteEventId);


// **********************************************************
    } else if (isset($_POST["insertRelation"])){
        $insertRelationPid1 = $_POST["insertRelationPid1"];
        $insertRelationPid2 = $_POST["insertRelationPid2"];
        $insertRelationDescription = $_POST["insertRelationDescription"];
        $data = insertRelation($insertRelationPid1, $insertRelationPid2, 
        $insertRelationDescription);
    } else if (isset($_POST["updateRelation"])){
        $updateRelationId = $_POST["updateRelationId"];
        $updateRelationPid1 = $_POST["updateRelationPid1"];
        $updateRelationPid2 = $_POST["updateRelationPid2"];
        $updateRelationDescription = $_POST["updateRelationDescription"];
        $updateRelationIsArchived = $_POST["updateRelationIsArchived"];
        $data = updateRelation($updateRelationId, $updateRelationPid1, 
        $updateRelationPid2, $updateRelationDescription, $updateRelationIsArchived);
    } else if (isset($_POST["archiveRelation"])){
        $archiveRelationId = $_POST["archiveRelationId"];
        $data = archiveRelation($archiveRelationId);
    } else {
        $error = "Please input a value for one of the forms";
    }
?>