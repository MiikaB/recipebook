
// Path '/new-recipe' for adding new recipe entity gotten from web-form and sent to mariaDB

package recipebookController;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipebookModel.Recipe;
import recipebookModel.dao.recipeDAO;

@WebServlet("/new-recipe")
public class NewRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/view/new-recipe.jsp";
		getServletContext().getRequestDispatcher(jsp).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String ingrediants = request.getParameter("ingredients");
		String guide = request.getParameter("guide");
		Recipe recipe = new Recipe(name, ingrediants, guide);
		
		System.out.println("Uusi resepti: " + recipe.toString());
		
		recipeDAO recipedao = new recipeDAO();
		
		String errorcode = null;
		
	try {
		recipedao.addRecipe(recipe);	
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