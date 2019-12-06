<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Resepti-lista</title>
</head>
<body>
<h1>Reseptit</h1>
<a href="new-recipe" class="btn btn-success">Add a new Recipe</a>
<table class="table table-striped">
<tr>
	<th>Recipe Name:</th>
	<th>Ingredients:</th>
	<th>Guide:</th>
</tr>
<c:forEach items="${recipe}" var="recipe">
	<tr>
	<td><c:out value="${recipe.name}"/></td>
	<td><c:out value="${recipe.ingredients}"/></td>
	<td><c:out value="${recipe.guide}"/></td>
	<td><a class="btn btn-primary" href="edit_recipe?id=<c:out value='${recipe.id}' />">Edit</a></td>
	<td><a class="btn btn-danger" href="delete_recipe?id=<c:out value='${recipe.id}' />">Delete</a></td>
	</tr>
</c:forEach>
</table>
</body>
