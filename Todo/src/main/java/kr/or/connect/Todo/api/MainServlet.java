package kr.or.connect.Todo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.Todo.dao.TodoDao;
import kr.or.connect.Todo.dto.TodoDto;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		TodoDao dao = new TodoDao();
		
		String[] typeList = {"todo", "doing", "done"};
		String[] valueList = {"todoList", "doingList", "doneList"};
		
		for (int i = 0, len = typeList.length; i < len; i++ ) {
			List<TodoDto> list = dao.getTodos(typeList[i]);
			
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(list);
				
			request.setAttribute(valueList[i], json);			
		}
		
			
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		TodoDto todoDto = new TodoDto();
		try {
			BufferedReader reader = request.getReader();
			todoDto = mapper.readValue(reader.readLine(), TodoDto.class);
			
			TodoDao dao = new TodoDao();
			dao.updateTodo(todoDto);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
