package interoperability;
import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import jsonClasses.Item;
import jsonClasses.Member;

@Path("/web")
public class Create {
	
	
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
				
	@POST
	@Produces("Application/json")
	@Path("/addFromInput")
	public String register(Member m) {
		
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
	@Produces("Application/json")
	@Path("/addItem")
	public String addItem(Item i) {
		
		Connect conn = new Connect();
		String sql = "INSERT INTO item(Item_Name,Item_Count,Item_Description,Price,Item_Added_By_User) VALUES(?,?,?,?,?)";
		String success= "false";
		try (Connection c = conn.accessDB();) {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1,i.getItemName());
			stmt.setInt(2, i.getItemCount());
			stmt.setString(3, i.getItemDescription());
			stmt.setString(4, i.getPrice());
			stmt.setString(5, i.getAddedBy());
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
