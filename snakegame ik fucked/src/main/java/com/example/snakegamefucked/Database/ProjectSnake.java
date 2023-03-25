package com.example.snakegamefucked.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectSnake
{
    void addProject(SnakeTable p) throws SQLException;
    void deleteProject(SnakeTable p);
    void updateProject(SnakeTable p);
    ArrayList<SnakeTable> getAll () throws SQLException;
    SnakeTable get (SnakeTable p);
}
