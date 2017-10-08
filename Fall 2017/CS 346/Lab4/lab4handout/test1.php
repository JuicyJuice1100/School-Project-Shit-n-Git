<?php require_once("Lab4utils.php") ?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Lab 4</title>
    <link rel="stylesheet" type="text/css" href="Lab4styles.css" />
  </head>
  <body>

            <h1>Letter count</h1>
            <?php occurrence_table([ "mississippi", "cat"]); ?>
            <h1>CSS beautifier</h1>
            <?php beautify_CSS("styles.css") ?>

  </body>
</html>
