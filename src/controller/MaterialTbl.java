package controller;

import DAO.Material;
import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialTbl {
    private DBCon dbCon;
    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;
    private int updateCount;
    private Material material;
    private ArrayList<Material> materials;


    public MaterialTbl() throws SQLException {
        this.dbCon = new DBCon();
        this.materials = new ArrayList<>();
    }

    public boolean insertMaterial(Material material) throws SQLException {
        sql = "insert into tbl_material values(null, ?, ?, ?, ?, ?)";
        ps = dbCon.getConn().prepareStatement(sql);
        ps.setString(1, material.getMaterialCode());
        ps.setString(2, material.getMaterialName());
        ps.setString(3, material.getMaterialUnit());
        ps.setInt(4, material.getPrice());
        ps.setLong(5, material.getTblCompanyId());
        updateCount = ps.executeUpdate();

        return updateCount == 1;
    }

    public Material selectMaterial(String materialCode) throws SQLException {
        sql = "select * from tbl_material where materialCode = ?";
        ps = dbCon.getConn().prepareStatement(sql);
        ps.setString(1, materialCode);
        rs = ps.executeQuery();
        if(rs.next()){
            material = new Material(
                    rs.getLong("id"),             rs.getString("materialCode"),
                    rs.getString("materialName"), rs.getString("materialUnit"),
                    rs.getInt("price"),           rs.getLong("tbl_company_id"));
            return material;
        }else{
            return null;
        }
    }

    public ArrayList<Material> selectAllMaterial() throws SQLException {
        sql = "select * from tbl_material";
        ps = dbCon.getConn().prepareStatement(sql);
        rs = ps.executeQuery();
        materials.clear();
        while(rs.next()){
            material = new Material(
                    rs.getLong("id"),             rs.getString("materialCode"),
                    rs.getString("materialName"), rs.getString("materialUnit"),
                    rs.getInt("price"),           rs.getLong("tbl_company_id"));
            materials.add(material);
        }
        rs.close();
        return materials;
    }

    public boolean updateMaterial(Material material) throws SQLException {
        sql = "update tbl_material set materialName = ?, materialUnit = ?, price = ?, tbl_company_id = ? where id = ?";
        ps = dbCon.getConn().prepareStatement(sql);
        ps.setString(1, material.getMaterialName());
        ps.setString(2, material.getMaterialUnit());
        ps.setInt(3, material.getPrice());
        ps.setLong(4, material.getTblCompanyId());
        ps.setLong(5, material.getId());
        updateCount = ps.executeUpdate();

        ps.close();
        return updateCount == 1;

    }

    public boolean deleteMaterial(Long id) throws SQLException {
        sql = "delete from tbl_material where id = ?";
        ps = dbCon.getConn().prepareStatement(sql);
        ps.setLong(1, id);
        updateCount = ps.executeUpdate();
        ps.close();
        return updateCount == 1;
    }
}
