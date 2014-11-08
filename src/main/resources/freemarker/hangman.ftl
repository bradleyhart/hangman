<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "View Hangman">
<dl class="hangman">
    <dt>ID</dt>
    <dd id="length">${id}</dd>

    <dt>Word length</dt>
    <dd id="length">${wordLength}</dd>

    <dt>Attempts</dt>
    <dd id="attempts">${attempts}</dd>
</dl>
</@layout.defaultLayout>
