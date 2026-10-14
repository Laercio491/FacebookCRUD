<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comentários - Facebook CRUD</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>

<body>

<div class="container">

    <div class="row mt-3">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/">
                Home
            </a>
        </div>

        <div class="col-md-2"></div>
    </div>

    <div class="row mt-3">
        <div class="col-md-2"></div>

        <div class="col-md-8">

            <h2>Comentários</h2>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Conteúdo</th>
                        <th scope="col">Data</th>
                        <th scope="col">Usuário</th>
                        <th scope="col">Publicação</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="comment" items="${comments_list}">
                        <tr>
                            <th scope="row">${comment.id}</th>

                            <td>
                                <c:out value="${comment.content}"/>
                            </td>

                            <td>${comment.date}</td>

                            <td>
                                <c:out value="${comment.user.name}"/>
                            </td>

                            <td>
                                <c:out value="${comment.post.content}"/>
                            </td>

                            <td>
                                <a href="${pageContext.request.contextPath}/comments/update?commentId=${comment.id}"
                                   class="bi bi-pencil-square"></a>

                                <a href="${pageContext.request.contextPath}/comments/delete?commentId=${comment.id}"
                                   class="bi bi-trash"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/comments_form"
               class="btn btn-primary">
                Cadastrar comentário
            </a>

        </div>

        <div class="col-md-2"></div>
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

</body>
</html>