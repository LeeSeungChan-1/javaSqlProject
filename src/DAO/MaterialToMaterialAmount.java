package DAO;

import java.sql.SQLException;

public class MaterialToMaterialAmount extends Material{
    private long tblMaterialAmountId;
    private int materialAmount;

    public MaterialToMaterialAmount(long id, String materialCode, String materialName, String materialUnit, int price, long tblCompanyId, long tblMaterialAmountId, int materialAmount) throws SQLException {
        super(id, materialCode, materialName, materialUnit, price, tblCompanyId);
        this.tblMaterialAmountId = tblMaterialAmountId;
        this.materialAmount = materialAmount;
    }

    public int getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(int materialAmount) {
        this.materialAmount = materialAmount;
    }
}
