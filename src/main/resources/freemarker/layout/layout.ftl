<#macro defaultLayout title="Hangmen">
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <script src="${request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
</head>

<body>

<article>
    <#nested />
</article>

</body>

</html>
</#macro>