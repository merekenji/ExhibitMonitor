package monitor.exhibit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import monitor.exhibit.beans.Record;
import monitor.exhibit.utilities.ApplicationContext;

public class RecordDAO {
	
	Connection con = null;

	public synchronized void insertValidRecord(Record record) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		PreparedStatement ps = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "C@pgemin!");
			synchronized (con) {
				ps = con.prepareStatement("INSERT INTO VALID_RECORD(fileName,rowNum,date,value) VALUES(?,?,?,?)");
				ps.setString(1, record.getFileName());
				ps.setInt(2, record.getRowNum());
				Calendar cal = Calendar.getInstance();
				cal.setTime(record.getDate());
				ps.setDate(3, new Date(cal.getTimeInMillis()));
				ps.setString(4, record.getRecord());
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ApplicationContext.getValidRecords().remove(0);
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void insertInvalidRecord(Record record) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		PreparedStatement ps = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "C@pgemin!");
			synchronized (con) {
				ps = con.prepareStatement("INSERT INTO INVALID_RECORD(fileName,rowNum,date,value) VALUES(?,?,?,?)");
				ps.setString(1, record.getFileName());
				ps.setInt(2, record.getRowNum());
				Calendar cal = Calendar.getInstance();
				cal.setTime(record.getDate());
				ps.setDate(3, new Date(cal.getTimeInMillis()));
				ps.setString(4, record.getRecord());
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ApplicationContext.getInvalidRecords().remove(0);
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized List<Record> getValidRecord(String fileName) {
		List<Record> records = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "C@pgemin!");
			synchronized (con) {
				ps = con.prepareStatement("SELECT * FROM VALID_RECORD WHERE FILENAME=?");
				ps.setString(1, fileName);
				rs = ps.executeQuery();
				while (rs.next()) {
					records.add(new Record(rs.getString("fileName"), rs.getInt("rowNum"), rs.getDate("date"),
							rs.getString("value")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return records;
	}
	
}
