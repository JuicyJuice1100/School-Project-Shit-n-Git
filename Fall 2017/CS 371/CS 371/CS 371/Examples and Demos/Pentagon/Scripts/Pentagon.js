// pentagon-with-line.js

// Add event listeners (button and key) to rotate the pentagon

// Rotation amount control by a uniform variable theta

// Smooth shading done by a color vector interpolated in the fragment shader

// A white line outlines the smooth shaded pentagon.  This line must
// be drawn after the shaded pentagon.  Why?

var gl;
var points;
var rotation_by_5_deg = radians(5.0);
var theta = 0.0;
var thetaLoc;
var program;
var vertices = [];

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");

    //    gl = WebGLUtils.setupWebGL( canvas );
    gl = WebGLDebugUtils.makeDebugContext(canvas.getContext("webgl")); // For debugging
    if (!gl) {
        alert("WebGL isn't available");
    }
    // Five Vertices

    var sweepAngle = 72.0; // Use radians function from Angel's MV library to convert
    vertices = [
        vec3(0.5, 0.0, 0.0),
        vec3(0.5 * Math.cos(radians(sweepAngle)), 0.5 * Math.sin(radians(sweepAngle)), 0.0),
        vec3(0.5 * Math.cos(2.0 * radians(sweepAngle)), 0.5 * Math.sin(2.0 * radians(sweepAngle)), 0.0),
        vec3(0.5 * Math.cos(3.0 * radians(sweepAngle)), 0.5 * Math.sin(3.0 * radians(sweepAngle)), 0.0),
        vec3(0.5 * Math.cos(4.0 * radians(sweepAngle)), 0.5 * Math.sin(4.0 * radians(sweepAngle)), 0.0)
    ];

    // Associate a RGBA color with each vertex
    var colors = [
        vec4(vertices[0][0] + 0.5, 0.0, vertices[0][2] + 0.5, 1.0),
        vec4(vertices[1][0] + 0.5, 0.0, vertices[1][2] + 0.5, 1.0),
        vec4(vertices[2][0] + 0.5, 0.0, vertices[2][2] + 0.5, 1.0),
        vec4(vertices[3][0] + 0.5, 0.0, vertices[3][2] + 0.5, 1.0),
        vec4(vertices[4][0] + 0.5, 0.0, vertices[4][2] + 0.5, 1.0)
    ];


    //  Configure WebGL

    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);

    //  Load shaders and initialize attribute buffers

    program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Load the data into the GPU

    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW);

    // Associate our shader variables with our data buffer

    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 3, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);

    // Load the RGBA color data into the GPU

    var cBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vColor);

    thetaLoc = gl.getUniformLocation(program, "theta");

    // Establish what we do when each of the buttons are clicked
    document.getElementById("CLButton").onclick = function () { theta -= rotation_by_5_deg; };
    document.getElementById("CCButton").onclick = function () { theta += rotation_by_5_deg; };

    render();
};

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);

    // Get the rotation uniform to the GPU
    gl.uniform1f(thetaLoc, theta);
    // Here the line is drawn last to ensure it is highly visible
    // Use the vColor attribute
    gl.uniform1i(gl.getUniformLocation(program, "smooth_flag"),
        1);
    gl.drawArrays(gl.TRIANGLE_FAN, 0, 5);
    // Don't use the vColor attribute
    gl.uniform1i(gl.getUniformLocation(program, "smooth_flag"),
        0);
    gl.drawArrays(gl.LINE_LOOP, 0, vertices.length);

    requestAnimFrame(render);
};

// Key listener

window.onkeydown = function (event) {
    var key = String.fromCharCode(event.keyCode);
    // For letters, the upper-case version of the letter is always
    // returned because the shift-key is regarded as a separate key in
    // itself.  Hence upper- and lower-case can't be distinguished.
    switch (key) {
        case 'L':
            theta -= rotation_by_5_deg;
            break;
        case 'C':
            theta += rotation_by_5_deg;
            break;
    }
};
