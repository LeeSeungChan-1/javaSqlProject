package DAO;

import static java.sql.Types.NULL;

public class MaterialWarehousingDetail {
    private long id = NULL;
    private String warehousingNumberDetail;
    private int amount;
    private long tblMaterialId;
    private long tblMaterialWarehousingId;

    public MaterialWarehousingDetail(String warehousingNumberDetail, int amount, long tblMaterialId, long tblMaterialWarehousingId) {
        this.warehousingNumberDetail = warehousingNumberDetail;
        this.amount = amount;
        this.tblMaterialId = tblMaterialId;
        this.tblMaterialWarehousingId = tblMaterialWarehousingId;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWarehousingNumberDetail() {
        return warehousingNumberDetail;
    }

    public void setWarehousingNumberDetail(String warehousingNumberDetail) {
        this.warehousingNumberDetail = warehousingNumberDetail;
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

    public long getTblMaterialWarehousingId() {
        return tblMaterialWarehousingId;
    }

    public void setTblMaterialWarehousingId(long tblMaterialWarehousingId) {
        this.tblMaterialWarehousingId = tblMaterialWarehousingId;
    }
}
