<#import "layout/layout.ftl" as layout>

<@layout.defaultLayout "Hangman">

<script>
    $(function () {
        $('form').submit(function (event) {

            event.preventDefault()
            $theForm = $(this);

            $.ajax({
                type: 'post',
                url: 'guess',
                data: $theForm.serialize(),
                dataType: 'json',
                success: function (data) {
                    $('#attempts').text(Object.keys(data.guesses).length)
                    $.each(data.hits, function() {
                        $("#word-index-" + this.wordIndex).text(this.character);
                    });
                }
            });

            return false;
        });
    })
</script>

<form name="form" method="post" action="hangman">
    <input type=hidden name="wordLength" value="${hangman.wordLength}">
    <input type=hidden name="attempts" value="${hangman.attempts}">
    <input type=hidden name="hangmanId" value="${hangman.id}">

    <select id="guess" name="guess">
        <option value="a">a</option>
        <option value="b">b</option>
        <option value="c">c</option>
        <option value="d">d</option>
        <option value="e">e</option>
        <option value="f">f</option>
        <option value="g">g</option>
        <option value="h">h</option>
        <option value="i">i</option>
        <option value="j">j</option>
        <option value="k">k</option>
        <option value="l">l</option>
        <option value="m">m</option>
        <option value="n">n</option>
        <option value="o">o</option>
        <option value="p">p</option>
        <option value="q">q</option>
        <option value="r">r</option>
        <option value="s">s</option>
        <option value="t">t</option>
        <option value="u">u</option>
        <option value="v">v</option>
        <option value="w">w</option>
        <option value="x">x</option>
        <option value="y">y</option>
        <option value="z">z</option>
    </select>

    <input type="submit">
</form>

<div id="word">
    <#list 0..hangman.wordLength - 1 as index>
        <#if hangman.isHitAtIndex(index)>
            <span id="word-index-${index}">${hangman.characterAtIndex(index)}</span>
        <#else>
            <span id="word-index-${index}">_</span>
        </#if>
    </#list>
</div>

<dl class="hangman">
    <dt>Attempts</dt>
    <dd id="attempts">${hangman.attempts}</dd>
</dl>
</@layout.defaultLayout>
