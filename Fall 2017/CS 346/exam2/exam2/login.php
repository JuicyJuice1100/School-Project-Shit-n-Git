<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>login</title>
</head>
<body>
    <form action="login.php" method="post">
        <label for="login">12 + 8 =<label>
        <input type="text" id="login" name="login" required/>
        <input id="submit" type="submit" value="Submit"/>
    </form>
    <?php 
        $submission = $_POST[("login")];
        if(isset($_POST['submit']))
        { 
            header('Location: login.php');
            if($submission === 20){
                echo "correct";
            }
            else{
                echo "incorrect";
            }
        }
    ?>      
</body>
</html>