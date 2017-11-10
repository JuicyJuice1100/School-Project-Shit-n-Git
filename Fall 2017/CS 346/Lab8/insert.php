<?php

  require_once('./initialize.php');

  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // this is a POST request and thus a form submission: process the form data
    if(insert_question($_POST['ID'],$_POST['desc'],$_POST['points'])){
      echo "question inserted";
    }
    db_disconnect();
  }

?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Lab 8 Entry form</title>
</head>
<body>

  <div id="content">
    <h1>Create a new question</h1>

    <form action="insert.php" method="post">
      ID: <input type="text" name="ID" /><br />

      Description:
      <input type="text" name="desc" size="60"/><br />

      Points:      <input type="text" name="points" size="3"/><br />

      <input type="submit" name="submit" value="Create Question"  />
    </form>

  </div>

</body>
</html>
