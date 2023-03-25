package com.example.snakegamefucked.Database;

import java.sql.*;
import java.util.ArrayList;

public class SnakeImpl implements ProjectSnake
{
    private static String userName = "sa";
    private static String password = "123456";
    private static String databaseName = "Snake";
    private static String Port = "1433";
    private static Connection con;
    public SnakeImpl()
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:"+Port+";databaseName="+databaseName,userName,password);
        } catch (SQLException e) {
            System.err.println("Database connection fail" + e.getMessage());
        }
    }


    @Override
    public void addProject(SnakeTable p) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement("INSERT INTO snaketable (fldName, fldScore, fldBodyparts) VALUES (?, ?, ?)");

        ps.setString(1, p.getName());
        ps.setInt(2, p.getScore());
        ps.setInt(3, p.getBodyparts());

        ps.executeUpdate();
    }
    @Override
    public void deleteProject(SnakeTable p)
    {

    }

    @Override
    public void updateProject(SnakeTable p)
    {

    }

    @Override
    public ArrayList<SnakeTable> getAll() throws SQLException {
        ArrayList<SnakeTable> ARL = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from snaketable order by fldScore , fldBodyparts, fldID desc");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ARL.add(new SnakeTable(rs.getString("fldName"), rs.getInt("fldScore"), rs.getInt("fldBodyparts")));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e; // or return null;
        }
        return ARL;
    }



    @Override
    public SnakeTable get(SnakeTable p)
    {
        return null;
    }
}
