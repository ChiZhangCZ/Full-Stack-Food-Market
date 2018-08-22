package interoperability;
import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import jsonClasses.Member;

@Path("/web")
public class ExampleWebApp {
	
	
	@GET
	@Produces("Application/json")
	@Path("/find/{name}")
	public String findPerson(@PathParam("name") String name) {
		String DB_URL = "jdbc:mysql://localhost:3306/personal_project?useSSL=false";

		String USER = "root";
		String PASS = "password";
		String finalValue = "";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM member WHERE username = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1,name);	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					finalValue  = rs.getString("First_Name")+" "+rs.getString("Last_Name");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "{\"result\":\""+finalValue+"\"}";
   }
	
	@GET
	@Produces("Application/json")
	@Path("/search/{name}")
	public String searchPerson(@PathParam("name") String name) {
		String DB_URL = "jdbc:mysql://localhost:3306/personal_project?useSSL=false";

		String USER = "root";
		String PASS = "password";
		JSONArray jsonArray = new JSONArray();
		
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM member WHERE First_Name = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1,name);	
				ResultSet rs = stmt.executeQuery();

			    while (rs.next()) {
			        int total_rows = rs.getMetaData().getColumnCount();
			        JSONObject obj = new JSONObject();
			        for (int i = 0; i < total_rows; i++) {
			            obj.put(rs.getMetaData().getColumnLabel(i+1)
			                    .toLowerCase(), rs.getObject(i + 1));
			        }
			        jsonArray.put(obj);
			    }
			    
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonArray.toString();
	}
				
	@POST
	@Path("/addFromInput")
	public void addNewCreatedEntry(Member m) {
		String DB_URL = "jdbc:mysql://localhost:3306/personal_project?useSSL=false";

		String USER = "root";
		String PASS = "password";
		String sql = "INSERT INTO member(Username,First_Name,Last_Name) VALUES(?,?,?)";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1,m.getUsername());
				stmt.setString(2, m.getFirst_name());
				stmt.setString(3, m.getLast_name());
				stmt.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	@POST
	@Path("/cleanperson")
	public void deletePerson(Member m) {
		String DB_URL = "jdbc:mysql://localhost:3306/personal_project?useSSL=false";

		String USER = "root";
		String PASS = "password";
		String sql = "DELETE FROM member WHERE Username = ?";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, m.getUsername());
				stmt.executeUpdate();
			    } catch (SQLException se) {
					se.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
