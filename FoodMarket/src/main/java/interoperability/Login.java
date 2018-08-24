package interoperability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import jsonClasses.Member;

@Path("/log")
public class Login {
	
	@POST
	@Produces("Application/json")
	@Path("/login")
	public String loginCheck(Member m) {
		
		Connect conn = new Connect();
		
		String success= "false";
		try (Connection c = conn.accessDB();) {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String sql = "SELECT * FROM member WHERE Email = ? AND Password = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1,m.getEmail());
			stmt.setString(2, m.getPassword());
			ResultSet rs = stmt.executeQuery();
            if(rs.next()) {	
				success = "true";
			}
			c.close();
		} catch (SQLException se) {
			se.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "{\"result\":\""+success +"\"}";	
	}
}
