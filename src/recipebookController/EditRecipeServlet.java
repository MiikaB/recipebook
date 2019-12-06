package recipebookController;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipebookModel.Recipe;
import recipebookModel.dao.recipeDAO;

// Path to show edit-recipe.jsp which includes html form for editing recipe

@WebServlet("/edit_recipe")
public class EditRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Get-request with id to get current data of the to be edited recipe
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		String idStr = request.getParameter("id");
		int id = new Integer(idStr);
		recipeDAO recipedao = new recipeDAO();
		Recipe recipe = recipedao.findRecipe(id);
		request.setAttribute("recipe", recipe);
		
		String jsp = "/view/edit-recipe.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
	} catch (SQLException e) {
		System.out.println("::Error happened:: " + e.getMessage());
	}
	
	}

	//Post-request to send updated info of the recipe to replace old info
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idStr = request.getParameter("id");
		int id = new Integer(idStr);
		String name = request.getParameter("name");
		String ingrediants = request.getParameter("ingredients");
		String guide = request.getParameter("guide");
		Recipe recipe = new Recipe(id, name, ingrediants, guide);
		
		System.out.println("Päivitetty resepti: " + recipe.toString());
		
		recipeDAO recipedao = new recipeDAO();
		
		String errorcode = null;
		
		try {
			
			// Sending the updated recipe entity to database
			
			recipedao.editRecipe(recipe);	
		} catch (Exception e) {
			errorcode = "Upsie! Error happened in the database :(";
		}
		if (errorcode != null) {
			String codedText = URLEncoder.encode(errorcode, "UTF-8");
			response.sendRedirect("list-recipes?message=" + codedText);
		} else
			response.sendRedirect("list-recipes");
	}
}
