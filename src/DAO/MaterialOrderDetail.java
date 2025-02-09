package DAO;

import static java.sql.Types.NULL;

public class MaterialOrderDetail {
    private long id = NULL;
    private String orderNumberDetail;
    private int amount;
    private long tblMaterialId = NULL;
    private long tblMaterialOrderId = NULL;

    public MaterialOrderDetail(String orderNumberDetail, int amount, long tblMaterialId, long tblMaterialOrderId) {
        this.orderNumberDetail = orderNumberDetail;
        this.amount = amount;
        this.tblMaterialId = tblMaterialId;
        this.tblMaterialOrderId = tblMaterialOrderId;
    }

    public MaterialOrderDetail(long id, String orderNumberDetail, int amount, long tblMaterialId, long tblMaterialOrderId) {
        this.id = id;
        this.orderNumberDetail = orderNumberDetail;
        this.amount = amount;
        this.tblMaterialId = tblMaterialId;
        this.tblMaterialOrderId = tblMaterialOrderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getTblMaterialOrderId() {
        return tblMaterialOrderId;
    }

    public void setTblMaterialOrderId(long tblMaterialOrderId) {
        this.tblMaterialOrderId = tblMaterialOrderId;
    }
}
