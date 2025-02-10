/*
 * Created by JFormDesigner on Sat Feb 08 22:05:36 KST 2025
 */

package gui;

import DAO.User;
import gui.material.Material;
import gui.materialOrder.MaterialOrder;
import gui.materialWarehousing.MaterialWarehousing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author seung
 */
public class ChooseMenu extends JFrame {
    private String dept;
    private User user;
    public ChooseMenu(User user) {
        initComponents();

        this.user = user;

        if(user.getTblCompanyId() == 1){
            this.dept = "자재";
            this.jfMain.setTitle(dept + " : " + user.getName() + " : " + user.getIdNumber());
            this.jlTop.setText(dept + "메뉴");
            this.jbMaterial.setText(dept + "관리");
            this.jbMaterialOrder.setText(dept + "발주관리");
            this.jbMaterialWarehousing.setText(dept + "입고관리");

        }

    }

    private void jbMaterial(ActionEvent e) {
        // TODO add your code here
        new Material(user);
    }

    private void jbMaterialOrder(ActionEvent e) {
        // TODO add your code here
        new MaterialOrder(user);
    }

    private void jbMaterialWarehousing(ActionEvent e) {
        // TODO add your code here
        new MaterialWarehousing(user);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpMain = new JPanel();
        jbMaterial = new JButton();
        jbMaterialOrder = new JButton();
        jbMaterialWarehousing = new JButton();
        button4 = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(500, 200));
            jfMain.setTitle("@ : \uc774\ub984 : \uc0ac\ubc88");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
                . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing
                .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
                Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
                ) ,jpTop. getBorder () ) ); jpTop. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
                public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName (
                ) ) )throw new RuntimeException( ) ;} } );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\uba54\ub274");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 30));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
            }
            jfMainContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(2, 2));

                //---- jbMaterial ----
                jbMaterial.setText("@\uad00\ub9ac");
                jbMaterial.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbMaterial.addActionListener(e -> jbMaterial(e));
                jpMain.add(jbMaterial);

                //---- jbMaterialOrder ----
                jbMaterialOrder.setText("@\ubc1c\uc8fc");
                jbMaterialOrder.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbMaterialOrder.addActionListener(e -> jbMaterialOrder(e));
                jpMain.add(jbMaterialOrder);

                //---- jbMaterialWarehousing ----
                jbMaterialWarehousing.setText("@\uc785\uace0");
                jbMaterialWarehousing.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbMaterialWarehousing.addActionListener(e -> jbMaterialWarehousing(e));
                jpMain.add(jbMaterialWarehousing);

                //---- button4 ----
                button4.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
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
    private JButton jbMaterial;
    private JButton jbMaterialOrder;
    private JButton jbMaterialWarehousing;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
