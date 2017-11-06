var loginValidation = function(){
    var validate = document.getElementById("userName").value;
    return (validate === "student" || validate === "instructor");
}

var checkPermissions = function(){
    var permission = document.getElementById("userName").value || null;
    var loginAction = document.getElementById("loginForm");
    /* Checking to see if username is student or instructor
    * temporarily only using student or instructor, case sensitive, as usernames
    * will use actually usernames once we have a database 
    */
    if(loginValidation()){
        if(permission === "student"){
            loginAction.action = "StudentHomePage.html";
        } 
        else{
            loginAction.action = "InstructorHomePage.html";
        }
    }
}
