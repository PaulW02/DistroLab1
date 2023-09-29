package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ItemDB {

    public static List<Item> searchItems(String item){
        ArrayList<Item> items = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from items where name = item");

            while (rs.next()){
                int i = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                items.add(new Item(i, name, desc));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

}