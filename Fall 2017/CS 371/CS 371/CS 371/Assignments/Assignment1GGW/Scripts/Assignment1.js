/*
* @authors:  Justin Espiritu, Tyler Bates
* GGW points are labels with GGW comments
*
*/
var gl;
var points;
var vertices = [];
var canvas;
var program;
var theta = 0.0;
var thetaLoc;
//GGW hardcoded the degree of rotation from vertices to vertices
var rotation = radians(45.0);

window.onload = function init() {
    canvas = document.getElementById("gl-canvas");

    gl = WebGLUtils.setupWebGL(canvas);  // More efficient
    if (!gl) {
        alert("WebGL isn't available");
    }


    for (var i = 0; i < 8; i++) {
        var x = 1.08 * Math.cos((2 * Math.PI / 8) * (i + .5));
        var y = 1.08 * Math.sin((2 * Math.PI / 8) * (i + .5));
        vertices.push(vec2(x, y));
    };

    //GGW we played around with the colors to to try tying certain colors to vertices
    var colors = [
        vec4(vertices[0][0] + 0.5, vertices[0][0], vertices[0][1] + 0.5, vertices[7][1]),
        vec4(vertices[1][0] + 0.5, 0.0, vertices[1][1] + 0.5, 1.0),
        vec4(vertices[2][0] + 0.5, 0.0, vertices[2][1] + 0.5, 1.0),
        vec4(vertices[3][0] + 0.5, 0.0, vertices[3][1] + 0.5, 1.0),
        vec4(vertices[4][0] + 0.5, 0.0, vertices[4][1] + 0.5, 1.0),
        vec4(vertices[5][0] + 0.5, 0.0, vertices[5][1] + 0.5, 1.0),
        vec4(vertices[6][0] + 0.5, 0.0, vertices[6][1] + 0.5, 1.0),
        vec4(vertices[7][0] + 0.5, 0.0, vertices[7][1] + 0.5, 1.0)
    ];

    gl.clearColor(0.0, 0.0, 0.0, 1.0);


    program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW);

    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);

    //GGW to load colors into the GPU
    var cBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vColor);

    thetaLoc = gl.getUniformLocation(program, "theta");

    render();
};

function render() {
    var sectionWidth = canvas.width / 6;
    var sectionHeight = canvas.height / 4;
    gl.clear(gl.COLOR_BUFFER_BIT);
    for (var i = 4; i > 0; i--) {
        for (var j = 0; j < 6; j++) {
            //GGW repeatedly the change in rotation uniform to the GPU
            gl.uniform1f(thetaLoc, theta);
            //GGW repeatdly rotate via the degrees of the vertices
            theta = rotation * j * i;
            gl.viewport(j * sectionWidth, i * sectionHeight, sectionWidth, sectionHeight);
            gl.drawArrays(gl.TRIANGLE_FAN, 0, 8);
        }
    }
}