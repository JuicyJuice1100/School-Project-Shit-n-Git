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
            <p>
                Dear <?php echo $_POST["firstName"]; ?>, 
            </p>
            <p>
                Thank you for your note about <?php echo $_POST["subject"]; ?> (see below),
                which I received at <?php date_default_timezone_set("America/Chicago"); echo date("h:i:s A"); ?> on <?php echo date("Y-m-d"); ?>.
                I will get back to you at <?php echo $_POST["email"]; ?> as soon as I can.
            </p>
            <p>
                Have a great day!
            </p>
            <p>
                Dr. Nip
            </p>
            <p id="phpNotes">
                Your note: <?php echo $_POST["myNotes"]; ?>
            </p>
        </div>
    </body>
</html>