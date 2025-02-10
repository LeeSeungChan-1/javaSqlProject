package DAO;

import static java.sql.Types.NULL;

public class MaterialWarehousing {
    private long id = NULL;
    private String warehousingNumber;
    private String warehousingDate;
    private String wearer;
    private long tblMaterialOrderId;

    public MaterialWarehousing(String warehousingNumber, String warehousingDate, String wearer, long tblMaterialOrderId) {
        this.warehousingNumber = warehousingNumber;
        this.warehousingDate = warehousingDate;
        this.wearer = wearer;
        this.tblMaterialOrderId = tblMaterialOrderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWarehousingNumber() {
        return warehousingNumber;
    }

    public void setWarehousingNumber(String warehousingNumber) {
        this.warehousingNumber = warehousingNumber;
    }

    public String getWarehousingDate() {
        return warehousingDate;
    }

    public void setWarehousingDate(String warehousingDate) {
        this.warehousingDate = warehousingDate;
    }

    public String getWearer() {
        return wearer;
    }

    public void setWearer(String wearer) {
        this.wearer = wearer;
    }

    public long getTblMaterialOrderId() {
        return tblMaterialOrderId;
    }

    public void setTblMaterialOrderId(long tblMaterialOrderId) {
        this.tblMaterialOrderId = tblMaterialOrderId;
    }
}
