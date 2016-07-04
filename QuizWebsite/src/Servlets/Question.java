package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Constants;

/**
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("id")));
		
		//System.out.println("id" + id);
		ArrayList<Integer> positions = (ArrayList<Integer>)request.getSession().getAttribute("questionPositions");
		ArrayList<classes.question.Abstract.Question> questions = (ArrayList<classes.question.Abstract.Question>)request.getSession().getAttribute("Questions");
		
		
		
		RequestDispatcher rd = null;
		for (classes.question.Abstract.Question q : questions) System.out.println(q.getType());
		//for (Integer q : positions) System.out.println(q);
		//System.out.println(positions.size());
		if (id<positions.size()) {
			//System.out.println(questions.get(id).getType() + " " + id);
			String type = questions.get(positions.get(id)).getType();
			rd = request.getRequestDispatcher("Test/" + type + ".jsp?id=" + positions.get(id));
		} 
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}