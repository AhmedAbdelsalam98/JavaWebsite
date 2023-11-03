package system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(urlPatterns = {"/LogOut"})
public class LogOutController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/");
			return;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			doPost(request, response);
	}
	
}
