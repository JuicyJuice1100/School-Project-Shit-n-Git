declare variable $assets external;

let $d := doc("Banks.xml")

for $en in $d/BANKS/BRANCH
    for $x in distinct-values($en/LOANS/LOAN/@cname)
        where $en/ASSETS > number($assets)
        return $x