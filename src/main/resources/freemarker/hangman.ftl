<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "View Hangman">
<dl class="hangman">
    <dt>Word length</dt>
    <dd id="make">${wordLength}</dd>

    <dt>Attempts</dt>
    <dd id="model">${attempts}</dd>
</dl>
</@layout.defaultLayout>
