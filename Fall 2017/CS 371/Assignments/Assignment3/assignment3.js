/*
* @author: Justin Espiritu and Tyler Bates
GGW points are labeled with GGW comments
*/
var canvas;
var gl;
var program;

// moebius band generation
var nRows = 25;
var nColumns = 25;

// data for the parametric surface

var datax = [];
var datay = [];
var dataz = [];

var near = 0.3;
var far = 10.0;
var radius = 4.0; // Used to establish eye point
var theta  = 0.0; // Used to establish eye point
var phi    = 0.0; // Used to establish eye point
var rotation_by_5_deg = 5.0 * Math.PI/180.0;

var  fovy = 45.0;  // Field-of-view in Y direction angle (in degrees)
var  aspect;       // Viewport aspect ratio

var modelViewMatrix, projectionMatrix;
var modelViewMatrixLoc, projectionMatrixLoc;
var eye;			// Established by radius, theta, phi as we move

const black = vec4(0.0, 0.0, 0.0, 1.0);
const random = vec4(Math.random(), Math.random(), Math.random(), 1.0);

const at = vec3(0.0, 0.0, 0.0);
const up = vec3(0.0, 1.0, 0.0);

var rotation = 0.0;
var ballRotation = 0.0;
var x = 0.0;
var y = 1.0;

////////////////// Object 1 vertex information //////////////////  


var bufferPointer = 0;

var pointsArray1 = [];
var mobiusBandVerticies = [];

function coordsForObj1()
{
    for( var i = 0; i <= nRows; ++i ) {
        datax.push( [] );
        datay.push( [] );
        dataz.push( [] );
        var u = 2.0 * Math.PI * (i/nRows);
        console.log(u);
        
        for( var j = 0; j <= nColumns; ++j ) {
            var v = -0.3 + ((j/nColumns) * 0.6);
            datax[i].push(Math.cos(u) + v * Math.sin(u/2.0) * Math.cos(u));
            datay[i].push(Math.sin(u) + v * Math.sin(u/2.0) * Math.sin(u));
            dataz[i].push(v * Math.cos(u/2.0));
        }
    }
        
    for(var i=0; i<nRows; i++) {
        for(var j=0; j<nColumns;j++) {
            mobiusBandVerticies.push( vec4(datax[i][j], datay[i][j], dataz[i][j],1.0));
            mobiusBandVerticies.push( vec4(datax[i+1][j], datay[i+1][j], dataz[i+1][j], 1.0));
            mobiusBandVerticies.push( vec4(datax[i+1][j+1], datay[i+1][j+1], dataz[i+1][j+1], 1.0));
            mobiusBandVerticies.push( vec4(datax[i][j+1], datay[i][j+1], dataz[i][j+1], 1.0));
        }
    }
}

///////// End of vertex information for Object 1  ////////

window.onload = function init() {

    canvas = document.getElementById( "gl-canvas" );
    //    gl = WebGLUtils.setupWebGL( canvas );
    gl = WebGLDebugUtils.makeDebugContext( canvas.getContext("webgl") ); // For debugging
    if ( !gl ) { alert( "WebGL isn't available" ); }

    gl.viewport( 0, 0, canvas.width, canvas.height );


    aspect =  canvas.width/canvas.height;
    gl.clearColor( 0.0, 0.0, 0.0, 1.0 );
    gl.enable(gl.DEPTH_TEST);
    
    gl.depthFunc(gl.LEQUAL);
    gl.enable(gl.POLYGON_OFFSET_FILL);
    gl.polygonOffset(1.0, 2.0);
   
    //
    //  Load shaders and initialize attribute buffers
    //
    program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );
    
    coordsForObj1(); 
    
    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
    
    pointsArray1 = pointsArray1.concat(mobiusBandVerticies);
    
    for (var i = 0; i < buckyBall.length; i++) {
        pointsArray1 = pointsArray1.concat(buckyBall[i]);
    }

    gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsArray1), gl.STATIC_DRAW );
    
    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );
    
    modelViewMatrixLoc = gl.getUniformLocation( program, "modelViewMatrix" );
    projectionMatrixLoc = gl.getUniformLocation( program, "projectionMatrix" );
    
    // buttons for viewing parameters
    
    document.getElementById("Button1").onclick = function(){near  *= 1.02; far *= 1.02;};
    document.getElementById("Button2").onclick = function(){near *= 0.98; far *= 0.98;};
    document.getElementById("Button3").onclick = function(){radius *= 1.1;};
    document.getElementById("Button4").onclick = function(){radius *= 0.9;};
    document.getElementById("Button5").onclick = function(){theta += rotation_by_5_deg;};
    document.getElementById("Button6").onclick = function(){theta -= rotation_by_5_deg;};
    document.getElementById("Button7").onclick = function(){phi += rotation_by_5_deg;};
    document.getElementById("Button8").onclick = function(){phi -= rotation_by_5_deg;};
    
    render();
};

var render = function() {
    gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    
    // Object 1
    var eye = vec3( radius*Math.sin(theta)*Math.cos(phi), 
    radius*Math.sin(theta)*Math.sin(phi),
    radius*Math.cos(theta));

    //GGW translate away from the orgin on the z axis to have the so that the mobius strip will also rotate along with the 
    // bucky ball
    modelViewMatrix = lookAt(eye, at , up);
    modelViewMatrix = mult(modelViewMatrix, rotateY(-rotation));
    modelViewMatrix = mult(modelViewMatrix, translate(0.0,0.0,1.5));
    modelViewMatrix = mult(modelViewMatrix, scalem(0.5,0.5,0.5));
    modelViewMatrix = mult(modelViewMatrix, rotateY(rotation));
    projectionMatrix = perspective(fovy, aspect, near, far);

    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    //GGW we wanted to make the mobius strip look like rainbow road from mario kart 64 so we made the colors random
    for(var i=0; i<mobiusBandVerticies.length; i+=4) { 
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(Math.random(), Math.random(), Math.random(), 1.0)));
        gl.drawArrays( gl.TRIANGLE_FAN, i, 4 );
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(black));
        gl.drawArrays( gl.LINE_LOOP, i, 4 );
        bufferPointer += mobiusBandVerticies[i].length;
    }
    // The BuckyBall

    //GGW to make the ball rotate on the same axis as the mobius strip.  We also wanted to make the ball have top spin
    // so we added rotate before the translate
    modelViewMatrix = lookAt(eye, at , up);
    modelViewMatrix = mult(modelViewMatrix, rotateY(rotation));
    modelViewMatrix = mult(modelViewMatrix, translate(0, Math.cos(y) , -1.5));
    modelViewMatrix = mult(modelViewMatrix, scalem(0.01,0.01,0.01));
    modelViewMatrix = mult(modelViewMatrix, rotateX(-ballRotation));
    modelViewMatrix = mult(modelViewMatrix, rotateZ(ballRotation));
    projectionMatrix = perspective(fovy, aspect, near, far);

    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );
    

    // Draw buckyBall
    for (var i = 0; i < buckyBall.length - 12; i++){
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(1.0, 1.0, 1.0, 1.0)));
        gl.drawArrays( gl.TRIANGLE_FAN, bufferPointer, buckyBall[i].length);
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(0.0, 0.0, 0.0, 1.0)));
        gl.drawArrays( gl.LINE_LOOP, bufferPointer, buckyBall[i].length);
        bufferPointer += buckyBall[i].length;
    }
    //GGW to make sure people don't have "seizures", we only made the pentagons random colors and not the whole buckyball
    for(var i = 20; i < 32; i++){
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(Math.random(), Math.random(), Math.random(), 1.0)));
        gl.drawArrays( gl.TRIANGLE_FAN, bufferPointer, buckyBall[i].length);
        bufferPointer += buckyBall[i].length;
    }

    bufferPointer = 0;

    //GGW because one wasn't enough we wanted to make two buckyballs and mobius strips
    // to differentiate this mobius stip we had it rotate the other way
    modelViewMatrix = lookAt(eye, at , up);
    modelViewMatrix = mult(modelViewMatrix, rotateY(rotation));
    modelViewMatrix = mult(modelViewMatrix, translate(0.0,0.0,1.5));
    modelViewMatrix = mult(modelViewMatrix, scalem(0.5,0.5,0.5));
    modelViewMatrix = mult(modelViewMatrix, rotateY(-rotation));
    projectionMatrix = perspective(fovy, aspect, near, far);

    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    // draw each quad as two filled red triangles
    // and then as two black line loops

    for(var i=0; i<mobiusBandVerticies.length; i+=4) { 
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(Math.random(), Math.random(), Math.random(), 1.0)));
        gl.drawArrays( gl.TRIANGLE_FAN, i, 4 );
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(black));
        gl.drawArrays( gl.LINE_LOOP, i, 4 );
        bufferPointer += mobiusBandVerticies[i].length;
    }

    //GGW to help differentiate this from the other buckyball we had it spin and rotate in the opposite direction
    modelViewMatrix = lookAt(eye, at , up);
    modelViewMatrix = mult(modelViewMatrix, rotateY(-rotation));
    modelViewMatrix = mult(modelViewMatrix, translate(0, Math.sin(y) , -1.5));
    modelViewMatrix = mult(modelViewMatrix, scalem(0.01,0.01,0.01));
    modelViewMatrix = mult(modelViewMatrix, rotateX(ballRotation));
    modelViewMatrix = mult(modelViewMatrix, rotateZ(-ballRotation));
    projectionMatrix = perspective(fovy, aspect, near, far);

    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    //GGW to also help differentiate from the other we made the hexagons on this buckyball random colors to induce "seizures"
    // we also thought it was cool to have the pentagon missing so we just didn't drawthose so you can see through this buckyball
    for (var i = 0; i < buckyBall.length - 12; i++){
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(Math.random(), Math.random(), Math.random(), 1.0)));
        gl.drawArrays( gl.TRIANGLE_FAN, bufferPointer, buckyBall[i].length);
        gl.uniform4fv(gl.getUniformLocation(program, "fColor"), flatten(vec4(0.0, 0.0, 0.0, 1.0)));
        gl.drawArrays( gl.LINE_LOOP, bufferPointer, buckyBall[i].length);
        bufferPointer += buckyBall[i].length;
    }

    rotation += 1;
    ballRotation += 45;
    y -= .2;
    bufferPointer = 0;
    requestAnimFrame(render);
};