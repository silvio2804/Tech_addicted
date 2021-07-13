function checkPass() {
    var x = document.registerForm.password.value;
    var y = document.registerForm.checkPassword.value;
    var z = document.registerForm.email.value;
    var w = document.registerForm.checkEmail.value;


    if (z != w) {
        alert("le due email non coincidono!");
        return false;
    }


    if (x != y) {
        alert("le due password non coincidono!");
        return false;
    } else {
        document.registerForm.action("progetto_war_exploded/accounts/signup");
        document.registerForm.submit()
    }
}






