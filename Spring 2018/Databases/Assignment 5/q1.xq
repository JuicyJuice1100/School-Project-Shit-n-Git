
for $en in distinct-values(doc("Banks.xml")/BANKS/BRANCH/DEPOSITS/DEPOSIT/@cname)
    order by $en descending
    return $en