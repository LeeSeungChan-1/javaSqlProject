package DAO;

import static java.sql.Types.NULL;

public class User {
    private long id = NULL;
    private String idNumber;
    private String password;
    private String name;
    private String grade;
    private long tblCompanyId;

    public User(long id, String idNumber, String password, String name, String grade, long tblCompanyId) {
        this.id = id;
        this.idNumber = idNumber;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.tblCompanyId = tblCompanyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public long getTblCompanyId() {
        return tblCompanyId;
    }

    public void setTblCompanyId(long tblCompanyId) {
        this.tblCompanyId = tblCompanyId;
    }
}

