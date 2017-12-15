<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Page C</title>
</head>
<body>
  <header>
    <h1>Page C</h1>
    <hr />
  </header>
  <p>This is page C!</p>
  <p> Goto <a href="A.php">Page A</a> or <a href="B.php">Page B</a></p>
  <?php    
    if (!isset($_COOKIE['C']))
    {
        $cookieC = 1;
        setcookie("C", $cookieC);
    }
    else
    {
        $cookieC = $_COOKIE['C'] + 1;
        setcookie("C", $cookieC);
    ?> 
  <?php include('footer.php'); ?>
</body>
</html>
