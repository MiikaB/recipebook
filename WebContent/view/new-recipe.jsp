<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Add new Recipe</title>
</head>
<body>
<h1>New Recipe</h1>

<form action="new-recipe" method="post">
<label>Recipe name:</label>
<input type="text" name="name" value="" class="form-control" size="50"/>
<br/>
<label>Ingredients:</label>
<input type="text" name="ingredients" value="" class="form-control" size="50"/>
<br/>
<label>Guide:</label>
<input type="text" name="guide" value="" class="form-control" size="50"/>
<br/>
<input type="submit" class="btn btn-success"  name="submit-button" value="Save"/>
<a href="list-recipes"><input type="button" class="btn btn-danger" value="Cancel"/></a>
</form>
</body>
</html>