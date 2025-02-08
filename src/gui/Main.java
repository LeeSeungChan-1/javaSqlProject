/*
 * Created by JFormDesigner on Sat Feb 08 16:37:33 KST 2025
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
public class Main  {
    private String dept;

    public Main(User user) {
        initComponents();

        if(user.getTblCompanyId() == 1){
            dept = "자재";
            jlTop.setText(dept + "관리");
            jbSelectAll.setText(dept + "전체조회");
            jbInsert.setText(dept + "등록");
            jbSelect.setText(dept + "조회");

        }

    }


    private void jbSelectAll(ActionEvent e) throws SQLException {
        // TODO add your code here
        new SelectAll(dept);
    }

    private void jbInsert(ActionEvent e) throws SQLException {
        // TODO add your code here
        new Insert(dept);
    }

    private void jbSelect(ActionEvent e) throws SQLException {
        // TODO add your code here
        new Select(dept);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpMain = new JPanel();
        jbSelectAll = new JButton();
        jbInsert = new JButton();
        jbSelect = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(500, 200));
            jfMain.setTitle("@\uad00\ub9ac : \uc774\ub984 : \uc0ac\ubc88");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
                0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
                .BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),java.awt.Color.
                red),jpTop. getBorder()));jpTop. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
                beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException();}});
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
                jpMain.setLayout(new GridLayout(1, 3));

                //---- jbSelectAll ----
                jbSelectAll.setText("@\uc804\uccb4\uc870\ud68c");
                jbSelectAll.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelectAll.addActionListener(e -> {try {
jbSelectAll(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbSelectAll);

                //---- jbInsert ----
                jbInsert.setText("@\ub4f1\ub85d");
                jbInsert.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbInsert.addActionListener(e -> {try {
jbInsert(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbInsert);

                //---- jbSelect ----
                jbSelect.setText("@\uc870\ud68c");
                jbSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelect.addActionListener(e -> {try {
jbSelect(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbSelect);
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
    private JButton jbSelectAll;
    private JButton jbInsert;
    private JButton jbSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
