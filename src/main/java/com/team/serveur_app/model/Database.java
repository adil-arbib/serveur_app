package com.team.serveur_app.model;

import java.sql.SQLException;
import java.text.ParseException;

public interface Database {
    int add() throws SQLException, ParseException;
    boolean update() throws SQLException, ParseException;
    boolean delete() throws SQLException;
    Object select() throws SQLException;

}
