// fractal-by-ifs.js
// Generate fractal by iterating tranformations taken from an IFS specification
// The variable triangle is loaded from a JSON representation of the IFS specification

var gl;
var goingToDragon = true;
var tweenLoc;
var tweenFactor = 0.0;
var canvas;


var projectionMatrix;
var projection;


var vertices = [];


var triangleLeft = -3.5;
var triangleRight = 3.75;
var triangleBottom = -2.5;
var triangleTop = 4.0;

var dragonLeft = -10.0;
var dragonRight = 10.0;
var dragonBottom = 0.0;
var dragonTop = 10.0;


var numberOfPoints = 50000;

window.onload = function init(){
    canvas = document.getElementById( "gl-canvas" );
    
    //    gl = WebGLUtils.setupWebGL( canvas );
    gl = WebGLDebugUtils.makeDebugContext( canvas.getContext("webgl") ); // For debugging
    if ( !gl ) { alert( "WebGL isn't available" );
	       }


    generateFractalPoints();

    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 0.0, 0.0, 0.0, 1.0 );



    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    


    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW );


    var tPosition = gl.getAttribLocation( program, "tPosition" );
    gl.vertexAttribPointer(tPosition, 2, gl.FLOAT, false, 16, 0);
    gl.enableVertexAttribArray(tPosition);

    var dPosition = gl.getAttribLocation( program, "dPosition")
    gl.vertexAttribPointer(dPosition, 2, gl.FLOAT, false, 16, 8);
    gl.enableVertexAttribArray( dPosition );    
    
    projection = gl.getUniformLocation (program, "projection");
    tweenLoc = gl.getUniformLocation(program, "tween");

    render();
};


function generateFractalPoints () {

    var iterations, trianglePoint, dragonPoint;
    var oldTriangleX = oldDragonX = 0;
    var oldTriangleY = oldDragonY = 0;
    var newTriangleX, newTriangleY, newDragonX, newDragonY, pointRandomizer;
    var triangleCumulativeProb = [];
    var dragonCumulativeProb = [];


    dragonCumulativeProb.push(dragon.transformations[0].prob);
    triangleCumulativeProb.push(triangle.transformations[0].prob);
    
    for (var i = 1; i < triangle.transformations.length; i++){
	    triangleCumulativeProb.push(triangleCumulativeProb[i-1] + triangle.transformations[i].prob); // Make probability cumulative
    }

    for(var i = 1; i < dragon.transformations.length; i++){
        dragonCumulativeProb.push(dragonCumulativeProb[i-1] + dragon.transformations[i].prob);
    }

    iterations = 0;
    while (iterations < numberOfPoints)
    {
	    pointRandomizer = Math.random();
        

        trianglePoint = 0;
        dragonPoint = 0;
        while ((pointRandomizer > triangleCumulativeProb[trianglePoint]) && (trianglePoint < triangle.transformations.length - 1)){
            trianglePoint++;
        } 

        while ((pointRandomizer > dragonCumulativeProb[dragonPoint]) && (dragonPoint < dragon.transformations.length - 1)){
            dragonPoint++;
        }
        

        newTriangleX = triangle.transformations[trianglePoint].rotate_scalexx*oldTriangleX
            + triangle.transformations[trianglePoint].rotate_scalexy*oldTriangleY
            + triangle.transformations[trianglePoint].trans_x;
        newTriangleY = triangle.transformations[trianglePoint].rotate_scaleyx*oldTriangleX
            + triangle.transformations[trianglePoint].rotate_scaleyy*oldTriangleY
            + triangle.transformations[trianglePoint].trans_y;
        
        newDragonX = dragon.transformations[dragonPoint].rotate_scalexx*oldDragonX
            + dragon.transformations[dragonPoint].rotate_scalexy*oldDragonY
            + dragon.transformations[dragonPoint].trans_x;

        newDragonY = dragon.transformations[dragonPoint].rotate_scaleyx*oldDragonX
            + dragon.transformations[dragonPoint].rotate_scaleyy*oldDragonY
            + dragon.transformations[dragonPoint].trans_y;

        if(iterations > 20){
            
            vertices.push(vec2(newTriangleX, newTriangleY ));
            vertices.push(vec2(newDragonX, newDragonY ));
        }


        oldTriangleX = newTriangleX;
        oldTriangleY = newTriangleY;
        oldDragonX = newDragonX;
        oldDragonY = newDragonY;

        iterations++;
    }
};

function render() {
    gl.clear( gl.COLOR_BUFFER_BIT );

    
    if(goingToDragon){
        tweenFactor = Math.min(tweenFactor + 0.001, 1.0);
        projectionMatrix = ortho((1.0 - tweenFactor) * triangleLeft + dragonLeft * tweenFactor, (1.0 - tweenFactor) * triangleRight + dragonRight * tweenFactor, 
            (1.0 - tweenFactor) * triangleBottom + dragonBottom * tweenFactor, (1.0 - tweenFactor) * triangleTop + dragonTop * tweenFactor, -1.0, 1.0);
        if (tweenFactor >= 1.0) {
            goingToDragon = false;
            document.getElementById('captionForTheGoal').innerHTML="Dragon to Triangle";
        }
    }

    else {
        tweenFactor = Math.max(tweenFactor - 0.001, 0.0);
        projectionMatrix = ortho((1.0 - tweenFactor) * triangleLeft + dragonLeft * tweenFactor, (1.0 - tweenFactor) * triangleRight + dragonRight * tweenFactor, 
            (1.0 - tweenFactor) * triangleBottom + dragonBottom * tweenFactor, (1.0 - tweenFactor) * triangleTop + dragonTop * tweenFactor, -1.0, 1.0);
        

        if (tweenFactor <= 0.0){
            goingToDragon = true;
            document.getElementById('captionForTheGoal').innerHTML="Triangle to Dragon";
        }
    }
    gl.uniformMatrix4fv(projection, false, flatten(projectionMatrix));
    gl.uniform1f(tweenLoc, tweenFactor);
    gl.drawArrays( gl.POINTS, 0, vertices.length/2 );
    requestAnimFrame( render );
}