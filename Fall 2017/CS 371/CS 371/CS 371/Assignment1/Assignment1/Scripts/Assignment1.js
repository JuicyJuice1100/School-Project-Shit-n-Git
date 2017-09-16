﻿/* assignement1.js

*/
var gl;
var points;
var vertices = [];
var canvas;
var program;

window.onload = function init(){
    canvas = document.getElementById( "gl-canvas" );
    
    gl = WebGLUtils.setupWebGL( canvas );  // More efficient
    //gl = WebGLDebugUtils.makeDebugContext( canvas.getContext("webgl") ); // For debugging
    if ( !gl ) { alert( "WebGL isn't available" );
               }

    
    for (var i = 0; i < 8; i++) {
        var x = 1.08 * Math.cos((2 * Math.PI / 8) * (i + .5));
        var y = 1.08 * Math.sin((2 * Math.PI / 8) * (i + .5));
        vertices.push(vec2(x, y));
    };
    
    
    //  Configure WebGL
    
    //gl.viewport( 0, 0, canvas.width/6, canvas.height/4 );
    gl.clearColor( 0.0, 0.0, 0.0, 1.0 );

    //  Load shaders and initialize attribute buffers using A/S utility initShaders

    program = initShaders( gl, "vertex-shader", "fragment-shader" ); 
    gl.useProgram( program );

    // Load the data into the GPU using A/S flatten function

    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW ); 
                                                                         

    // Associate our shader variables with our data buffer

    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer(
        vPosition, // Specifies the index of the generic vertex attribute to be modified.
        2,         // Specifies the number of components per generic vertex attribute. 
                   // Must be 1, 2, 3, or 4. 
        gl.FLOAT,  // Specifies the data type of each component in the array. 
            // GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT, GL_FIXED, or GL_FLOAT. 
        false,     // Specifies whether fixed-point data values should be normalized (GL_TRUE) 
            // or converted directly as fixed-point values (GL_FALSE) when they are accessed.
        0,         // Specifies the byte offset between consecutive generic vertex attributes. 
            // If stride is 0, the generic vertex attributes are understood 
            // to be tightly packed in the array.
        0          // Specifies a pointer to the first component 
            // of the first generic vertex attribute in the array.
    );

    gl.enableVertexAttribArray( vPosition );    
    
    render();
};

function render() {
    var sectionWidth = canvas.width / 6;
    var sectionHeight = canvas.height / 4;
    gl.clear(gl.COLOR_BUFFER_BIT);
    for (var i = 4; i > 0; i--) {
        for (var j = 0; j < 6; j++) {
            gl.viewport(j * sectionWidth, i * sectionHeight, sectionWidth, sectionHeight);
            gl.drawArrays(gl.LINE_LOOP, 0, 8);
        }
    }  
}