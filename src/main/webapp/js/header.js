$(document).ready(function(){

const homeImg = document.getElementsByClassName("leftside")[0].firstElementChild
homeImg.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/site/home"
})

const login = document.getElementsByClassName("account")[1]
login.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/accounts/secret"
})}