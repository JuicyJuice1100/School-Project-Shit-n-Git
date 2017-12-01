

// Key listener

var isFLast;
var isJump;

window.onkeydown = function(event) {
    var key = String.fromCharCode(event.keyCode);
    
    // For letters, the upper-case version of the letter is always
    // returned because the shift-key is regarded as a separate key in
    // itself.  Hence upper- and lower-case can't be distinguished.
    switch (key) {
    // case 'S':
    //     // Move backward
    //     console.log(hero.z);
    //     hero.move(-5.0);
    //     break;
    case 'F':
        if(!isFLast){
            hero.move(-4.0);
            isFLast = !isFLast;
        } else{
            villain.move(-1.0);
        }
        break;
    case 'J':
        if(isFLast){
            hero.move(-4.0);
            isFLast = !isFLast;
        } else{
            villain.move(-1.0);
        }
        break;
    case ' ':
        if(hero.y === 10){
            hero.y = hero.jump(20.0);
            isJump = true;
        } else{
            villain.move(-1.0);
        }
        break;
    // case 'R':
    //     if(!isLow){
    //         hero.jump(-100.0);
    //         isLow = !isLow;
    //     } else{
    //         villain.move(-1.0);
    //     }
    //     break;
    default:
        villain.move(-1.0);
    }
};