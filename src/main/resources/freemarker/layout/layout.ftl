<#macro defaultLayout title="Hangmen">
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <script src="${request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
    <script src="${request.contextPath}/resources/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="${request.contextPath}/resources/css/autocomplete.css">
</head>

<body>

<header>
    <nav>
        <ul>
            <li><a href="${request.contextPath}/view-hangmans">All</a></li>
            <li><a href="${request.contextPath}/add-hangman">Add</a></li>
            <li><a href="${request.contextPath}/search-hangmans">Search</a></li>
        </ul>
    </nav>
</header>

<article>
    <#nested />
</article>

</body>

</html>
</#macro>