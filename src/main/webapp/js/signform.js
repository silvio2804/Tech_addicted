var x = document.getElementById("password");
var y = document.getElementById("passwordCheck");

function checkPass (x,y){
    if(x === y){
        console.log("le due password coincidono !");
    }
    else{
        document.getElementById("passwordCheck").style.color='red';
    }
}

var k = document.getElementById("email");
var z = document.getElementById("emailCheck");

function checkEmail(k,z){
    if(k === z){
        console.log("le due email coincidono !");
    }
    else{
        document.getElementById("emailCheck").style.color='red';
    }
}



