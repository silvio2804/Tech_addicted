const homeImg = document.getElementsByClassName("leftside")[0].firstElementChild
homeImg.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/site/home"
})

const login = document.getElementsByClassName("account")[1]
login.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/accounts/signin"
})

const form = document.getElementById('myForm')
const btn = document.getElementById('sc')
btn.addEventListener('click', function () {

    form.classList.toggle('block')

})


const cart = document.getElementById("cart")
cart.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/carts/show"
})