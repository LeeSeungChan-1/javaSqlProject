package DAO;

import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.NULL;

public class Material {
    private long id = NULL;
    private String materialCode;
    private String materialName;
    private String materialUnit;
    private int price;
    private Long tblCompanyId;


    public Material(String materialCode, String materialName, String materialUnit, int price, Long tblCompanyId) throws SQLException {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialUnit = materialUnit;
        this.price = price;
        this.tblCompanyId = tblCompanyId;

    }

    public Long getTblCompanyId() {
        return tblCompanyId;
    }

    public void setTblCompanyId(Long tblCompanyId) {
        this.tblCompanyId = tblCompanyId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
