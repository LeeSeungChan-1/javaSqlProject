package controller;

import DAO.MaterialOrder;
import DAO.MaterialOrderToMaterialOrderDetailToMaterial;
import connection.DBCon;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialOrderTbl {
    private DBCon dbCon;
    private Connection conn;
    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;
    private ArrayList<MaterialOrder> materialOders;
    private MaterialOrder materialOrder;
    private int updateCount;
    private MaterialOrderDetailTbl materialOrderDetailTbl;
    private MaterialOrderToMaterialOrderDetailToMaterial materialOrderToMaterialOrderDetailToMaterial;
    private ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> materialOrderToMaterialOrderDetailToMaterials;

    public MaterialOrderTbl() throws SQLException {
        this.dbCon = new DBCon();
        this.conn = dbCon.getConn();
        this.materialOders = new ArrayList<>();
        this.materialOrderDetailTbl = new MaterialOrderDetailTbl();
        this.materialOrderToMaterialOrderDetailToMaterials = new ArrayList<>();
    }

    public String selectByDateMaxOrderNumber(String date) throws SQLException {
        try{
            String maxOrderNumber = "";
            sql = "select ifnull(max(orderNumber), 0) maxOrderNumber from tbl_materialOrder where orderDate = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            rs.next();
            maxOrderNumber = rs.getString("maxOrderNumber");
            return maxOrderNumber;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public MaterialOrder insert(MaterialOrder materialOrder) throws SQLException {
        try{
            // 발주 테이블 insert
            sql = "insert into tbl_materialOrder values(null, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, materialOrder.getOrderNumber());
            ps.setString(2, materialOrder.getOrderDate());
            ps.setString(3, materialOrder.getOrderer());
            ps.executeUpdate();

            // insert한 테이블 id 가져오기
            sql = "select id from tbl_materialOrder where orderNumber = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, materialOrder.getOrderNumber());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            materialOrder.setId(rs.getLong("id"));
            return materialOrder;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> selectByOrderNumber(String orderNumber) throws SQLException {
        try{
            materialOrderToMaterialOrderDetailToMaterials.clear();

            sql = "select a.id idA, a.orderNumber, a.orderDate, a.orderer, b.id idB, b.orderNumberDetail, b.amount, b.tbl_material_id, c.materialCode, c.materialName" +
                    "  from tbl_materialOrder a, tbl_materialOrderDetail b, tbl_material c" +
                    " where a.orderNumber = ?" +
                    "   and a.id = b.tbl_materialOrder_id" +
                    "   and b.tbl_material_id = c.id";
            ps = conn.prepareStatement(sql);
            ps.setString(1, orderNumber);
            rs = ps.executeQuery();

            while(rs.next()) {
                materialOrderToMaterialOrderDetailToMaterial = new MaterialOrderToMaterialOrderDetailToMaterial(
                        rs.getLong("idA"),       rs.getString("orderNumber"),   rs.getString("orderDate"),
                        rs.getString("orderer"), rs.getLong("idB"),             rs.getString("orderNumberDetail"),
                        rs.getInt("amount"),     rs.getLong("tbl_material_id"), rs.getString("materialCode"),
                        rs.getString("materialName")
                );
                materialOrderToMaterialOrderDetailToMaterials.add(materialOrderToMaterialOrderDetailToMaterial);
            }
            return materialOrderToMaterialOrderDetailToMaterials;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> selectAllToMaterialOrderDetailToMaterial() throws SQLException {
        try{
            sql = "select a.id idA, a.orderNumber, a.orderDate, a.orderer, b.id idB, b.orderNumberDetail, b.amount, b.tbl_material_id, c.materialCode, c.materialName" +
                    "  from tbl_materialOrder a, tbl_materialOrderDetail b, tbl_material c" +
                    " where a.id = b.tbl_materialOrder_id" +
                    "   and b.tbl_material_id = c.id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            materialOrderToMaterialOrderDetailToMaterials.clear();
            while(rs.next()) {
                materialOrderToMaterialOrderDetailToMaterial = new MaterialOrderToMaterialOrderDetailToMaterial(
                        rs.getLong("idA"),       rs.getString("orderNumber"),   rs.getString("orderDate"),
                        rs.getString("orderer"), rs.getLong("idB"),             rs.getString("orderNumberDetail"),
                        rs.getInt("amount"),     rs.getLong("tbl_material_id"), rs.getString("materialCode"),
                        rs.getString("materialName")
                );
                materialOrderToMaterialOrderDetailToMaterials.add(materialOrderToMaterialOrderDetailToMaterial);
            }
            return materialOrderToMaterialOrderDetailToMaterials;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public boolean deleteMaterialOrder(MaterialOrder materialOrder) throws SQLException {
        try{
            sql = "delete from tbl_materialOrder where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, materialOrder.getId());
            ps.executeUpdate();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }


    }
}
