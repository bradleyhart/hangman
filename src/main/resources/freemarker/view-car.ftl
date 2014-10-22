<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "View Car">
<dl class="car">
    <dt>Make</dt>
    <dd id="make">${car.make}</dd>

    <dt>Model</dt>
    <dd id="model">${car.model}</dd>

    <dt>Year</dt>
    <dd id="year">${car.year?c}</dd>

    <dt>Price</dt>
    <dd id="price">${car.price}</dd>
</dl>
</@layout.defaultLayout>
