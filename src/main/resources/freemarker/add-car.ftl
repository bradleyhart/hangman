<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "Add Car">
<form action="add-car" method="post">
    <label for="make">Make: </label><input id="make" name="make" type="text"/>
    <label for="model">Model: </label><input id="model" name="model" type="text"/>
    <label for="year">Year: </label><input id="year" name="year" type="text"/>
    <label for="price">Price: </label><input id="price" name="price" type="text"/>
    <input id="add" type="submit" value="Add"/>
</form>
</@layout.defaultLayout>
