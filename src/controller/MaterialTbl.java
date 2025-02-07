package controller;

import DAO.Material;
import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialTbl {
    private DBCon dbCon;
    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;
    private int updateCount;

    public MaterialTbl() throws SQLException {
        this.dbCon = new DBCon();
    }

    public boolean insertMaterial(Material material) throws SQLException {
        this.sql = "insert into tbl_material values(null, ?, ?, ?, ?, ?)";
        this.ps = this.dbCon.getConn().prepareStatement(this.sql);
        this.ps.setString(1, material.getMaterialCode());
        this.ps.setString(2, material.getMaterialName());
        this.ps.setString(3, material.getMaterialUnit());
        this.ps.setInt(4, material.getPrice());
        this.ps.setLong(5, material.getTblCompanyId());
        this.updateCount = this.ps.executeUpdate();

        return this.updateCount == 1;
    }
}
