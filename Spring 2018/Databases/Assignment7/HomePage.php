<?php
    session_start();
	include('CallQuery.php');
?>

<html lang="en">
	<head>
		<title>Family Tree Home</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="mystyle.css">
	</head>

    <body>
	<!-- <header><?php echo $_SESSION['permission'];?></header> -->
    Insert Person:
    <form action="" method="post"> 
		<div>
			<label>Bio
				<input type="text" name='insertPersonbio' value="">
			</label>
		</div>
		<div>
			<label>Gender
				<input type="text" name='insertPersongender' value="">
			</label>
		</div>
		<div>
			<label>First Name
				<input type="text" name='insertPersonfirstName' value=""> 
			</label>
		</div>
		<div>
			<label>Middle Name
				<input type="text" name='insertPersonmiddleName' value="">
			</label>
		</div>
		<div>
			<label>Last Name
				<input type="text" name='insertPersonlastName' value="">
			</label>
		</div>
		<div>
			<label> Last Known Address
				<input type="text" name='insertPersonlastKnownAddress' value="">
			</label>
		</div>
		<div>
			<label>Last Known Phonenumber
				<input type="text" name='insertPersonlastKnownPhonenumber' value="">
			</label>
		</div>
		<div>
			<label>Birth Date
				<input type="text" name='insertPersonbirthDate' value="">
			</label>
		</div>
		<div>
			<label>Birth Location
				<input type="text" name='insertPersonbirthLocation'>
			</label>
		</div>
		<div>
			<label>Death Date
				<input type="text" name='insertPersondeathDate'>
			</label>
		</div>
		<div>
			<label>Death Location
				<input type="text" name='insertPersondeathLocation'>
			</label>
		</div>
		<div>
			<label>Parent1
				<input type="number" name='insertPersonparent1'>
			</label>
		</div>
		<div>
			<label>Parent2
				<input type="number" name='insertPersonparent2'>
			</label>
		</div>
		<input type="submit" name="insertPerson">
    </form>

    Update Person:
    <form action="" method="post"> 
		<div>
			<label>Id
				<input type="number" name='updatePersonpersonId'>
			</label>
		</div>
		<div>
			<label>Bio
				<input type="text" name='updatePersonbio'>
			</label>
		</div>
		<div>
			<label>Gender
				<input type="text" name='updatePersongender'>
			</label>
		</div>
		<div>
			<label>First Name
				<input type="text" name='updatePersonfirstName'>
			</label>
		</div>
		<div>
			<label>Middle Name
				<input type="text" name='updatePersonmiddleName'>
			</label>
		</div>
		<div>
			<label>Last Name
				<input type="text" name='updatePersonlastName'>
			</label>
		</div>
		<div>
			<label> Last Known Address
				<input type="text" name='updatePersonlastKnownAddress'>
			</label>
		</div>
		<div>
			<label>Last Known Phonenumber
				<input type="text" name='updatePersonlastKnownPhonenumber'>
			</label>
		</div>
		<div>
			<label>Birth Date
				<input type="text" name='updatePersonbirthDate'>
			</label>
		</div>
		<div>
			<label>Birth Location
				<input type="text" name='updatePersonbirthLocation'>
			</label>
		</div>
		<div>
			<label>Death Date
				<input type="text" name='updatePersondeathDate'>
			</label>
		</div>
		<div>
			<label>Death Location
				<input type="text" name='updatePersondeathLocation'>
			</label>
		</div>
		<div>
			<label>Parent1
				<input type="number" name='updatePersonparent1'>
			</label>
		</div>
		<div>
			<label>Parent2
				<input type="number" name='updatePersonparent2'>
			</label>
		</div>
		<input type="submit" name="updatePerson">
    </form>

    Delete Person:
    <form action="" method="post">
		<label>Id
			<input type="number" name='deletePersonId'>
		</label>
		<input type="submit" name="deletePerson">
	</form>
	
	Insert Event:
	<form action="" method="post">
		<div>
			<label>Date
				<input type="text" name='insertEventdate'>
			</label>
		</div>
		<div>
			<label>Name
				<input type="text" name='insertEventname'>
			</label>
		</div>
		<div>
			<label>Location
				<input type="text" name='insertEventlocation'>
			</label>
		</div>
		<div>
			<label>PersonId1
				<input type="number" name='insertEventPersonId1'>
			</label>
		</div>
		<div>
			<label>PersonId2
				<input type="number" name='insertEventPersonId2'>
			</label>
		</div>
		<input type="submit" name="insertEvent">
	</form>

	Update Event:
	<form action="" method="post">
		<div>
			<label>Id
				<input type="number" name='updateEventId'>
			</label>
		</div>
		<div>
			<label>Date
				<input type="text" name='updateEventdate'>
			</label>
		</div>
		<div>
			<label>Name
				<input type="text" name='updateEventname'>
			</label>
		</div>
		<div>
			<label>Location
				<input type="text" name='updateEventlocation'>
			</label>
		</div>
		<div>
			<label>PersonId1
				<input type="number" name='updateEventPersonId1'>
			</label>
		</div>
		<div>
			<label>PersonId2
				<input type="number" name='updateEventPersonId2'>
			</label>
		</div>
		<input type="submit" name="updateEvent">
	</form>

	Delete Event:
	<form action="" method="post">
		<div>
			<label> Id
				<input type="number" name="deleteEventId">
			</label>
		</div>
		<input type="submit" name="deleteEvent">
	</form>

	Insert Relation:
	<form action="" method="post">
		<div>
			<label>PersonId 1
				<input type="number" name='insertRelationPid1'>
			</label>
		</div>
		<div>
			<label>PersonId 2
				<input type="number" name='insertRelationPid2'>
			</label>
		</div>
		<div>
			<label>Description
				<input type="text" name='insertRelationDescription'>
			</label>
		</div>
		<input type="submit" name="insertRelation">
	</form>

	Update Relation:
	<form action="" method="post">
		<div>
			<label>Id
				<input type="number" name='updateRelationId'>
			</label>
		</div>
		<div>
			<label>PersonId 1
				<input type="number" name='updateRelationPid1'>
			</label>
		</div>
		<div>
			<label>PersonId 2
				<input type="number" name='updateRelationPid2'>
			</label>
		</div>
		<div>
			<label>Description
				<input type="text" name='updateRelationDescription'>
			</label>
		</div>
		<div>
			<label> Is Archived
				<input type="number" name="updateRelationIsArchived">
			</label>
		</div>
		<input type="submit" name="updateRelation">
	</form>

	Archive Relation:
	<form action="" method="post">
		<div>
			<label> Id
				<input type="number" name="archiveRelationId">
			</label>
		</div>
		<input type="submit" name="archiveRelation">
	</form>

    Query1 --> full names:
    <form action="" method="post">
        <input type="text" name="query1">
		<input type="submit">	
    </form>

    Query2 --> parents:
    <form action="" method="post">
        <input type="text" name="query2">
		<input type="submit">
    </form>

    Query3 --> children:
    <form action="" method="post">
        <input type="text" name="query3">
		<input type="submit">
    </form>

    Query4 --> siblings:
    <form action="" method="post">
        <input type="text" name="query4">
		<input type="submit">
    </form>

    Query5 --> grandparents:
    <form action="" method="post">
        <input type="text" name="query5">
		<input type="submit">
    </form>

    Query6 --> grandchildren:
    <form action="" method="post">
        <input type="text" name="query6">
		<input type="submit">
    </form>

    Query7 --> current spouse:
    <form action="" method="post">
        <input type="text" name="query7">
		<input type="submit">
    </form>

    Query8 --> events:
    <form action="" method="post">
        <input type="text" name="query8">
		<input type="submit">
    </form>

    Query9 --> keyword:
    <form action="" method="post">
        <input type="text" name="query9">
		<input type="submit">
    </form>

    Query10 --> events on a date:
    <form action="" method="post">
        <input type="text" name="query10">
		<input type="submit">
	</form>
	
	<span><?php print_r($data) ?></span>
	<span><?php echo $error?></span>
</body>

</html>
