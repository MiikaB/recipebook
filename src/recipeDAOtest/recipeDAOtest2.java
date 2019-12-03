
// Test Class to send new recipe entity to the database

package recipeDAOtest;

import java.sql.SQLException;

import recipebookModel.Recipe;
import recipebookModel.dao.recipeDAO;

public class recipeDAOtest2 {

	public static void main(String[] args) {
		recipeDAO recipedao = new recipeDAO();
		Recipe recipe = new Recipe(0, "Pannukakkutesti","Maito, Jauho" , "Sekoita kulhossa. Kaada uunipellille ja paista 225-asteessa.");
		try {
			recipedao.addRecipe(recipe);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
