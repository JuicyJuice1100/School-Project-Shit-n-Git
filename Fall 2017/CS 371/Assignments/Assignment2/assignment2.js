// fractal-by-ifs.js
// Generate fractal by iterating tranformations taken from an IFS specification
// The variable triangle is loaded from a JSON representation of the IFS specification

const NUM = 300;
var gl;
var goingToTriangle = true;
var tweenLoc;
var tweenFactor = 0.0;
var canvas;
var size = 0.15;

// triangleProjection matrix for WC window
var projectionMatrix;
var projection;

// vertices computed from the IFS transformations
var vertices = [];

// WC window will have to be adjusted based on the fractal's properties
// Those below work for Sierpinski's triangle
var triangleLeft = -3.5;
var triangleRight = 3.75;
var triangleBottom = -2.5;
var triangleTop = 4.0;

// Those below work for the dragon fractal
var dragonLeft = -10.0;
var dragonRight = 10.0;
var dragonBottom = 0.0;
var dragonTop = 10.0;

// Number of fractal points to generate
var numberOfPoints = 50000;

window.onload = function init(){
    canvas = document.getElementById( "gl-canvas" );
    
    //    gl = WebGLUtils.setupWebGL( canvas );
    gl = WebGLDebugUtils.makeDebugContext( canvas.getContext("webgl") ); // For debugging
    if ( !gl ) { alert( "WebGL isn't available" );
	       }


    ///////////////// Point generation completed ///////////////////////////////////////////////

    //  Configure WebGL
    
    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 0.0, 0.0, 0.0, 1.0 );

    //  Load shaders and initialize attribute buffers

    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    
    ///////////////// Generate the fractal points //////////////////////////////////////////////
    generateFractalPoints();
    // Load the data into the GPU

    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW );

    // Associate our shader variables with our data buffer

    // Note that we must change the second parameter to be consistent with the 3-dragonPoint coordinate
    var tPosition = gl.getAttribLocation( program, "tPosition" );
    gl.vertexAttribPointer(tPosition, 2, gl.FLOAT, false, 16, 0);
    gl.enableVertexAttribArray(tPosition);

    var dPosition = gl.getAttribLocation( program, "dPosition")
    gl.vertexAttribPointer(dPosition, 2, gl.FLOAT, false, 16, 8);
    gl.enableVertexAttribArray( dPosition );    
    
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

    debugger;
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
        
        // Select transformation trianglePoint
        trianglePoint = 0;
        dragonPoint = 0;
        while ((pointRandomizer > triangleCumulativeProb[trianglePoint]) && (trianglePoint < triangle.transformations.length - 1)){
            trianglePoint++;
        } 

        while ((pointRandomizer > dragonCumulativeProb[dragonPoint]) && (dragonPoint < dragon.transformations.length - 1)){
            dragonPoint++;
        }
        
        // Transform point by transformation trianglePoint 
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
        // Jump around for awhile without plotting to make
        //   sure the first point seen is attracted into the
        //   fractal
        if(iterations > 20){
            
            vertices.push(vec2(.28 * newTriangleX, .34 * newTriangleY - .3625));
            vertices.push(vec2(.16 * newDragonX, .185 * newDragonY - .9));
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

    if(goingToTriangle){
        tweenFactor = Math.min(tweenFactor + 0.0075, 1.0);
        projectionMatrix = ortho(dragonLeft, dragonRight, dragonBottom, dragonTop, -1.0, 1.0);
        gl.uniformMatrix4fv( projection, false, flatten(projectionMatrix));
        if (tweenFactor >= 1.0) {
            goingToTriangle = false;
            document.getElementById('captionForTheGoal').innerHTML="Dragon to Triangle";
        }
    }

    else {
        tweenFactor = Math.max(tweenFactor - 0.0075, 0.0);
        // projectionMatrix = ortho(dragonLeft, dragonRight, dragonBottom, dragonTop, -1.0, 1.0);
        // gl.uniformMatrix4fv(projection, false, flatten(projectionMatrix));
        if (tweenFactor <= 0.0){
            goingToTriangle = true;
            document.getElementById('captionForTheGoal').innerHTML="Triangle to Dragon";
        }
    }

    gl.uniform1f(tweenLoc, tweenFactor);
    gl.drawArrays( gl.POINTS, 0, vertices.length/2 );

    // Currently animation isn'trianglePoint needed, but perhaps in an assignment ...
    requestAnimFrame( render );
}