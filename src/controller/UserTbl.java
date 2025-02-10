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
    private ArrayList<User> users = new ArrayList<>();
    private ResultSet rs;
    private int updateCount;
    private String sql;
    private PreparedStatement ps;


    public UserTbl(DBCon dbCon) {
        this.dbCon = dbCon;
    }

    public ArrayList<User> selectAllUser() throws SQLException {
        try{
            sql = "select * from tbl_user";
            rs = dbCon.getStmt().executeQuery(sql);

            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),         rs.getString("idNumber"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("grade"),    rs.getLong("tbl_company_id"));

                users.add(user);
            }

            return users;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
        }

    }

    public User selectUserByID(long id) throws SQLException {
        try{
            sql = "select * from tbl_user where id = " + id;
            rs = dbCon.getStmt().executeQuery(sql);

            if(rs.next()){
                user = new User(
                        rs.getLong("id"),         rs.getString("idNumber"),
                        rs.getString("password"), rs.getString("name"),
                        rs.getString("grade"),    rs.getLong("tbl_company_id"));
                rs.close();
                return user;
            }

            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
        }

    }

    public boolean insertUser(User user) throws SQLException {
        try{
            sql = "insert into tbl_user values(null, ?, ?, ?, ?, ?)";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, user.getIdNumber());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getGrade());
            ps.setLong(5, user.getTblCompanyId());
            updateCount = ps.executeUpdate();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }

    public boolean updateUser(User user) throws SQLException {
        try{
            sql = "update tbl_user set password = ?, name = ?, grade = ?, tbl_company_id = ? where id = ?";
            ps = dbCon.getConn().prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getGrade());
            ps.setLong(4, user.getTblCompanyId());
            ps.setLong(5, user.getId());
            updateCount = ps.executeUpdate();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }

    public boolean deleteUser(long id) throws SQLException {
        try{
            sql = "delete from tbl_user where id = " + id;
            ps = dbCon.getConn().prepareStatement(sql);
            updateCount = ps.executeUpdate();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            ps.close();
        }

    }

    public User selectUserByIdNumber(String idNumber) throws SQLException {
        try{
            sql = "select * from tbl_user where idNumber = " + idNumber;
            rs = dbCon.getStmt().executeQuery(sql);

            rs.next();
            user = new User(
                    rs.getLong("id"),         rs.getString("idNumber"),
                    rs.getString("password"), rs.getString("name"),
                    rs.getString("grade"),    rs.getLong("tbl_company_id"));

            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            rs.close();
        }

    }
}
