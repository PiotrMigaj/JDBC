package pl.migibud.jdbcDAOexercise.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
		name = "Task",
		urlPatterns = {"/task"}
)
public class TaskServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(TaskServlet.class);
	private ObjectMapper objectMapper;
	private AbstractDAOInterface<Task> abstractDAOInterface;

	public TaskServlet() {
		this(new ObjectMapper(),new TaskDAO());
	}

	public TaskServlet(ObjectMapper objectMapper, AbstractDAOInterface<Task> abstractDAOInterface) {
		this.objectMapper = objectMapper;
		this.abstractDAOInterface = abstractDAOInterface;
	}

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
		logger.info("Got request with parameters: "+req.getParameterMap());
		String id = req.getParameter("id");
		String description = req.getParameter("description");
		String userId = req.getParameter("userId");
//		resp.setContentType("text/html;charset=UTF-8");
//		resp.getWriter().write("Hello from TaskServlet: "+id +" "+description+" "+userId);
		Task task = new Task(Long.parseLong(id),description,Long.parseLong(userId));
		try {
			this.abstractDAOInterface.create(task);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json;charset=UTF-8");
		logger.info(String.valueOf(task));
		try {
			objectMapper.writeValue(resp.getOutputStream(),abstractDAOInterface.readAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
