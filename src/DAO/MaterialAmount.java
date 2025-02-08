package DAO;

public class MaterialAmount {
    private long id;
    private int materialAmount;
    private long tblMaterialId;

    public MaterialAmount(long id, int materialAmount, long tblMaterialId) {
        this.id = id;
        this.materialAmount = materialAmount;
        this.tblMaterialId = tblMaterialId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(int materialAmount) {
        this.materialAmount = materialAmount;
    }

    public long getTblMaterialId() {
        return tblMaterialId;
    }

    public void setTblMaterialId(long tblMaterialId) {
        this.tblMaterialId = tblMaterialId;
    }
}
