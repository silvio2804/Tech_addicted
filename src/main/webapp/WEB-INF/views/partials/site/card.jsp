<div class="card">
    <img src="/progetto_war_exploded/covers/${product.cover}" alt="Immagine prodotto" style="width:100%">
    <h1>${product.name}</h1>
    <p class="price">${product.price}</p>
    <p>
    <form action="/progetto_war_exploded/products/showCli">
        <input type="hidden" name="id" value="${product.productId}">
        <button type="submit">Visualizza prodotto</button>
    </form>
    </p>
</div>