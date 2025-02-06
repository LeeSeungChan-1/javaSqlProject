package controller;

import DAO.User;
import connection.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserTbl {
    private DBCon dbCon;
    private User user;
    private ArrayList<User> users = new ArrayList<User>();
    private ResultSet rs;
    private int updateCount;
    private String sql;
    private PreparedStatement ps;


    public UserTbl(DBCon dbCon) {
        this.dbCon = dbCon;
    }

    public ArrayList<User> findUserAll() throws SQLException {
        this.sql = "select * from tbl_user";
        this.rs = this.dbCon.getStmt().executeQuery(this.sql);

        while (this.rs.next()) {
            this.user = new User(
                    this.rs.getLong("id"),         this.rs.getString("idNumber"),
                    this.rs.getString("password"), this.rs.getString("name"),
                    this.rs.getString("grade"),    this.rs.getLong("tbl_company_id"));

            this.users.add(user);
        }
        this.rs.close();
        return this.users;
    }

    public User findUserByID(long id) throws SQLException {
        this.sql = "select * from tbl_user where id = " + id;
        this.rs = this.dbCon.getStmt().executeQuery(this.sql);

        if(this.rs.next()){
            this.user = new User(
                    this.rs.getLong("id"),         this.rs.getString("idNumber"),
                    this.rs.getString("password"), this.rs.getString("name"),
                    this.rs.getString("grade"),    this.rs.getLong("tbl_company_id"));
            this.rs.close();
            return this.user;
        }

        this.rs.close();
        return null;
    }

    public boolean addUser(User user) throws SQLException {
        this.sql = "insert into tbl_user values(null, ?, ?, ?, ?, ?)";
        this.ps = this.dbCon.getConn().prepareStatement(this.sql);
        this.ps.setString(1, user.getIdNumber());
        this.ps.setString(2, user.getPassword());
        this.ps.setString(3, user.getName());
        this.ps.setString(4, user.getGrade());
        this.ps.setLong(5, user.getTblCompanyId());
        this.updateCount = this.ps.executeUpdate();

        return this.updateCount == 1;
    }

    public boolean updateUser(User user) throws SQLException {
        this.sql = "update tbl_user set password = ?, name = ?, grade = ?, tbl_company_id = ? where id = ?";
        this.ps = this.dbCon.getConn().prepareStatement(this.sql);
        this.ps.setString(1, user.getPassword());
        this.ps.setString(2, user.getName());
        this.ps.setString(3, user.getGrade());
        this.ps.setLong(4, user.getTblCompanyId());
        this.ps.setLong(5, user.getId());
        this.updateCount = this.ps.executeUpdate();

        return this.updateCount == 1;
    }

    public boolean deleteUser(long id) throws SQLException {
        this.sql = "delete from tbl_user where id = " + id;
        this.ps = this.dbCon.getConn().prepareStatement(this.sql);
        this.updateCount = this.ps.executeUpdate();

        return this.updateCount == 1;
    }
}
