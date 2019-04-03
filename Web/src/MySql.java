import java.sql.*;

public class MySql {
	private Connection con;
	private Statement st;
	private ResultSet set;

	public MySql() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://website:poort/userdata&autoReconnect=true&failOverReadOnly=false&maxReconnects=10",
					"gebruikersnaam", "wachtwoord");
			st = con.createStatement();
			System.out.println("Connection is succesful!");
		} catch (Exception ex) {
			System.out.println("Connection error: " + ex);
		}
	}

	public void getData(String Name, String Password) {
		try {
			String query = "select * from passwords";
			set = st.executeQuery(query);
			System.out.println("Passwords: \n");

			while (set.next()) {
				String name = set.getString("naamDB");
				String password = set.getString("WachtwoordDB");
				if (Name == name && Password == password)
					System.out.println("Permission granted!");
				break;
			}
		} catch (Exception ex) {
			System.out.println("Data error: " + ex);
		}finally {
			System.out.println("Data exchange succesful!");
		}
	}
}
