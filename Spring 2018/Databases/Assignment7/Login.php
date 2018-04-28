<?php    
    require_once 'Queries.php';
    require_once 'DbCredentials.php';
    require_once 'Initialize.php';
    $error = '';


    if (isset($_POST['submit'])) {
        if (empty($_POST['username']) || empty($_POST['password'])) {
            $error = "Error: username or password field is empty";
        }
        else{
            // Define $username and $password
            $username=$_POST['username'];
            $password=$_POST['password'];
            $login = getLogin($username, $password);
                if (empty($login)) {
                    $error = "Error Student : username or password is incorrect";
                } else {
                    $_SESSION['permission'] = getPermission($username);
                    header("location: HomePage.php"); // Redirecting To Other Page
                }
        }
    } else if(isset($_POST['createSubmit'])) {
        if(empty($_POST['createUsername']) || empty($_POST['createPassword']) 
            || empty($_POST['reenterPassword']) || empty($_POST['permission'])){
            $error = "Error: Must fill out all fields for create username";
        } else if($_POST['createPassword'] != $_POST['reenterPassword']){
            $error = "Error: Passwords must match";
        } else {
            $username=$_POST['createUsername'];
            $password=$_POST['createPassword'];
            $permission=$_POST['permission'];
            $user = createUser($username, $password, $permission);
        }
    }
?>