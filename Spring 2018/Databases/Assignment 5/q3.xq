let $d := doc("Banks.xml")

for $x in $d/BANKS/CUSTOMER
    for $y in $d/BANKS/CUSTOMER
        where $y/@addr = $x/@addr and $y/@cname != $x/@cname
        return <PAIR><NAME1>{data($y/@cname)}</NAME1><NAME2>{data($x/@cname)}</NAME2></PAIR>