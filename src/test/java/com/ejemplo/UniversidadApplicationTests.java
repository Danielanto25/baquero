package com.ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UniversidadApplicationTests {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void contextLoads() {

		String clave = encoder.encode("123456789");
		String secret = encoder.encode("secret123");

		System.out.println(clave);
		System.out.println(secret);
	}

	//@Test
	public void prueba() throws Exception {

		 String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=universidad;user=sa;password=123456";

	        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
	            String SQL = "SELECT TOP 10 * FROM persona";
	            ResultSet rs = stmt.executeQuery(SQL);

	            // Iterate through the data in the result set and display it.
	            while (rs.next()) {
	                System.out.println(rs.getString("per_nombre") + " " + rs.getString("per_apellido"));
	            }
	        }
	        // Handle any errors that may have occurred.
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
