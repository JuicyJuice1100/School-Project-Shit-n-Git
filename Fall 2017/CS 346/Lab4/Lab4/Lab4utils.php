<?php 
    $tableArray = array();
    $tableHeaderArray = array("");

    function tableHeaderArray($string){
        global $tableHeaderArray;
        foreach (count_chars($string, 1) as $char => $val){
            $tableHeaderArray[$val] = $val;
        }
        asort($tableHeaderArray);
    }

    function tableRowArray($string){
        global $tableArray;
        $keyArray = array($string);
        foreach (count_chars($string, 1) as $char => $val){
            $keyArray[$val] .= chr($char);
        }
        ksort($keyArray);
        array_unshift($tableArray, $keyArray);
    }

    function buildTable($header, $data){
        global $tableHeaderArray;
        $html = '<table><tr>';
        foreach($header as $header){
            $html .= '<th>' . $header . '</th>';
        }
        $html .= '</tr>';
        foreach($data as $array => $row){
            $html .= '<tr>';
            $wrote = false;
            foreach($row as $rowKey=>$rowValue){
                foreach($tableHeaderArray as $tableKey=>$tableValue){
                    if($rowKey === $tableKey && !$wrote){
                        $html .= '<td>' . $rowValue . '</td>';
                        $wrote = true;
                    }
                    elseif(end($row) === $rowValue && $wrote){
                        $html .= '<td></td>';
                    }
                }
                $wrote = false;
            }
        }
        $html .= '</table>';
        return $html;
    }

    function occurrence_table($wordsArray){
        global $tableHeaderArray, $tableArray;
        foreach ($wordsArray as $word){
            tableHeaderArray($word);
            @tableRowArray($word);
        }
        echo @buildTable($tableHeaderArray, $tableArray);
    }

    function beautify_CSS($file){
        $string = file_get_contents($file);
        $string = preg_replace('!\s+!', '', $string);
        $string = str_replace(array('{','}',';','h1','nav','h2', ':'), array('{<br>  ', '}<br><br>', ';<br>  ', 'h1 ', 'nav ', 'h2 ', ':  '), $string);
        $string = preg_replace('/\s+}/', '}', $string);
        echo "<pre>" . $string . "</pre>";
    }
?>