let $d := doc("Banks.xml")

for $x in $d/BANKS/CUSTOMER
    for $y in $d/BANKS/BRANCH
        where $y/DEPOSITS/DEPOSIT/@cname = $y/LOANS/LOAN/@cname
        return <BANKCUSTOMER branch="{$y/@bname}" city="{($x/@city)}">{data($x/@cname)}</BANKCUSTOMER>