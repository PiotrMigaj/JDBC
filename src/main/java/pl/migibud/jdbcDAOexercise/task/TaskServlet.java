package pl.migibud.jdbcDAOexercise.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
		name = "Task",
		urlPatterns = {"/task"}
)
public class TaskServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(TaskServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = req.getParameter("id");
//		logger.info(id);
//		String description = req.getParameter("description");
//		logger.info(description);
//		String userId = req.getParameter("userId");
//		logger.info(userId);


//		resp.getWriter().println("Hello from TaskServlet" + id +" "+description+" "+userId);
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("Hello from TaskServlet POST");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("Hello from TaskServlet");
	}
}
