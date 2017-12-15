/* This function must make an asynchronous AJAX call to
 * http://webdev.cs.uwosh.edu/students/furcyd/Exam2/T3/get_random_color.php
 * and use the returned JSON-encoded array containing three color components
 * between 0 and 255 to build a color that must then become the background color
 * for the box element in T3.html
 */
function get_random_color() {
  var ajax = new XMLHttpRequest();
  ajax.onreadystatechange = function () {
      if (ajax.readyState == 4) {
          var color = JSON.parse(ajax.reponseText);
      }
      ajax.open("GET", "http://webdev.cs.uwosh.edu/student/furcyd/Exam2/T3/get_random_color.php", true);
      ajax.send(null);
      var box = document.getElementById("box");
      box.style.backgroundColor = "rgb(" + color[0] + "," + color[1] + "," + color[2] + ")"; 
  }
}

/* Implement any additional code (if any needed) below.
   Do not include any code that is not required to solve Task 3.
 */
