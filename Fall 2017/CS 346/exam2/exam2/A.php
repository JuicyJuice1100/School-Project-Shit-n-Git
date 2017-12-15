<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Page A</title>
</head>
<body>
  <header>
    <h1>Page A</h1>
    <hr />
  </header>
  <p>This is page A!</p>
  <p> Goto <a href="B.php">Page B</a> or <a href="C.php">Page C</a></p>
  <?php 
    if (!isset($_COOKIE['A']))
    {
        $cookieA = 1;
        setcookie("A", $cookieA);
    }
    else
    {
        $cookieA = $_COOKIE['A'] + 1;
        setcookie("A", $cookieA);
    ?> 
  <?php include('footer.php'); ?> 
</body>
</html>
