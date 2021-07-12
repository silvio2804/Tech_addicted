ajax({
    url: '/progect_war_exploded/categories/api',
    method: 'GET',
    success: function (data) {
        const select = document.getElementById("categoryId");
        for (let index in data.categories) {
            let option = document.createElement("option");
            let optionText = document.createTextNode(data.categories[index].label);
            option.setAttribute("value", data.categories[index].id);
            option.appendChild(optionText);
            select.appendChild(option);
        }
    },
    error: function (err){
        console.error(err);
    }
});

