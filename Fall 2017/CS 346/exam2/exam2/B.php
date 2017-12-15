<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Page B</title>
</head>
<body>
  <header>
    <h1>Page B</h1>
    <hr />
  </header>
  <p>This is page B!</p>
  <p> Goto <a href="A.php">Page A</a> or <a href="C.php">Page C</a></p>
  <?php   
    if (!isset($_COOKIE['B']))
    {
        $cookieB = 1;
        setcookie("B", $cookieB);
    }
    else
    {
        $cookieB = $_COOKIE['B'] + 1;
        setcookie("B", $cookieB);
    ?> 
  <?php include('footer.php'); ?>
</body>
</html>
