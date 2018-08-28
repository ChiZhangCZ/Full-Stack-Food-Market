package interoperability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/read")
public class Read {
	
	@GET
	@Produces("Application/json")
	@Path("/filterItem/{id}")
	public String filterItem(@PathParam("id") String id) {
		
		Connect conn = new Connect();
		JSONArray jsonArray = new JSONArray();
		
			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM item WHERE Item_ID = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1,id);
				ResultSet rs = stmt.executeQuery();
				JSONObject obj = null;
			    while (rs.next()) {
			        obj = new JSONObject();
	                obj.put("Item_Name", rs.getString("Item_Name"));
	                obj.put("Item_Description", rs.getString("Item_Description"));
	                obj.put("Item_Count", rs.getString("Item_Count"));
	                obj.put("Price", rs.getString("Price"));
	                obj.put("Added_By", rs.getString("Item_Added_By_User"));
			        jsonArray.put(obj);
			    }
		        rs.close();
			    c.close();
			    
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonArray.toString();
	}
	
	
	@GET
	@Produces("Application/json")
	@Path("/allItems")
	public String getAllItems() {
		
		Connect conn = new Connect();
		JSONArray jsonArray = new JSONArray();
		
			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM item";
				PreparedStatement stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				JSONObject obj = null;
			    while (rs.next()) {
			        obj = new JSONObject();
			        obj.put("Item_ID", rs.getString("Item_ID"));
	                obj.put("Item_Name", rs.getString("Item_Name"));
	                obj.put("Item_Count", rs.getString("Item_Count"));
	                obj.put("Item_Description", rs.getString("Item_Description"));
	                obj.put("Price", rs.getString("Price"));
	                obj.put("Added_By", rs.getString("Item_Added_By_User"));
			        jsonArray.put(obj);	        
			    }
			    rs.close();
			    c.close();
			    
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonArray.toString();
	}
	
	
	@GET
	@Produces("Application/json")
	@Path("/findItem/{id}")
	public String findItem(@PathParam("id") int id) {
        
		Connect conn = new Connect();
		JSONObject obj = null;

			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM item WHERE Item_ID = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1,id);	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
					obj = new JSONObject();
					obj.put("Item_ID", rs.getString("Item_ID"));
	                obj.put("Item_Name", rs.getString("Item_Name"));
	                obj.put("Item_Count", rs.getString("Item_Count"));
	                obj.put("Item_Description", rs.getString("Item_Description"));
	                obj.put("Price", rs.getString("Price"));
	                obj.put("Added_By", rs.getString("Item_Added_By_User"));				
				}
				rs.close();
				c.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obj.toString();
   }
	
	@GET
	@Produces("Application/json")
	@Path("/findMember/{name}")
	public String findMember(@PathParam("name") String name) {
        
		Connect conn = new Connect();
		JSONObject obj = null;

			try (Connection c = conn.accessDB();) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				String sql = "SELECT * FROM member WHERE username = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1,name);	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
					obj = new JSONObject();
					obj.put("First_Name", rs.getString("First_Name"));
					obj.put("Last_Name", rs.getString("Last_Name"));
					obj.put("HouseNameNo", rs.getString("Default_HouseNameNo"));
	                obj.put("Postcode", rs.getString("Default_Postcode"));			
				}
				rs.close();
				c.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obj.toString();
   }

}


