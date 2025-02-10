package controller;

import DAO.MaterialOrder;
import DAO.MaterialWarehousing;
import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialWarehousingTbl {
    private DBCon dbCon;
    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;

    public MaterialWarehousingTbl() throws SQLException {
        this.dbCon = new DBCon();
    }

    public String selectByDateMaxWarehousingNumber(String date) throws SQLException {
        try{
            String maxWarehousingNumber;
            sql = "select ifnull(max(warehousingNumber)+1, '0') maxWarehousingNumber from tbl_materialWarehousing where warehousingDate = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            rs.next();
            maxWarehousingNumber = rs.getString("maxWarehousingNumber");
            return maxWarehousingNumber;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }

    public MaterialWarehousing insert(MaterialWarehousing materialWarehousing) throws SQLException {
        try{
            // 입고 테이블 insert
            sql = "insert into tbl_materialWarehousing(id, warehousingNumber, warehousingDate, wearer, tbl_materialOrder_id) values(null, ?, ?, ?, ?)";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, materialWarehousing.getWarehousingNumber());
            ps.setString(2, materialWarehousing.getWarehousingDate());
            ps.setString(3, materialWarehousing.getWearer());
            ps.setLong(4, materialWarehousing.getTblMaterialOrderId());
            ps.executeUpdate();

            // insert한 테이블 id 가져오기
            sql = "select id from tbl_materialWarehousing where warehousingNumber = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, materialWarehousing.getWarehousingNumber());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            materialWarehousing.setId(rs.getLong("id"));
            return materialWarehousing;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
            ps.close();
        }

    }
}
