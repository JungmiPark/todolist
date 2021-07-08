package kr.or.connect.Todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.Todo.dto.TodoDto;

public class TodoDao {
	
	// 1. DB diver 클래스 로드
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	// 2. DB 연결	
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!@#";
	
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private boolean dbConnect() {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			result = true;
		} catch(Exception e) {
			e.printStackTrace();			
		}
		
		return result;
	}	
	
	
	// 3. DB 해제	
	private void dbClose() {
		try {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// INSERT TODO	
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		boolean connectResult;
		try {
			if (this.dbConnect()) {
			String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());
			
			insertCount = ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbClose();
		}
			
		return insertCount;
	}
	
	
	// SELECT TODOS
	public List<TodoDto> getTodos(String type) {
		List<TodoDto> list = new ArrayList<TodoDto>();
		
		try {
			if(this.dbConnect()) {
				String sql = "SELECT title, regdate, name, sequence, type, id FROM todo WHERE type = ? ORDER BY sequence";
				ps = conn.prepareStatement(sql);
				ps.setString(1, type);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					String title = rs.getString(1);
					String regdate = rs.getString(2);
					String name = rs.getString(3);
					Integer sequence = rs.getInt(4);
					type = rs.getString(5);
					long id = rs.getLong(6);
					TodoDto todo = new TodoDto(title, regdate, name, sequence, type, id);
					list.add(todo);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbClose();
		}
		
		return list;
	}
	
	// UPDATE TODO TYPE
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		
		try {
			if (this.dbConnect()) {
				String sql = "UPDATE todo SET type = ? WHERE id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, todoDto.getType());
				ps.setLong(2, todoDto.getId());
			
				updateCount = ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbClose();
		}
		
		return updateCount;
	}
}
