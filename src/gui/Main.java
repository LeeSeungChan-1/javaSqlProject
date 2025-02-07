/*
 * Created by JFormDesigner on Fri Feb 07 11:44:14 KST 2025
 */

package gui;

import DAO.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author seung
 */
public class Main {
    private String dept;
    public Main(User user) {
        initComponents();

        System.out.println(user.getTblCompanyId());
        if(user.getTblCompanyId() == 1) {
            dept = "자재";
            jlTop.setText(dept);
            jbRegister.setText(dept + "등록");
            jbSearch.setText(dept + "조회");
            jbModify.setText(dept + "수정");
            jbDelete.setText(dept + "삭제");
            jfMain.setTitle(dept + " : " + user.getName() + " : " + user.getIdNumber());
        }
    }

    private void jbRegister(ActionEvent e) throws SQLException {
        // TODO add your code here
        System.out.println(123);
        new Register(dept);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpMain = new JPanel();
        jbRegister = new JButton();
        jbSearch = new JButton();
        jbModify = new JButton();
        jbDelete = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(500, 300));
            jfMain.setTitle("\ub85c\uadf8\uc778\ud55c \uc0ac\ub78c \uba85 + \uc0ac\ubc88");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
                ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
                . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
                propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\uad00\ub9ac");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 30));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
            }
            jfMainContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(2, 2));

                //---- jbRegister ----
                jbRegister.setText("@\ub4f1\ub85d");
                jbRegister.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbRegister.addActionListener(e -> {try {
jbRegister(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbRegister);

                //---- jbSearch ----
                jbSearch.setText("@\uc870\ud68c");
                jbSearch.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jbSearch);

                //---- jbModify ----
                jbModify.setText("@\uc218\uc815");
                jbModify.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jbModify);

                //---- jbDelete ----
                jbDelete.setText("@\uc0ad\uc81c");
                jbDelete.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jbDelete);
            }
            jfMainContentPane.add(jpMain, BorderLayout.CENTER);
            jfMain.pack();
            jfMain.setLocationRelativeTo(jfMain.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - LeeSeungChan
    private JFrame jfMain;
    private JPanel jpTop;
    private JLabel jlTop;
    private JPanel jpMain;
    private JButton jbRegister;
    private JButton jbSearch;
    private JButton jbModify;
    private JButton jbDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
