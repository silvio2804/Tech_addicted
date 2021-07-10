<form class="app grid-x justify-center align-center" action="/progetto_war_exploded/site/home" method="post"> <!-- qui chiama la servlet crm in http/crm/dashboard!-->
    <fieldset class="grid-y cell w33 login">

    <span style="text-align: center; margin: 0px"><h2 >Registrazione</h2></span>

    <label for="name" class="field cell w33">
    <input id="name" name="name" placeholder="Nome" type="text">
    </label>

    <label for="lastName" class="field cell w33">
    <input id="lastName" name="lastName" placeholder="Cognome" type="text">
    </label>

    <label for="dataNa" class="field cell w33" >
    <input id="dataNa" name="dataNa" placeholder="data di nascita" type="date">
    </label>

    <label for="street" class="field cell w33">
    <input id="street" name="street" placeholder="Via" type="text">
    </label>

    <label for="city" class="field cell w33">
    <input id="city" name="city" placeholder="Citta" type="text">
    </label>

    <label for="houseNumber" class="field cell w33">
    <input id="houseNumber" name="houseNumber" placeholder="Numero civico" type="text">
    </label>

    <label for="email" class="field cell w33">
    <input id="email" name="email" placeholder="Email" type="email">
    </label>

    <label for="emailCheck" class="field cell w 40">
    <input id="emailCheck" name="emailCheck" placeholder="Conferma email" type="email">
    </label>

    <label for="passwordCheck" class="field cell w33" >
    <input id="passwordCheck" name="passwordCheck" placeholder="Conferma password" type="text">
    </label>

    <label for="password" class="field cell w33" >
    <input id="password" name="password" placeholder="password" type="text">
    </label>

    <button type="submit" class="cell w33 btn primary">Registrati</button>

    </fieldset>
    </form>