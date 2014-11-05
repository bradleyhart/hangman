<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "View Hangman">
<dl class="hangman">
    <dt>Make</dt>
    <dd id="make">${hangman.make}</dd>

    <dt>Model</dt>
    <dd id="model">${hangman.model}</dd>

    <dt>Year</dt>
    <dd id="year">${hangman.year?c}</dd>

    <dt>Price</dt>
    <dd id="price">${hangman.price}</dd>
</dl>
</@layout.defaultLayout>
