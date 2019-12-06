
// Path '/list-recipes' for listing all the recipes from the database in web-view

package recipebookController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipebookModel.Recipe;
import recipebookModel.dao.recipeDAO;

@WebServlet("/list-recipes")
public class ListRecipesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ListRecipesServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		recipeDAO recipedao = new recipeDAO();
		
		// Arraylist to contain all entitys from the database
		
		ArrayList<Recipe>recipe = recipedao.findAll();
		
		// Setting attribute to access data in jsp web-file
		
		request.setAttribute("recipe", recipe);
		String jsp = "/view/list-recipes.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
	}
}
