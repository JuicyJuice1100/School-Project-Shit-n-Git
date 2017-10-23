<?php 
    $questionNumber = $_POST[("questionNumber")];
    $points = $_POST[("points")];
    $chapter = $_POST[("chapter")];
    $question = $_POST[("question")];
    $questionType = $_POST[("questionType")];
    $image = $_POST[("uploadedImage")];
    ob_start();
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Question <?php echo $questionNumber ?></title>
        <meta charset="utf-8">
        <meta name="author" content="Justin Espiritu">
        <meta name="keywords" content="IndividualProject2">
        <meta name="description" content="CS 346 IndividualProject2">
        <meta name="date" content="10/19/2017">
        <link rel="stylesheet" type="text/css" href="IndividualProject2.css">
        <!-- this is for testing when it returns html back -->
        <link rel="stylesheet" type="text/css" href="../IndividualProject2.css">
        <?php 
            function answers($type){
                global $choiceA, $choiceB, $choiceC, $choiceD;
                $html = "\t\t\t<div id=\"response\">\n";
                if(intval($type) === 0){
                    $html .= "\t\t\t\t<label class=\"radio\">\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"true\" class=\"response\" name=\"response\">True\n";
                    $html .= "\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"radio\">\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"false\" class=\"response\" name=\"response\">False\n";
                    $html .= "\t\t\t\t</label>\n";
                }
                else if(intval($type) === 1){
                    $choiceA = $_POST[("choiceA")];
                    $choiceB = $_POST[("choiceB")];
                    $choiceC = $_POST[("choiceC")];
                    $choiceD = $_POST[("choiceD")];
                    $html .= "\t\t\t\t<label class=\"radio\">A)\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"choiceA\" class=\"response\" name=\"response\">";
                    $html .= $choiceA."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"radio\">B)\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"choiceB\" class=\"response\" name=\"response\">";
                    $html .= $choiceB."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"radio\">C)\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"choiceC\" class=\"response\" name=\"response\">";
                    $html .= $choiceC."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"radio\">D)\n";
                    $html .= "\t\t\t\t\t<input type=\"radio\" id=\"choiceD\" class=\"response\" name=\"response\">";
                    $html .= $choiceD."\n\t\t\t\t</label>\n";
                }
                else if(intval($type) === 2){
                    $choiceA = $_POST[("choiceA")];
                    $choiceB = $_POST[("choiceB")];
                    $choiceC = $_POST[("choiceC")];
                    $choiceD = $_POST[("choiceD")];
                    $html .= "\t\t\t\t<label class=\"checkbox\">A)\n";
                    $html .= "\t\t\t\t\t<input type=\"checkbox\" id=\"choiceA\" class=\"response\" name=\"response\">";
                    $html .= $choiceA."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"checkbox\">B)\n";
                    $html .= "\t\t\t\t\t<input type=\"checkbox\" id=\"choiceB\" class=\"response\" name=\"response\">";
                    $html .= $choiceB."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"checkbox\">C)\n";
                    $html .= "\t\t\t\t\t<input type=\"checkbox\" id=\"choiceC\" class=\"response\" name=\"response\">";
                    $html .= $choiceC."\n\t\t\t\t</label>\n";
                    $html .= "\t\t\t\t<label class=\"checkbox\">D)\n";
                    $html .= "\t\t\t\t\t<input type=\"checkbox\" id=\"choiceD\" class=\"response\" name=\"response\">";
                    $html .= $choiceD."\n\t\t\t\t</label>\n";
                }
                else{
                    $html .= "\t\t\t\t<input type=\"text\" id=\"shortAnswer\" placeholder=\"Type Answer Here...\" class=\"response\">\n";
                }
                $html .= "\t\t\t</div>\n";
                return $html;
            }

            function image($image){
                $html = "\t\t\t<img src=\"./files/".$image."\" alt=\"".$image."\">\n";
                return $html;
            }
        ?>
    </head>
    <body>
        <section id="phpOutput">
            <?php 
                $html = "<h1>Q".$questionNumber." | ".$points." Points | ";
                $html .= "Chapter ".$chapter."</h1>\n";
                $html .= "\t\t\t<p id=\"question\">".$question."</p>\n";
                if($image){
                    $html .= image($image);
                }
                $html .= answers($questionType);
                $html .= "\t\t\t<input id=\"submitButton\" type=\"submit\" value=\"Submit\"/>\n";
                echo $html;
            ?>
        </section>
    </body>
</html>

<?php                 
    $file = fopen("../Q".$questionNumber.".html", "c") or die("Cannot Open File");
    fwrite($file, ob_get_flush());
    fclose($file); 
?>