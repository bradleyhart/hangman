<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "View Hangmen">
<ul class="results">
    <#list hangmans as hangman>
        <li>
            <dl class="hangman">
                <dt>Make</dt>
                <dd class="make">${hangman.make}</dd>

                <dt>Model</dt>
                <dd class="model">${hangman.model}</dd>

                <dt>Year</dt>
                <dd class="year">${hangman.year?c}</dd>

                <dt>Price</dt>
                <dd class="price">${hangman.price}</dd>
            </dl>
        </li>
    </#list>
</ul>
</@layout.defaultLayout>
