package com.mindtree.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mindtree.utility.DBConnection;
import com.mindtree.utility.MyException;

public class DatabaseToJson {
	public static void storeToJson() throws SQLException, MyException {
		Connection con = null;
		ResultSet rs = null;
		con = DBConnection.getConnection();
		String str = "Select * from player";
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			rs = st.executeQuery(str);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();

		while (rs.next()) {
			JSONObject record = new JSONObject();
			record.put("Player ID", rs.getShort("playerId"));
			record.put("Player Age", rs.getShort("playerAge"));
			record.put("Player Name", rs.getString("playerName"));

			jArray.add(record);
		}
		jObject.put("PlayerDetails", jArray);
		FileWriter file = null;
		try {
			file = new FileWriter("D:/PlayerDetails.json");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			file.write(jObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
