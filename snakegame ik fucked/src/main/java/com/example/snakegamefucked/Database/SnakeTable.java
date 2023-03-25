package com.example.snakegamefucked.Database;

public class SnakeTable
{
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public int getBodyparts()
    {
        return bodyparts;
    }

    public void setBodyparts(int bodyparts)
    {
        this.bodyparts = bodyparts;
    }

    public int getScore()
    {
        return Score;
    }

    public void setScore(int score)
    {
        Score = score;
    }

    private int  ID;
    private String Name;
    private int bodyparts;

    public SnakeTable(String name, int bodyparts, int score) {
        Name = name;
        this.bodyparts = bodyparts;
        Score = score;
    }

    private  int Score;
}
