<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "Hangman">

<form name="form" method="post" action="guess">
    <input type=hidden id="wordLength" name="wordLength" value="${hangman.wordLength}">
    <input type=hidden id="attempts" name="attempts" value="${hangman.attempts}">
    <input type=hidden id="hangmanId" name="hangmanId" value="${hangman.id}">

    <select id="guess" name="guess">
        <option value="a">a</option>
        <option value="a">b</option>
        <option value="a">c</option>
        <option value="a">d</option>
        <option value="a">e</option>
        <option value="a">f</option>
        <option value="a">g</option>
        <option value="a">h</option>
        <option value="a">i</option>
        <option value="a">j</option>
        <option value="a">k</option>
        <option value="a">l</option>
        <option value="a">m</option>
        <option value="a">n</option>
        <option value="a">o</option>
        <option value="a">p</option>
        <option value="a">q</option>
        <option value="a">r</option>
        <option value="a">s</option>
        <option value="a">t</option>
        <option value="a">u</option>
        <option value="a">v</option>
        <option value="a">w</option>
        <option value="a">x</option>
        <option value="a">y</option>
        <option value="a">z</option>
    </select>

    <input type="submit">
</form>

<div id="word">
    <#list 0..hangman.wordLength - 1 as index>
        <#if hangman.isHitAtIndex(index)>
            <span data-word-index="${index}">${hangman.characterAtIndex(index)}</span>
        <#else>
            <span data-word-index="${index}">_</span>
        </#if>
    </#list>
</div>

<dl class="hangman">
    <dt>ID</dt>
    <dd id="length">${hangman.id}</dd>

    <dt>Word length</dt>
    <dd id="length">${hangman.wordLength}</dd>

    <dt>Attempts</dt>
    <dd id="attempts">${hangman.attempts}</dd>
</dl>
</@layout.defaultLayout>
