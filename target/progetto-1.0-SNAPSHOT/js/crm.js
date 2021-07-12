const hamburger = document.getElementsByClassName("topbar")[0].firstElementChild
hamburger.addEventListener('click', function(){
    const sidebar = document.getElementsByClassName("sidebar")[0]
    const content = document.getElementsByClassName("content")[0]
    sidebar.classList.toggle("collapse")
    content.classList.toggle("full-width")
})

const homeImg = document.getElementsByClassName("menu")[0].firstElementChild
homeImg.addEventListener('click', function(){
    window.location.href = "/progetto_war_exploded/site/home"
})



const notification = document.getElementsByClassName('notification')[0]
if (notification) {
    const notycross = document.getElementById('notification-close')
    notycross.addEventListener('click', function () {
        notification.style.display = 'none'
    })
}
