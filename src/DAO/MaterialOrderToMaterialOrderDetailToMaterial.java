package DAO;

import static java.sql.Types.NULL;

public class MaterialOrderToMaterialOrderDetailToMaterial extends MaterialOrder{
    private long tblMaterialOrderDetailId = NULL;
    private String orderNumberDetail;
    private int amount;
    private long tblMaterialId;
    private String materialName;
    private String materialCode;

    public MaterialOrderToMaterialOrderDetailToMaterial(long id, String orderNumber, String orderDate, String orderer, long tblMaterialOrderDetailId, String orderNumberDetail, int amount, long tblMaterialId, String materialName, String materialCode) {
        super(id, orderNumber, orderDate, orderer);
        this.tblMaterialOrderDetailId = tblMaterialOrderDetailId;
        this.orderNumberDetail = orderNumberDetail;
        this.amount = amount;
        this.tblMaterialId = tblMaterialId;
        this.materialName = materialName;
        this.materialCode = materialCode;
    }

    public long getTblMaterialOrderDetailId() {
        return tblMaterialOrderDetailId;
    }

    public void setTblMaterialOrderDetailId(long tblMaterialOrderDetailId) {
        this.tblMaterialOrderDetailId = tblMaterialOrderDetailId;
    }

    public String getOrderNumberDetail() {
        return orderNumberDetail;
    }

    public void setOrderNumberDetail(String orderNumberDetail) {
        this.orderNumberDetail = orderNumberDetail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTblMaterialId() {
        return tblMaterialId;
    }

    public void setTblMaterialId(long tblMaterialId) {
        this.tblMaterialId = tblMaterialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
}
