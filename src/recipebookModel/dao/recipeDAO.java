
// Object for reading, editing, deleting, adding entity 's from the database

package recipebookModel.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import recipebookModel.Recipe;


public class recipeDAO extends DataAccessObject {
	
		// Method for reading all the recipes from the database
		// and setting them in an array

		public ArrayList<Recipe> findAll() {	
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Recipe> recipies = new ArrayList<Recipe>();
			Recipe recipe = null; 
			try {
				connection = getConnection();
				String sqlSelect = "SELECT id, name, ingredients, guide FROM recipe;";
				stmt = connection.prepareStatement(sqlSelect);
				rs = stmt.executeQuery();
				while (rs.next()) {
					recipe = readRecipe(rs);
					recipies.add(recipe);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, connection);
			}
			return recipies;
		}
		
		// Add method for inserting new recipe to the database
		
		public void addRecipe(Recipe recipe) throws SQLException {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
			try {
				connection = getConnection();
				String sqlInsert = "INSERT INTO recipe(id, name, ingredients, guide) VALUES (?, ?, ?, ?);";
				stmtInsert = connection.prepareStatement(sqlInsert);
				stmtInsert.setInt(1, recipe.getId());
				stmtInsert.setString(2, recipe.getName());
				stmtInsert.setString(3, recipe.getIngredients());
				stmtInsert.setString(4, recipe.getGuide());
				stmtInsert.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtInsert, connection);
			}
		}
		
		//remove method for removing recipes from the database
		
		public void removeRecipe(int id) throws SQLException {
 			Connection connection = null;
 			PreparedStatement stmtDelete = null;
 			try {
 				connection = getConnection();
 				String sqlInsert = "DELETE FROM recipe WHERE id = ?;";
 				stmtDelete = connection.prepareStatement(sqlInsert);
 				stmtDelete.setInt(1, id);;
 				stmtDelete.executeUpdate();
 			} catch (SQLException e) {
 				throw new RuntimeException(e);
 			} finally {
 				close(stmtDelete, connection);
 			}
 		}
		
		public void editRecipe(Recipe recipe) throws SQLException {
 			Connection connection = null;
 			PreparedStatement stmtUpdate = null;
 			try {
 				connection = getConnection();
 				String sqlSelect = "UPDATE recipe SET id = ?, name = ?, ingredients = ?, guide = ? WHERE id = ?;";
				stmtUpdate = connection.prepareStatement(sqlSelect);
				stmtUpdate.setInt(1, recipe.getId());
				stmtUpdate.setString(2, recipe.getName());
				stmtUpdate.setString(3, recipe.getIngredients());
				stmtUpdate.setString(4, recipe.getGuide());
				stmtUpdate.setInt(5, recipe.getId());
				stmtUpdate.executeUpdate();
 			} catch (SQLException e) {
 				throw new RuntimeException(e);
 			} finally {
 				close(stmtUpdate, connection);
 			}
		}
		
		//Method to find a recipe with id and return its values
		
		public Recipe findRecipe(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Recipe recipe = null; 
 			try {
 				connection = getConnection();
 				String sqlSelect = "SELECT * FROM recipe WHERE id = ?;";
 				stmt = connection.prepareStatement(sqlSelect);
 				stmt.setInt(1, id);;
				rs = stmt.executeQuery();
				rs.next();
				recipe = readRecipe(rs);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, connection);
			}
			return recipe;
		}
		
		// Get-method

		private Recipe readRecipe(ResultSet rs) {	
			try {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String ingredients = rs.getString("ingredients");
				String guide = rs.getString("guide");
				return new Recipe(id ,name, ingredients, guide);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}