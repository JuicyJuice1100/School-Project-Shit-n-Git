<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Justin Espiritu">
        <meta name="keywords" content="IndividualProject2">
        <meta name="description" content="CS 346 IndividualProject2">
        <meta name="date" content="10/19/2017">
        <link rel="stylesheet" type="text/css" href="IndividualProject2.css">
        <?php 
            $questionNumber = $_POST[("questionNumber")];
            $points = $_POST[("points")];
            $chapter = $_POST[("chapter")];
            $question = $_POST[("question")];
        ?>
    </head>
    <body>
        <section id="phpOutput">
            <?php 
                $html = "<h1>Q".$questionNumber." | ".$points." Points |";
                $html .= " Chapter ".$chapter."</h1>\n";
                $html .= "<label for=\"question\">Question:</label>\n";
                $html .= "<p id=\"question\">".$question."</p>\n";
                $html .= "<input id=\"submitButton\" type=\"submit\" value=\"Submit\"/>\n";
                echo $html;
                $file = fopen("Q".$questionNumber.".html", "c") or die("Cannot Open File");
                fwrite($file, $html);
                fclose($file); 
            ?>
        </section>
    </body>
</html>