
// Path "/recipes" for data as in API to be fetched for other projects

package recipebookController;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import recipebookModel.Recipe;
import recipebookModel.dao.recipeDAO;


@WebServlet("/recipes")
	public class RecipebookRestServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				
				// Fetching all recipe entity 's from database to arraylist
				
				recipeDAO recipedao = new recipeDAO();
				ArrayList<Recipe> recipe = recipedao.findAll();

				String strJSON = new JSONObject().put("recipe", recipe).toString();

				// Header to accept CORS-policy
				
				response.addHeader("Access-Control-Allow-Origin", "*");
				
				// Content type to accept utf-8 meaning nordic letters ��� etc. will be shown correctly
				
				response.setContentType ("text/html;charset=utf-8");
				
				// Content type how data will be listed as in JSON
				
				response.setContentType("application/json");
				response.getWriter().println(strJSON);

			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException("Error occured during the request");
			}
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			//POST call to create new recipe and sending it to addRecipe-method
			
			try {

				BufferedReader reader = request.getReader();
				StringBuffer strBuffer = new StringBuffer();
				String line = reader.readLine();
				while (line != null) {
					strBuffer.append(line);
					line = reader.readLine();
				}
				System.out.println(strBuffer.toString());

				JSONObject recipeJsonObject = new JSONObject(strBuffer.toString());
				System.out.println(recipeJsonObject);
				
				//Prints the form data to console, showing new recipe

				Recipe recipe = new Recipe();
				recipe.setName(recipeJsonObject.getString("name"));
				recipe.setIngredients(recipeJsonObject.getString("ingredients"));
				recipe.setGuide(recipeJsonObject.getString("guide"));
				
				System.out.println(recipe);

				recipeDAO recipedao = new recipeDAO();

				recipedao.addRecipe(recipe);

				response.setContentType("application/json");
				response.getWriter().println(recipeJsonObject);

			} catch (JSONException e) {
				e.printStackTrace();
				throw new IOException("JSON-data compiling had an error");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException("Database error");
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException("Request error");
			}
		}
			protected void doDelete(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				
				// DELETE call to removeRecipe-method to remove entity from the database with parameter 'id'

				try {
					String idStr = request.getParameter("id");
						 System.out.println("Removal id of the recipe: " + idStr);
					int id = new Integer(idStr);

					recipeDAO recipedao = new recipeDAO();

					recipedao.removeRecipe(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException("Database error occured.");
				}
		}
			
			protected void doPut(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				
				// PUT call to update or create an entity depending if it exists
				// Calls editRecipe-method
				
				try {

					BufferedReader reader = request.getReader();
					StringBuffer strBuffer = new StringBuffer();
					String line = reader.readLine();
					while (line != null) {
						strBuffer.append(line);
						line = reader.readLine();
					}
					System.out.println(strBuffer.toString());

					JSONObject recipeJsonObject = new JSONObject(strBuffer.toString());
					System.out.println(recipeJsonObject);

					Recipe recipe = new Recipe();
					recipe.setId(recipeJsonObject.getInt("id"));
					recipe.setName(recipeJsonObject.getString("name"));
					recipe.setIngredients(recipeJsonObject.getString("ingredients"));
					recipe.setGuide(recipeJsonObject.getString("guide"));
					
					System.out.println(recipe);

					recipeDAO recipedao = new recipeDAO();

					recipedao.editRecipe(recipe);

					response.setContentType("application/json");
					response.getWriter().println(recipeJsonObject);

				} catch (JSONException e) {
					e.printStackTrace();
					throw new IOException("JSON-data compiling had an error");
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException("Database error");
				} catch (Exception e) {
					e.printStackTrace();
					throw new IOException("Request error");
				}
				
			}
	}
