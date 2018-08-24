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
public class Register {
	
	
	@GET
	@Produces("Application/json")
	@Path("/find/{name}")
	public String findPerson(@PathParam("name") String name) {
        
		Connect conn = new Connect();
		String finalValue = "";
			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM member WHERE username = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1,name);	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					finalValue  = rs.getString("First_Name")+" "+rs.getString("Last_Name");
					rs.close();
				}
				c.close();
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
		
		Connect conn = new Connect();
		JSONArray jsonArray = new JSONArray();
		
			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM member WHERE First_Name = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1,name);	
				ResultSet rs = stmt.executeQuery();
				JSONObject obj = null;
			    while (rs.next()) {
			        obj = new JSONObject();
	                obj.put("first_name", rs.getString("First_Name"));
	                obj.put("last_name", rs.getString("Last_Name"));
			        jsonArray.put(obj);
			        rs.close();
			    }
			    c.close();
			    
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonArray.toString();
	}
				
	@POST
	@Produces("Application/json")
	@Path("/addFromInput")
	public String addNewCreatedEntry(Member m) {
		
		Connect conn = new Connect();
		String sql = "INSERT INTO member(Username,Password,Email,First_Name,Last_Name) VALUES(?,?,?,?,?)";
		String success= "false";
		try (Connection c = conn.accessDB();) {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1,m.getUsername());
			stmt.setString(2, m.getPassword());
			stmt.setString(3, m.getEmail());
			stmt.setString(4, m.getFirst_name());
			stmt.setString(5, m.getLast_name());
			stmt.executeUpdate();				
			success = "true";
			c.close();
		} catch (SQLException se) {
			se.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "{\"result\":\""+success +"\"}";	
	}
	
	@POST
	@Path("/cleanperson")
	public void deletePerson(Member m) {

		Connect conn = new Connect();
		String sql = "DELETE FROM member WHERE Username = ?";
			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, m.getUsername());
				stmt.executeUpdate();
				c.close();
		    } catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
}
