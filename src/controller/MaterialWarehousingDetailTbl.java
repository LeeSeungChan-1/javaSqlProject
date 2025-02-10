package controller;

import DAO.MaterialOrderDetail;
import DAO.MaterialWarehousingDetail;
import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialWarehousingDetailTbl {
    private DBCon dbCon;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public MaterialWarehousingDetailTbl() throws SQLException {
        this.dbCon = new DBCon();
    }

    public boolean insertAll(ArrayList<MaterialWarehousingDetail> materialWarehousingDetails) throws SQLException {
        try{
            for (MaterialWarehousingDetail materialWarehousingDetail : materialWarehousingDetails) {
                sql = "insert into tbl_materialWarehousingDetail(id, warehousingNumberDetail, amount, tbl_material_id, tbl_materialWarehousing_id) values(null, ?, ?, ?, ?)";
                ps = dbCon.getConn().prepareStatement(sql);
                ps.setString(1, materialWarehousingDetail.getWarehousingNumberDetail());
                ps.setInt(2, materialWarehousingDetail.getAmount());
                ps.setLong(3, materialWarehousingDetail.getTblMaterialId());
                ps.setLong(4, materialWarehousingDetail.getTblMaterialWarehousingId());
                ps.executeUpdate();

                System.out.println(
                        materialWarehousingDetail.getWarehousingNumberDetail() + " " + materialWarehousingDetail.getAmount() + " " +
                                materialWarehousingDetail.getTblMaterialId() + " " +     materialWarehousingDetail.getTblMaterialWarehousingId()
                );
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }
}
