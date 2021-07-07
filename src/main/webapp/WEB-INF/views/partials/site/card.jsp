<div class="card">
    <img src="${product.image}" alt="Immagine prodotto" style="width:100%">
    <h1>${product.name}</h1>
    <p class="price">${product.price}</p>
    <p>
    <form action="/site/product">
        <input type="hidden" value="${product.id}">
        <button>Visualizza prodotto</button>
    </form>
    </p>
</div>