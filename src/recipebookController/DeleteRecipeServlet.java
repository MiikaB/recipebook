
// Delete function for path '/delete_recipe' which requires id of the tobe deleted entity 

package recipebookController;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipebookModel.dao.recipeDAO;

@WebServlet("/delete_recipe")
public class DeleteRecipeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// Fetch requested id parameter
			
			String idStr = request.getParameter("id");
			int id = new Integer(idStr);
			recipeDAO recipedao = new recipeDAO();
			
			// Calling function removeRecipe with id parameter
			
			recipedao.removeRecipe(id);
		} catch (SQLException e) {
				System.out.println("::Error happened:: " + e.getMessage());
		}
		
		// Redirect to list all recipes updated with current deletion
		
			response.sendRedirect("list-recipes");
	}
}
