/*
 * Created by JFormDesigner on Sat Feb 08 22:19:31 KST 2025
 */

package gui.materialOrder;

import DAO.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author seung
 */
public class MaterialOrder extends JFrame {
    private String dept;
    private User user;
    
    public MaterialOrder(User user) {
        initComponents();

        this.user = user;

        if(user.getTblCompanyId() == 1){
            this.dept = "자재";
            this.jfMain.setTitle(dept + "발주 : " + user.getName() + " : " + user.getIdNumber());
            this.jlTop.setText(dept + "발주");
            this.jbSelect.setText(dept + "발주조회");
            this.jbSelectAll.setText(dept + "발주전체조회");
            this.jbInsert.setText(dept + "발주등록");

        }
    }

    private void jbInsert(ActionEvent e) throws SQLException {
        // TODO add your code here
        new MaterialOrderInsert(user);
    }

    private void jbSelect(ActionEvent e) throws SQLException {
        // TODO add your code here
        new MaterialOrderSelect(user);
    }

    private void jbSelectAll(ActionEvent e) throws SQLException {
        // TODO add your code here
        new MaterialOrderSelectAll(user);
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
        button4 = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(500, 200));
            jfMain.setTitle("@\ubc1c\uc8fc : \uc774\ub984 : \uc0ac\ubc88");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
                EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing
                . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
                java . awt. Color .red ) ,jpTop. getBorder () ) ); jpTop. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
                { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )
                throw new RuntimeException( ) ;} } );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ubc1c\uc8fc");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 30));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
            }
            jfMainContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(2, 2));

                //---- jbSelectAll ----
                jbSelectAll.setText("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
                jbSelectAll.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelectAll.addActionListener(e -> {try {
jbSelectAll(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbSelectAll);

                //---- jbInsert ----
                jbInsert.setText("@\ubc1c\uc8fc\ub4f1\ub85d");
                jbInsert.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbInsert.addActionListener(e -> {try {
jbInsert(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbInsert);

                //---- jbSelect ----
                jbSelect.setText("@\ubc1c\uc8fc\uc870\ud68c");
                jbSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelect.addActionListener(e -> {try {
jbSelect(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbSelect);
                jpMain.add(button4);
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
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
