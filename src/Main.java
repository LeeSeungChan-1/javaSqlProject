import DAO.User;
import connection.DBCon;
import controller.UserTbl;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBCon dbCon = new DBCon();
        UserTbl usertbl = new UserTbl(dbCon);
        User user2;
        User user3;
        User user4;
        User inputUser;

        ArrayList<User> users = new ArrayList<User>();

        users = usertbl.findUserAll();

        System.out.println("id idNumber password name grade tbl_company_id");

        for (User user : users) {

            System.out.println(
                    user.getId()       + " " + user.getIdNumber() + " " +
                    user.getPassword() + " " + user.getName()     + " " +
                    user.getGrade()    + " " + user.getTblCompanyId());
        }

        user2 = usertbl.findUserByID(2);
        if(user2 != null) {
            System.out.println("id idNumber password name grade tbl_company_id");
            System.out.println(
                    user2.getId()       + " " + user2.getIdNumber() + " " +
                    user2.getPassword() + " " + user2.getName()     + " " +
                    user2.getGrade()    + " " + user2.getTblCompanyId());
        }else{
            System.out.println("fail");
        }

        user3 = new User(NULL, "20250219", "20250219", "황기현", "4", 4);
        if(usertbl.addUser(user3)){
            System.out.println("success");
        }else{
            System.out.println("fail2");
        }

        inputUser = new User(18, "20250218", "newPassword2", "황기훈", "3", 3);
        user4 = usertbl.findUserByID(inputUser.getId());
        if(user4 != null) {
            if(usertbl.updateUser(inputUser)){
                System.out.println("success");
            }else{
                System.out.println("fail");
            }
        }else{
            System.out.println("user not found");
        }


        dbCon.dbClose();
    }
}