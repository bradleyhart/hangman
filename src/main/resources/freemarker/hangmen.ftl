<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "Hangmen">
<ul class="results">
    <#list hangmen as hangman>
        <li>
            <dl class="hangman">
                <dt>ID</dt>
                <dd class="make">${hangman.id}</dd>

                <dt>Attempts</dt>
                <dd class="model">${hangman.attempts}</dd>
            </dl>
        </li>
    </#list>
</ul>
</@layout.defaultLayout>
