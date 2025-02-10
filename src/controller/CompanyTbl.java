package controller;

import DAO.ComboItem;
import connection.DBCon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyTbl {
    private DBCon dbCon;
    private ArrayList<ComboItem> comboItems = new ArrayList<>();
    private String sql;
    private ResultSet rs;

    public CompanyTbl() throws SQLException {
        this.dbCon = new DBCon();
    }

    public ArrayList<ComboItem> selectCompanyAll() throws SQLException {
        try{
            sql = "SELECT id, companyName FROM tbl_company";
            rs = dbCon.getStmt().executeQuery(sql);

            while (rs.next()) {
                comboItems.add(new ComboItem(rs.getLong("id"), rs.getString("companyName")));
            }
            return comboItems;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
        }

    }
}
