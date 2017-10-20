<?php 
    $tableArray = array();
    $tableHeaderArray = array("");
    $expression = $_POST[("expression")]);
    $cmin = $_POST[("cmin")];
    $cmax = $_POST[("cmax")];
    $rmin = $_POST[("rmin")];
    $rmax = $_POST[("rmax")];

    function tableHeaderArray($min, $max){
        global $tableHeaderArray;
        array_push($tableHeaderArray, $_POST[("expression")]);
        for($min; $min <= $max; $min++){
            array_push($tableHeaderArray, $min);
        }
    }

    function tableRowArray($min, $max){
        global $tableArray;
        $cmin = $_POST[("cmin")];
        $cmax = $_POST[("cmax")];
        for($min; $min <= $max; $min++){
            $temp = array();
            array_push($temp, $min);
            for($cmin; $cmin <= $cmax; $cmin++){
                $expression = $min.$_POST[("expression")].$cmin;
                $expression = eval($expression);
                array_push($temp, $expression);
            }
            array_push($tableArray, $temp);
        }
    }

    function buildTable(){
        global $tableHeaderArray;
        global $tableArray;
        $html = '<table><tr>';
        foreach($tableHeaderArray as $Header){
            $html .= '<th>' . $header . '</th>';
        }
        $html .= '</tr>';
        foreach($tableArray as $array => $row){
            $html .= '<tr>';
            foreach($array as $output){
                $html .= '<td>' . $output . '</td>';
            }
        }
        $html .= '</table>';
        return $html;
    }
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Justin Espiritu">
        <link rel="stylesheet" type="text/css" href="two.css">
    </head>
    <body>
        <?php 
            tableHeaderArray($cmin, $cmax);
            tableRowArray($rmin, $rmax);
            buildTable();
        ?>
    </body>
</html>
