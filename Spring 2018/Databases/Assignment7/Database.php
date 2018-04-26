<?php
    require_once('DbCredentials.php');

    function db_connect(){
        try {
            $dbh = new PDO("mysql:dbname=".DB_NAME.";host=".DB_SERVER,
                        DB_USER, DB_PWD, array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
        } catch (PDOException $e){
            echo "This application exited with failure<br />" .
                "because there was an error when trying to<br />" .
                "connect to its database.<br />";
                exit();
        }
        return $dbh;
    }

    function db_disconnect() {
        global $db;
        if(isset($db)){
            $db = null;
        }
    }
?>