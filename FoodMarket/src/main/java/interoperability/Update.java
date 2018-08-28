package interoperability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import jsonClasses.Member;

@Path("/update")
public class Update {
	
	@POST
	@Produces("Application/json")
	@Path("/user")
	public String register(Member m) {
		
		Connect conn = new Connect();
		String sqlAddress = "INSERT IGNORE INTO address(HouseNameNo,Postcode) VALUES(?,?)";
		String sqlMember = "UPDATE member SET Contact_Number = ?, Default_HouseNameNo = ?, Default_Postcode = ? WHERE Username = ?";
		String success= "false";
		try (Connection c = conn.accessDB();) {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			PreparedStatement stmtAddress = c.prepareStatement(sqlAddress);
			stmtAddress.setString(1, m.getHouseNameNo());
			stmtAddress.setString(2, m.getPostcode());
			stmtAddress.executeUpdate();
			PreparedStatement stmtMember = c.prepareStatement(sqlMember);
			stmtMember.setString(1,m.getContact_no());
			stmtMember.setString(2, m.getHouseNameNo());
			stmtMember.setString(3, m.getPostcode());
			stmtMember.setString(4, m.getUsername());
			stmtMember.executeUpdate();				
			success = "true";
			c.close();
		} catch (SQLException se) {
			se.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "{\"result\":\""+success +"\"}";	
	}

}
