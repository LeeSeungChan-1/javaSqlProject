/*
 * Created by JFormDesigner on Fri Feb 07 11:07:26 KST 2025
 */

package gui;

import DAO.User;
import connection.DBCon;
import controller.UserTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author seung
 */
public class Login {
    public Login() {
        initComponents();
    }

    private void jbLogin(ActionEvent e) throws SQLException {
        // TODO add your code here
        DBCon dbCon = new DBCon();
        UserTbl userTbl = new UserTbl(dbCon);

        String id = jtfId.getText().trim();
        String password = jtfPassword.getText().trim();

        User user = userTbl.selectUserByIdNumber(id);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "사용자를 찾을 수 없습니다.");
        }else{
            if(id.equals(user.getIdNumber()) && password.equals(user.getPassword())) {
//                JOptionPane.showMessageDialog(null, "로그인 성공");
                // 메인 창 띄워주기
                new ChooseMenu(user);
                jfLogin.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "아이디 혹은 비빌번호가 다릅니다.");
            }
        }


        

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfLogin = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpMain = new JPanel();
        jlId = new JLabel();
        jtfId = new JTextField();
        jlPassword = new JLabel();
        jtfPassword = new JPasswordField();
        jbLogin = new JButton();
        jbChange = new JButton();

        //======== jfLogin ========
        {
            jfLogin.setVisible(true);
            jfLogin.setTitle("\ub85c\uadf8\uc778");
            jfLogin.setResizable(false);
            jfLogin.setPreferredSize(new Dimension(500, 300));
            var jfLoginContentPane = jfLogin.getContentPane();
            jfLoginContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
                ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
                . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
                propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("\ub85c\uadf8\uc778");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 36));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
            }
            jfLoginContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(6, 1));

                //---- jlId ----
                jlId.setText("\uc544\uc774\ub514");
                jlId.setHorizontalAlignment(SwingConstants.CENTER);
                jlId.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlId);

                //---- jtfId ----
                jtfId.setHorizontalAlignment(SwingConstants.CENTER);
                jtfId.setToolTipText("\uc544\uc774\ub514\ub97c \uc785\ub825\ud558\uc138\uc694.");
                jtfId.setText("20250209");
                jpMain.add(jtfId);

                //---- jlPassword ----
                jlPassword.setText("\ube44\ubc00\ubc88\ud638");
                jlPassword.setHorizontalAlignment(SwingConstants.CENTER);
                jlPassword.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlPassword);

                //---- jtfPassword ----
                jtfPassword.setHorizontalAlignment(SwingConstants.CENTER);
                jtfPassword.setToolTipText("\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud558\uc138\uc694.");
                jtfPassword.setText("20250209");
                jpMain.add(jtfPassword);

                //---- jbLogin ----
                jbLogin.setText("\ub85c\uadf8\uc778");
                jbLogin.addActionListener(e -> {try {
jbLogin(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbLogin);

                //---- jbChange ----
                jbChange.setText("\ube44\ubc00\ubc88\ud638 \ubcc0\uacbd");
                jpMain.add(jbChange);
            }
            jfLoginContentPane.add(jpMain, BorderLayout.CENTER);
            jfLogin.pack();
            jfLogin.setLocationRelativeTo(jfLogin.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - LeeSeungChan
    private JFrame jfLogin;
    private JPanel jpTop;
    private JLabel jlTop;
    private JPanel jpMain;
    private JLabel jlId;
    private JTextField jtfId;
    private JLabel jlPassword;
    private JPasswordField jtfPassword;
    private JButton jbLogin;
    private JButton jbChange;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
