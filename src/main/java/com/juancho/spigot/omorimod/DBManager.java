package com.juancho.spigot.omorimod;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager {
    String URL = "omorimod.db";
    Connection conn = null;
    Statement stmt = null;

    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            // System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getPlayer(String playerName) {
        String sql =  "SELECT * FROM tbl_player WHERE player_name = '" + playerName + "'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("PLAYER INFO");
                System.out.println(rs.getString("player_name"));
                System.out.println(rs.getString("level"));
                System.out.println(rs.getString("daily_login"));
                System.out.println(rs.getString("experience"));
                System.out.println(rs.getString("class"));
                System.out.println(rs.getString("last_daily"));
                System.out.println(rs.getString("base_emotion"));
                System.out.println("END PLAYER INFO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }


    public String getMOTD() {
        String sql = "SELECT motd FROM test_table";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("motd");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }
        return "";

    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
