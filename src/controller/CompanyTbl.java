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
        this.sql = "SELECT id, companyName FROM tbl_company";
        this.rs = this.dbCon.getStmt().executeQuery(sql);

        while (rs.next()) {
            comboItems.add(new ComboItem(this.rs.getLong("id"), rs.getString("companyName")));
        }

        this.rs.close();
        return comboItems;
    }
}
