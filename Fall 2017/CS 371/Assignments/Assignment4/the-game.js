// the-game.js
/*****************************************
 * Justin Espiritu & Tyler Bates
 * 
 * GGW:  
 * When the hero loses the camera rotates around and looks at the villain.  All other objects freeze.
 * When the hero wins the camera rotates around and looks at the hero.   All other objects freeze.
 * When the villain collides with the VW the vw spins out of control.
 * When the hero collides with the VW the hero falls backwards, show via the camera, until it collides with the villain
 * If the hero makes any mistake the villain does get faster.
 * We have two extra lights, one on each of the flags for the check points.
 * Changed the size of the arena to make it more narrow to represent more of a hallway.
 * Implimented semi realistic jumping.
 * There is a score multiplier when the player crosses half way.
 * When the villain passes the middle checkpoint the villain begins to spin.
 */
var gl;
var canvas; 
const WALLHEIGHT     = 140.0; // Some playing field parameters
const ARENASIZE      = 1000.0;
var eyeHeight      = 10.0;
const HERO_VP        = 0.625;

const  upx=0.0, upy=1.0, upz=0.0;    // Some LookAt params 

const fov = 60.0;     // Perspective view params 
const near = 1.0;
const far = 10000.0;
var aspect, eyex, eyez, eyey;

const width = 1000;       // canvas size 
const height = 625;
const vp1_left = 0;      // Left viewport -- the hero's view 
const vp1_bottom = 0;

// Lighting stuff
var la0  = [ 0.2,0.2,0.2, 1.0 ]; // light 0 ambient intensity 
var ld0  = [ 1.0,1.0,1.0, 1.0 ]; // light 0 diffuse intensity 
var ls0  = [ 1.0,1.0,1.0, 1.0 ]; // light 0 specular 
var lp0  = [ 0.0,1.0,1.0, 1.0 ]; // light 0 position -- will adjust to hero's viewpoint 
var ma   = [ 0.02 , 0.2  , 0.02 , 1.0 ]; // material ambient 
var md   = [ 0.08, 0.6 , 0.08, 1.0 ]; // material diffuse 
var ms   = [ 0.6  , 0.7, 0.6  , 1.0 ]; // material specular 
var me      = 75;             // shininess exponent 
const red  = [ 1.0,0.0,0.0, 1.0 ]; // pure red 
const blue = [ 0.0,0.0,1.0, 1.0 ]; // pure blue 
const green = [ 0.0,1.0,0.0, 1.0 ]; // pure blue 
const yellow = [ 1.0,1.0,0.0, 1.0 ]; // pure yellow

var modelViewMatrix, projectionMatrix;
var modelViewMatrixLoc, projectionMatrixLoc;

var program;

var arena;
var hero;
var volkshagon;
var villain;
var checkpointFlag;
var checkpointFlag2;

var score = 0;

var g_matrixStack = []; // Stack for storing a matrix

var isAbove = true;
var isReloading;
var hitBox;
var isHit;
var rotation = 0.0;
var hasWon;

var prevX, prevY, prevZ;
var vWRotation = 0.0;
var villainHit;
var heroEaten;
var VILLAIN_SPEED = -1.5;
var multiplier = 1.0;

window.onload = function init(){
    canvas = document.getElementById( "gl-canvas" );
    
       gl = WebGLUtils.setupWebGL( canvas );
    // gl = WebGLDebugUtils.makeDebugContext( canvas.getContext("webgl") ); // For debugging
    if ( !gl ) { alert( "WebGL isn't available" ); }
    
    //  Configure WebGL
    
    gl.clearColor( 0.2, 0.2, 0.2, 1.0 );

    //  Load shaders and initialize attribute buffers

    program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

    eyex  = ARENASIZE/2.0;	// Where the hero starts
    eyez  = 0.0;

    villainDistance = 250;
   
    aspect=width/height;

    modelViewMatrixLoc = gl.getUniformLocation( program, "modelViewMatrix" );
    projectionMatrixLoc = gl.getUniformLocation( program, "projectionMatrix" );

    gl.uniform1i(gl.getUniformLocation(program, "texture_flag"),
		 0); // Assume no texturing is the default used in
                     // shader.  If your game object uses it, be sure
                     // to switch it back to 0 for consistency with
                     // those objects that use the defalt.
    
    
    arena = new Arena(program);
    arena.init();

    hero = new Hero(program, eyex, eyeHeight, eyez, -90, 10.0);
    hero.init();

    volkshagon = new ThingSeeking(program, ARENASIZE/2.0, -10.0, -ARENASIZE*1.5, 0, 10.0);
    volkshagon.init();
    hitBox = volkshagon.vwCoords(vwMesh);

    villain = new Villain(program, ARENASIZE/2.0, 0.0, eyez+250, 0, 10.0);
    villain.init();
    
    checkpointFlag = new Checkpoint(program, ARENASIZE * .4, 0.0, -ARENASIZE/2, 0, 10.0);
    checkpointFlag.init();

    checkpointFlag2 = new Checkpoint(program, ARENASIZE*.6, 0.0, -ARENASIZE/2, 0, 10.0);
    checkpointFlag2.init();

    render();
};

function render()
{
    gl.clear( gl.COLOR_BUFFER_BIT || gl.DEPTH_BUFFER_BIT);

    
    // Hero's eye viewport 
    gl.viewport( vp1_left, vp1_bottom, (HERO_VP * width), height );
    
    lp0[0] = hero.x + hero.xdir; // Light in front of hero, in line with hero's direction
    lp0[1] = eyeHeight;
    lp0[2] = hero.z + hero.zdir;
    modelViewMatrix = lookAt( vec3(hero.x, hero.y, hero.z),
			      vec3(hero.x + hero.xdir, hero.y, hero.z + hero.zdir),
                  vec3(upx, upy, upz) );
                  
    //constantly rotate camera when player wins
    if(hasWon){
        prevX = hero.x;
        prevY = hero.y;
        prevZ = hero.z;
        modelViewMatrix = mult(modelViewMatrix, translate(0.0, -10.0, -ARENASIZE/4));
        modelViewMatrix = mult(modelViewMatrix, translate(prevX, prevY, prevZ));
        // modelViewMatrix = mult(modelViewMatrix, rotateX(-45.0));
        modelViewMatrix = mult(modelViewMatrix, rotateY(rotation));
        modelViewMatrix = mult(modelViewMatrix, translate(-hero.x, -hero.y, -hero.z));
        rotation += 1.0;
        (function win(){
            var body = document.getElementById("body");
            var image = document.createElement("img");
            var node = document.createTextNode("test");
            image.appendChild(node);
            image.id = "image";
            image.src = "MattFirst.bmp";
            image.style.margin = 0 + "px";
            image.style.padding = 0 + "px";
            image.style.position = "absolute";
            image.style.zIndex = -1;
            image.style.right = Math.random() * 2000 + "px";
            image.style.left = Math.random() * 2000 + "px";
            image.style.top = Math.random() * 2000 + "px";
            image.style.bottom = Math.random() * 2000 + "px";
            body.appendChild(image);
        }());
    }

    if(heroEaten){
        prevX = hero.x;
        prevY = hero.y;
        prevZ = hero.z;
        modelViewMatrix = mult(modelViewMatrix, translate(0.0, -20.0, -ARENASIZE/2));
        modelViewMatrix = mult(modelViewMatrix, translate(prevX, prevY, prevZ));        
        modelViewMatrix = mult(modelViewMatrix, rotateX(-45.0));
        modelViewMatrix = mult(modelViewMatrix, rotateY(rotation));
        modelViewMatrix = mult(modelViewMatrix, translate(-hero.x, -hero.y, -hero.z));
        rotation += 1.0;
        (function win(){
            var body = document.getElementById("body");
            var image = document.createElement("img");
            var node = document.createTextNode("test");
            image.appendChild(node);
            image.id = "image";
            image.src = "Luke.jpg";
            image.style.margin = 0 + "px";
            image.style.padding = 0 + "px";
            image.style.position = "absolute";
            image.style.zIndex = -1;
            image.style.right = Math.random() * 2000 + "px";
            image.style.left = Math.random() * 2000 + "px";
            image.style.top = Math.random() * 2000 + "px";
            image.style.bottom = Math.random() * 2000 + "px";
            body.appendChild(image);
        }());
    }

    //Will cause the hero to fall backwards when hit by volkshagon
    if(isHit && !heroEaten){
        // modelViewMatrix = mult(modelViewMatrix, translate(0.0, volkshagon.y + 10.0, 0.0));
        modelViewMatrix = mult(modelViewMatrix, rotateX(rotation));
        hero.move(15.0);            
        rotation += 1.0;
    }
    

    projectionMatrix = perspective( fov, HERO_VP * aspect, near, far );
    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    arena.show();
    hero.show();
    volkshagon.show();
    villain.show();
    checkpointFlag.show();
    checkpointFlag2.show();
    
    // Overhead viewport 
    var horiz_offset = (width * (1.0 - HERO_VP) / 20.0);
    gl.viewport( vp1_left + (HERO_VP * width) + horiz_offset ,
		 vp1_bottom, 18 * horiz_offset, height );
    modelViewMatrix = lookAt(  vec3(500.0,100.0,-500.0),
			       vec3(500.0,0.0,-500.0),
			       vec3(0.0,0.0,-1.0) );
    projectionMatrix = ortho( -500,500, -500,500, 0,200 );
    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    //change score
    document.getElementById("score").innerHTML = score;
    
    if(!hasWon && !heroEaten){
        villain.move(VILLAIN_SPEED);
        volkshagon.move(7.5);
    }
    
    //A check to see if the hero hit the jump button while on the ground
    if(isJump){
        hero.y = Math.min(250, hero.jump(25));
        if(hero.y >= 250){
            isJump = false;
        }
    }

    //check if hero is grounded
    if(!isJump){
        //gravity for jumping
        hero.y = Math.max(10, hero.gravity());
    }

    //check if hero is eaten by villain
    if(eatenCheck(hero, villain)){
        heroEaten = true;
    }

    //check to see if hero made it to the end
    if(finishLine(hero)){
        hasWon = true;
    }

    //a check to see if the hero has gotten hit by the car
    if(hitByCarCheck(hero) && !isHit){
        isHit = true;
    }

    //check to see if villain hit car
    if(hitByCarCheck(villain)){
        villainHit = true;
    }

    if(hero.z <= -500){
        multiplier = 4;
    }

    arena.show();
    hero.show();
    volkshagon.show();
    villain.show();
    checkpointFlag.show();
    checkpointFlag2.show();

    requestAnimFrame( render );
};

