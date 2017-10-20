var questionType = function(){
    var element = document.getElementById("answers");
    var questionType = document.getElementById("questionType").value;

    if(questionType === 0 || questionType === 3){
        element.style.display = "none";
    }
    else{
        element.style.disply = "block";
    }
};
