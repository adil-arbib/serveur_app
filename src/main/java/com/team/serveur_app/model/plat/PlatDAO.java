package com.team.serveur_app.model.plat;


import com.team.serveur_app.model.Database;
import com.team.serveur_app.model.ResourcesManager;
import com.team.serveur_app.model.categorie.Categorie;
import com.team.serveur_app.model.categorie.CategorieDAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {


    public PlatDAO(int id, String nom, float price, String description, byte[] img, Categorie categorie) {
        super(id, nom, price, description, img, categorie);
    }

    public PlatDAO(String nom, float price, String description, byte[] img, Categorie categorie) {
        super(nom, price, description, img, categorie);
    }

    public PlatDAO() {
        super();
    }

    @Override
    public int add() throws SQLException, ParseException {
        String sql = "insert into plat(nom,price,description,img,id_cat) " +
                "values (?,?,?,?,?)";
        InputStream inputStream = new ByteArrayInputStream(img); // convert byte array to inputStream
        PreparedStatement ps = ResourcesManager.getConnection().prepareStatement(sql);
        ps.setString(1,nom);
        ps.setFloat(2,price);
        ps.setString(3,description);
        ps.setBlob(4,inputStream);
        ps.setInt(5,categorie.getId());
        ps.executeUpdate();
        Statement s = ResourcesManager.getConnection().createStatement();
        ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID();");
        if (rs.next())return rs.getInt(1);
        return 0;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("UPDATE plat set nom=?, price=?, description=?, img=?,id_cat=?" +
                " WHERE id = ?;");
        InputStream inputStream = new ByteArrayInputStream(img); // convert byte array to inputStream
        ps.setString(1,nom);
        ps.setFloat(2,price);
        ps.setString(3,description);
        ps.setBlob(4,inputStream);
        ps.setInt(5,categorie.getId());
        ps.setInt(6,id);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM plat WHERE id=?;");
        ps.setInt(1,id );
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from plat where id = ? ;");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Blob clob = rs.getBlob(5);
            byte[] byteArr = clob.getBytes(1,(int)clob.length()); // retrieving image from db
            CategorieDAO cDAO = new CategorieDAO();
            cDAO.setId(rs.getInt(6));
            Categorie categorie = (Categorie) cDAO.select(); // select categorie by id_cat
            return new Plat(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4),
                    byteArr, // image as byte array
                    categorie
            );
        }
        return null;
    }

    public static ArrayList<Plat> selectPLatByIdCat(int id_cat) throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * FROM plat Where id_cat = ?");
        ps.setInt(1,id_cat);
        ResultSet rs = ps.executeQuery();
        ArrayList<Plat> p = new ArrayList<>();
        while (rs.next()){
            Blob clob = rs.getBlob(5);
            byte[] byteArr = clob.getBytes(1,(int)clob.length());
            CategorieDAO cDAO = new CategorieDAO();
            cDAO.setId(rs.getInt(6));
            Categorie categorie = (Categorie) cDAO.select();
            p.add( new Plat(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4),
                    byteArr,
                   categorie));
        }
        return p;
    }





    /**
     * get all plats
     * @return
     * @throws SQLException
     */
    public static ArrayList<Plat> getAll() throws SQLException {
        Statement st = ResourcesManager.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from plat");
        ArrayList<Plat> plats = new ArrayList<>();
        while (rs.next()){
            Blob clob = rs.getBlob(5);
            byte[] byteArr = clob.getBytes(1,(int)clob.length()); // retrieving image from db
            CategorieDAO cDAO = new CategorieDAO();
            cDAO.setId(rs.getInt(6));
            Categorie categorie = (Categorie) cDAO.select(); // select categorie by id_cat
            plats.add(new Plat(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4),
                    byteArr, // image as byte array
                    categorie
            ));
        }
        return plats;
    }


}







