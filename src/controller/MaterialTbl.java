package controller;

import DAO.Material;
import DAO.MaterialToCompany;
import DAO.MaterialToMaterialAmount;
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
    private MaterialToCompany materialToCompany;
    private ArrayList<MaterialToCompany> materialToCompanys;
    private MaterialToMaterialAmount materialToMaterialAmount;
    private ArrayList<MaterialToMaterialAmount> materialToMaterialAmounts;


    public MaterialTbl() throws SQLException {
        this.dbCon = new DBCon();
        this.materials = new ArrayList<>();
        this.materialToCompanys = new ArrayList<>();
        this.materialToMaterialAmounts = new ArrayList<>();
    }

    public boolean insertMaterial(Material material) throws SQLException {
        try{
            sql = "insert into tbl_material values(null, ?, ?, ?, ?, ?)";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, material.getMaterialCode());
            ps.setString(2, material.getMaterialName());
            ps.setString(3, material.getMaterialUnit());
            ps.setInt(4, material.getPrice());
            ps.setLong(5, material.getTblCompanyId());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
         ps.close();
        }

    }

    public Material selectMaterial(String materialCode) throws SQLException {
        try{
            sql = "select * from tbl_material where materialCode = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, materialCode);
            rs = ps.executeQuery();
            rs.next();
            material = new Material(
                    rs.getLong("id"),             rs.getString("materialCode"),
                    rs.getString("materialName"), rs.getString("materialUnit"),
                    rs.getInt("price"),           rs.getLong("tbl_company_id"));
            return material;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public ArrayList<MaterialToCompany> selectAllMaterialToCompany() throws SQLException {
        try{
            sql = "select a.id , a.materialCode , a.materialName , a.materialUnit , a.price , a.tbl_company_id , b.companyName  from tbl_material a, tbl_company b where a.tbl_company_id = b.id";
            ps = dbCon.getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            materialToCompanys.clear();
            while(rs.next()){
                materialToCompany = new MaterialToCompany(
                        rs.getLong("id"),             rs.getString("materialCode"),
                        rs.getString("materialName"), rs.getString("materialUnit"),
                        rs.getInt("price"),           rs.getLong("tbl_company_id"),
                        rs.getString("companyName"));
                materialToCompanys.add(materialToCompany);
            }
            return materialToCompanys;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public boolean updateMaterial(Material material) throws SQLException {
        try{
            sql = "update tbl_material set materialName = ?, materialUnit = ?, price = ?, tbl_company_id = ? where id = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, material.getMaterialName());
            ps.setString(2, material.getMaterialUnit());
            ps.setInt(3, material.getPrice());
            ps.setLong(4, material.getTblCompanyId());
            ps.setLong(5, material.getId());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }

    public boolean deleteMaterial(Long id) throws SQLException {
        try{
            sql = "delete from tbl_material where id = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setLong(1, id);
            updateCount = ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }

    public ArrayList<MaterialToMaterialAmount> selectAllMaterialToMaterialAmount() throws SQLException {
        try{
            sql = "select a.id, a.materialCode, a.materialName, a.materialUnit, a.price, a.tbl_company_id, b.id tbl_materialAmount_id, b.materialAmount" +
                    "  from tbl_material a, tbl_materialAmount b" +
                    " where a.id = b.tbl_material_id";
            ps = dbCon.getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            materialToMaterialAmounts.clear();
            while(rs.next()){
                materialToMaterialAmount = new MaterialToMaterialAmount(
                        rs.getLong("id"),                    rs.getString("materialCode"),
                        rs.getString("materialName"),        rs.getString("materialUnit"),
                        rs.getInt("price"),                  rs.getLong("tbl_company_id"),
                        rs.getLong("tbl_materialAmount_id"), rs.getInt("materialAmount")
                );
                materialToMaterialAmounts.add(materialToMaterialAmount);
            }
            return materialToMaterialAmounts;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public ArrayList<Material> selectAllMaterial() throws SQLException {
        try{
            sql = "select * from tbl_material";
            ps = dbCon.getConn().prepareStatement(sql);
            rs = ps.executeQuery();
            materials.clear();
            while(rs.next()){
                material = new Material(
                        rs.getLong("id"),             rs.getString("materialCode"),
                        rs.getString("materialName"), rs.getString("materialUnit"),
                        rs.getInt("price"),           rs.getLong("tbl_company_id")
                );
                materials.add(material);
            }
            return materials;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }
}
