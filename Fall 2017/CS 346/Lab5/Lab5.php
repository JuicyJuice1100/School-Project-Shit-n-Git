<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Justin Espiritu">
        <meta name="keywords" content="Lab5">
        <meta name="description" content="CS 346 Lab5">
        <meta name="date" content="10/11/2017">
        <link rel="stylesheet" type="text/css" href="Lab5.css">
        <link href='http://fonts.googleapis.com/css?family=Oleo+Script' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div id="phpOutput">
            <?php 
                $firstName = $_POST[("firstName")];
                $subject = $_POST[("subject")];
                $email = $_POST[("email")];
                $myNotes = $_POST[("myNotes")];
                $html = "<p>Dear " . htmlspecialchars($firstName) . "</p>
                <p>Thank you for your note about" . htmlspecialchars($subject) . "(see below), 
                which I received at " . date_default_timezone_set("America/Chicago") . date("h:i:s A") . 
                " on " . date("Y-m-d") . " I will get back to you at " . htmlspecialchars($email) . " as soon as I can. 
                </p><p>Have a great day!</p><p>Dr. Nip</p>";
                echo $html;
            ?>
            <p id="phpNotes">
                Your note: <?php echo htmlspecialchars($myNotes); ?>
            </p>
        </div>
    </body>
</html>