package controller;

import DAO.MaterialOrderDetail;
import connection.DBCon;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialOrderDetailTbl {
    private DBCon dbCon;
    private MaterialOrderDetail materialOrderDetail;
    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;
    private int updateCount;

    public MaterialOrderDetailTbl() throws SQLException {
        this.dbCon = new DBCon();
    }

    public boolean insertAll(ArrayList<MaterialOrderDetail> materialOrderDetails) throws SQLException {
        for (MaterialOrderDetail materialOrderDetail : materialOrderDetails) {
            sql = "insert into tbl_materialOrderDetail(id, orderNumberDetail, amount, tbl_material_id, tbl_materialOrder_id) values(null, ?, ?, ?, ?)";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, materialOrderDetail.getOrderNumberDetail());
            ps.setInt(2, materialOrderDetail.getAmount());
            ps.setLong(3, materialOrderDetail.getTblMaterialId());
            ps.setLong(4, materialOrderDetail.getTblMaterialOrderId());

            System.out.println(
                    materialOrderDetail.getOrderNumberDetail() + " " + materialOrderDetail.getAmount() + " " +
                    materialOrderDetail.getTblMaterialId() + " " +     materialOrderDetail.getTblMaterialOrderId()
            );

            updateCount +=  ps.executeUpdate();
        }
        ps.close();

        return updateCount == materialOrderDetails.size();

    }
}
