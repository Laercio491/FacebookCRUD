<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Gestão de Posts</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
	</head>

	<body>
		<div class="container">
			<div class="row mt-3">
				<div class="col-md-2"></div>

				<div class="col-md-8">
					<h1>Cadastro de Posts</h1>

					<form action="/facebook/posts/save" method="GET" class="">

						<input type="hidden" id="post_id_id" name="post_id" value="${post.getId()}">

						<div class="mb-3">
							<label for="post_content_id" class="form-label">
								Conteúdo
							</label>
							<input id="post_content_id" type="text" name="post_content" class="form-control"
								value="${post.getContent()}">
						</div>

						<div class="mb-3">
							<label for="post_user_id" class="form-label">
								Usuário
							</label>
							<select id="post_user_id" name="post_user" class="form-control" required>
								<option value="${usuario.getId()}">Selecione um usuário</option>
								
								<c:forEach var="usuario" items="${users_list}">
                                <option value="${usuario.getId()}"<c:if test="${post.getUser().getId() == usuario.getId()}">
                                        selected
                                    </c:if>>
                                
                                    <c:out value="${usuario.name}"/>
                                    
                                </option>
                            </c:forEach>
							</select>
						</div>

						<button type="submit" class="btn btn-primary">
							Cadastrar
						</button>
					</form>
				</div>

				<div class="col-md-2"></div>
			</div>
		</div>
	</body>

	</html>