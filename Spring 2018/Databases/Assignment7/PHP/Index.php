<?php
    if(isset($_SESSION['permission']))
        session_destroy(); 
    include('Login.php'); // Includes Login Script
?>

<html lang="en">
	<head>
		<title>Family Tree Login</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>

    <body>
        <header>Family Tree</header>

        <div id="login">
            <div id="username">
            <form class="box" action="" method="post">
            
                <div class="formTitle">Welcome User</div>
                
                <div class="textInput">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" size="12" value=""/>
                </div><br />
                
                <div class="textInput"> 
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" size="12" value="" />
                </div>
                
                <div class="centered">
                <input id='submit' type='submit' name='submit' value='Log In'  />
                </div>

            </form>
            </div>
        </div>

        <div id="login">
            <div id="username">
            <form class="box" action="" method="post">
            
                <div class="formTitle">Create User</div>
                
                <div class="textInput">
                <label for="username">Username:</label>
                <input type="text" id="createUsername" name="createUsername" size="12" value=""/>
                </div><br />
                
                <div class="textInput"> 
                <label for="password">Password:</label>
                <input type="password" id="createPassword" name="createPassword" size="12" value="" />
                </div>

                <div class="textInput"> 
                    <label for="reenter">Password:</label>
                    <input type="password" id="reenter" name="reenterPassword" size="12" value="" />
                </div>

                <div class="radiobutton">
                <label for="permission">Permission:</label>
                <input type="radio" id="permission" name="permission" value="0">Read 
                <input type="radio" id="permission" name="permission" value="1">Write
                <input type="radio" id="permission" name="permission" value="2">Admin
                <div class="centered">
                <input id='createSubmit' type='submit' name='createSubmit' value='Create'  />
                </div>
                


            </form>
            </div>
        </div>
        <span><?php echo $error; ?></span>
	</body>
</html>
