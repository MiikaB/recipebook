
// Object for reading, inserting, deleting entity 's from the database

package recipebookModel.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import recipebookModel.Recipe;


public class recipeDAO extends DataAccessObject {

		public ArrayList<Recipe> findAll() {	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Recipe> recipies = new ArrayList<Recipe>();
			Recipe recipe = null; 
			try {
				conn = getConnection();
				String sqlSelect = "SELECT id, name, ingredients, guide FROM recipe;";
				stmt = conn.prepareStatement(sqlSelect);
				rs = stmt.executeQuery();
				while (rs.next()) {
					recipe = readRecipe(rs);
					recipies.add(recipe);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn);
			}
			return recipies;
		}
		
		public void addRecipe(Recipe recipe) throws SQLException {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
			try {
				connection = getConnection();
				String sqlInsert = "INSERT INTO recipe(id, name, ingredients, guide) VALUES (?, ?, ?, ?)";
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
		
		public void removeRecipe(int id) throws SQLException {
 			Connection connection = null;
 			PreparedStatement stmtDelete = null;
 			try {
 				connection = getConnection();
 				String sqlInsert = "DELETE FROM recipe WHERE id = ?";
 				stmtDelete = connection.prepareStatement(sqlInsert);
 				stmtDelete.setInt(1, id);;
 				stmtDelete.executeUpdate();
 			} catch (SQLException e) {
 				throw new RuntimeException(e);
 			} finally {
 				close(stmtDelete, connection);
 			}
 		}

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