<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Justin Espiritu">
        <meta name="keywords" content="IndividualProject2">
        <meta name="description" content="CS 346 IndividualProject2">
        <meta name="date" content="10/19/2017">
        <link rel="stylesheet" type="text/css" href="IndividualProject2.css">
    </head>
    <body>
        <section id="phpOutput">
            <?php 
                $questionNumber = $_POST[("questionNumber")];
                $points = $_POST[("points")];
                $chapter = $_POST[("chapter")];
                $question = $_POST[("question")];
                $description = $_POST[("description")];
                $grading = $_POST[("gradingCriteria")];
                $html = "<h1>Q".$questionNumber." ".$points." points</h1>";
                $html .= "<label for=\"chapter\">Chapter</label>";
                $html .= "<p id=\"chapter\">".$chapter."<p>";
                $html .= "<label for=\"question\">Question:</label>";
                $html .= "<p id=\"question\">".$question."</p>";
                $html .= "<label for=\"description\">Description:</label>";
                $html .= "<p id=\"description\">".$description."</p>";
                $html .= "<label for=\"grading\">Grading:</label>";
                $html .= "<p id=\"grading\">".$grading."</p>";
                echo $html;
                $file = fopen("Q".$questionNumber.".html", "c") or die("Cannot Open File");
                fwrite($file, $html);
                fclose($file); 
            ?>
        </section>
    </body>
</html>