
// Test class to see fetched data from the database to console

package recipeDAOtest;

import recipebookModel.dao.recipeDAO;

public class recipeDAOtest {

	public static void main(String[] args) {
		recipeDAO recipeDao = new recipeDAO();
		System.out.println(recipeDao.findAll());
	}

}
