package DAO;

import java.sql.SQLException;

public class MaterialToCompany extends Material{
    private String companyName;

    public MaterialToCompany(long id, String materialCode, String materialName, String materialUnit, int price, long tblCompanyId, String companyName) throws SQLException {
        super(id, materialCode, materialName, materialUnit, price, tblCompanyId);
        this.companyName = companyName;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
