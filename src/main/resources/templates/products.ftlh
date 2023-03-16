<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4> Item all country</h4
<#list products as product>
    <div>
        <p>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее...</a></p>}
    </div>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post">
    Название обьявления: <input type="text" name="title"/><br><br>
    Описание обьявления: <input type="text" name="description"/><br><br>
    Цена обьявления: <input type="number" name="price"/><br><br>
    Город обьявления: <input type="text" name="city"/><br><br>
    Имя обьявления: <input type="text" name="author"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>


