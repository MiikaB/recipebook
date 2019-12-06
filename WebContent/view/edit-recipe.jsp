
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Edit Recipe</title>
</head>
<body>
<h1>Edit recipe</h1>

<form action="edit_recipe" method="post">
<input type="hidden" value="${recipe.id}" name="id"/>
<label>Recipe name:</label>
<input type="text" name="name" value="${recipe.name}" class="form-control" size="50"/>
<br/>
<label>Ingredients:</label>
<input type="text" name="ingredients" value="${recipe.ingredients}" class="form-control" size="50"/>
<br/>
<label>Guide:</label>
<input type="text" name="guide" value="${recipe.guide}" class="form-control" size="50"/>
<br/>
<input type="submit" class="btn btn-success"  name="submit-button" value="Save"/>
<a href="list-recipes"><input type="button" class="btn btn-danger" value="Cancel"/></a>
</form>
</body>
</html>