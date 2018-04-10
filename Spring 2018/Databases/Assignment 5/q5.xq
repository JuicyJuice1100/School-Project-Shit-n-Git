let $d := doc("Banks.xml")

for $x in $d/BANKS/BRANCH
return <BANKLOANS loans="{count($x/LOANS/LOAN)}" totalbalance="{sum($x/LOANS/LOAN/AMOUNT)}" branch="{$x/@bname}"/>