<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestão de Comentários</title>

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
                <h1>Cadastro de Comentários</h1>

                <form action="${pageContext.request.contextPath}/comments/save"
                      method="GET">

                    <input type="hidden"
                           name="comment_id"
                           value="${comment.id}">

                    <div class="mb-3">
                        <label for="comment_content_id" class="form-label">
                            Conteúdo
                        </label>

                        <textarea id="comment_content_id"
                                  name="comment_content"
                                  class="form-control"
                                  required><c:out value="${comment.content}"/></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="comment_user_id" class="form-label">
                            Usuário
                        </label>

                        <select id="comment_user_id"
                                name="comment_user"
                                class="form-control"
                                required>

                            <option value="">Selecione um usuário</option>

                            <c:forEach var="usuario" items="${users_list}">
                                <option value="${usuario.id}"
                                    <c:if test="${comment.user.id == usuario.id}">
                                        selected
                                    </c:if>>
                                    <c:out value="${usuario.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="comment_post_id" class="form-label">
                            Publicação
                        </label>

                        <select id="comment_post_id"
                                name="comment_post"
                                class="form-control"
                                required>

                            <option value="">Selecione uma publicação</option>

                            <c:forEach var="post" items="${posts_list}">
                                <option value="${post.id}"
                                    <c:if test="${comment.post.id == post.id}">
                                        selected
                                    </c:if>>
                                    <c:out value="${post.content}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        Salvar comentário
                    </button>

                </form>
            </div>

            <div class="col-md-2"></div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>