eatenCheck = function(hero, villain){
    return hero.z >= villain.z;
}

finishLine = function(hero){
    return hero.z <= -ARENASIZE;
}

hitByCarCheck = function(hero){
    return hero.z <= volkshagon.z + hitBox["zMax"] && hero.z >= volkshagon.z + hitBox["zMin"] && hero.y < volkshagon.y + hitBox["yMax"];
}