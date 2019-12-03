
// Consturctor, Setters, Getters, and toString for recipe

package recipebookModel;

public class Recipe {
int id;
String name = "";
String ingredients = "";
String guide = "";

public Recipe() {
	this.id = 0;
	this.name = null;
	this.ingredients = null;
	this.guide = null;
}
public Recipe(int id, String name, String ingredients, String guide) {
	this.id = id;
	this.name = name;
	this.ingredients = ingredients;
	this.guide = guide;
}
public Recipe(String name, String ingredients, String guide) {
	this.id = 0;
	this.name = name;
	this.ingredients = ingredients;
	this.guide = guide;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGuide() {
	return guide;
}
public void setGuide(String guide) {
	this.guide = guide;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIngredients() {
	return ingredients;
}
public void setIngredients(String ingredients) {
	this.ingredients = ingredients;
}
@Override
public String toString() {
	return this.name + ": " + this.ingredients + " ::OHJE:: " + this.guide;
}


}
