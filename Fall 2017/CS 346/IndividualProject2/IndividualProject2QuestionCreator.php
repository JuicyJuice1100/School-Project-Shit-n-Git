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
            $questionType = $_POST[("questionType")];

            function answers($type){
                global $choiceA, $choiceB, $choiceC, $choiceD;
                $html = "<section>";
                $html .= "<div id=\"response\">";
                if(intval($type) === 0){
                    $html .= "<label class=\"radio\">True";
                    $html .= "<input type=\"radio\" id=\"true\" class=\"response\" name=\"response\">";
                    $html .= "</label>";
                    $html .= "<label class=\"radio\">False";
                    $html .= "<input type=\"radio\" id=\"false\" class=\"response\" name=\"response\">";
                    $html .= "</label>";
                }
                else if(intval($type) === 1){
                    $choiceA = $_POST[("choiceA")];
                    $choiceB = $_POST[("choiceB")];
                    $choiceC = $_POST[("choiceC")];
                    $choiceD = $_POST[("choiceD")];
                    $html .= "<label class=\"radio\">A)";
                    $html .= "<input type=\"radio\" id=\"choiceA\" class=\"response\" name=\"response\">";
                    $html .= $choiceA."</label>";
                    $html .= "<label class=\"radio\">B)";
                    $html .= "<input type=\"radio\" id=\"choiceB\" class=\"response\" name=\"response\">";
                    $html .= $choiceB."</label>";
                    $html .= "<label class=\"radio\">C)";
                    $html .= "<input type=\"radio\" id=\"choiceC\" class=\"response\" name=\"response\">";
                    $html .= $choiceC."</label>";
                    $html .= "<label class=\"radio\">D)";
                    $html .= "<input type=\"radio\" id=\"choiceD\" class=\"response\" name=\"response\">";
                    $html .= $choiceD."</label>";
                }
                else if(intval($type) === 2){
                    $choiceA = $_POST[("choiceA")];
                    $choiceB = $_POST[("choiceB")];
                    $choiceC = $_POST[("choiceC")];
                    $choiceD = $_POST[("choiceD")];
                    $html .= "<label class=\"checkbox\">A)";
                    $html .= "<input type=\"checkbox\" id=\"choiceA\" class=\"response\" name=\"response\">";
                    $html .= $choiceA."</label>";
                    $html .= "<label class=\"checkbox\">B)";
                    $html .= "<input type=\"checkbox\" id=\"choiceB\" class=\"response\" name=\"response\">";
                    $html .= $choiceB."</label>";
                    $html .= "<label class=\"checkbox\">C)";
                    $html .= "<input type=\"checkbox\" id=\"choiceC\" class=\"response\" name=\"response\">";
                    $html .= $choiceC."</label>";
                    $html .= "<label class=\"checkbox\">D)";
                    $html .= "<input type=\"checkbox\" id=\"choiceD\" class=\"response\" name=\"response\">";
                    $html .= $choiceD."</label>";
                }
                else{
                    $html .= "<input type=\"text\" id=\"shortAnswer\" placeholder=\"Type Answer Here...\" class=\"response\">";
                }
                $html .= "<div id=\"response\">";
                $html .= "</section>";
                return $html;
            }
        ?>
    </head>
    <body>
        <section id="phpOutput">
            <?php 
                $html = "<h1>Q".$questionNumber." | ".$points." Points |";
                $html .= " Chapter ".$chapter."</h1>\n";
                $html .= "<label for=\"question\">Question:</label>\n";
                $html .= "<p id=\"question\">".$question."</p>\n";
                $html .= answers($questionType);
                $html .= "<input id=\"submitButton\" type=\"submit\" value=\"Submit\"/>\n";
                echo $html;
                // $file = fopen("Q".$questionNumber.".html", "c") or die("Cannot Open File");
                // fwrite($file, $html);
                // fclose($file); 
                // $thisFile = readfile("IndividualProject2QuestionCreator.php");
                // $file = fopen("Q".$questionNumber.".html", "c") or die("Cannot Open File");
                // fwrite($file, $thisFile);
                // fclose($file); 
                $file = "Q".$questionNumber.".html";
                $thisFile = "IndividualProject2QuestionCreator.php";
                copy($thisFile, $file);
            ?>
        </section>
    </body>
</html>