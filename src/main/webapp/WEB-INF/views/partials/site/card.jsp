<div class="card">
    <img src="${product.cover}" alt="Immagine prodotto" style="width:100%">
    <h1>${product.name}</h1>
    <p class="price">${product.price}</p>
    <p>
    <form action="/site/product">
        <input type="hidden" value="${product.productId}">
        <button>Visualizza prodotto</button>
    </form>
    </p>
</div>