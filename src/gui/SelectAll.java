/*
 * Created by JFormDesigner on Sat Feb 08 15:36:50 KST 2025
 */

package gui;

import DAO.Material;
import controller.MaterialTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author seung
 */
public class SelectAll {
    private String dept;
    private ArrayList<Material> materials = new ArrayList<>();
    private MaterialTbl materialTbl;
    private DefaultTableModel dtModel;
    private String materialUnit;
    private String[] unitList = {"mm", "ml", "g", "m", "l", "kg"};
    private int rowCount;

    public SelectAll(String dept) throws SQLException {
        initComponents();

        this.materialTbl = new MaterialTbl();
        this.dept = dept;

        this.jfMain.setTitle(this.dept + "전체조회");
        this.jlTop.setText(this.dept + "전체조회");
        this.dtModel = new DefaultTableModel();
        this.jtMain.setModel(dtModel);
        dtModel.addColumn("ID");
        dtModel.addColumn("재료코드");
        dtModel.addColumn("재료명");
        dtModel.addColumn("재료단위");
        dtModel.addColumn("재료가격");
        dtModel.addColumn("재료회사");

    }

    private void jbSelectAll(ActionEvent e) throws SQLException {
        // TODO add your code here
        materials = materialTbl.selectAllMaterial();

        rowCount = dtModel.getRowCount();

        for(int i = rowCount - 1; i >= 0; i--){
            dtModel.removeRow(i);
            System.out.println("remove row " + i);
        }


        for (int i=0; i<materials.size(); i++) {
            materialUnit = unitList[Integer.parseInt(materials.get(i).getMaterialUnit()) - 1];
            dtModel.insertRow(
                    i,
                    new Object[]{
                            materials.get(i).getId(),           materials.get(i).getMaterialCode(),
                            materials.get(i).getMaterialName(), materialUnit,
                            materials.get(i).getPrice(),        materials.get(i).getTblCompanyId()});
        }

    }

    private void jbTop(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jbSelectAll = new JButton();
        jpMain = new JPanel();
        jspMain = new JScrollPane();
        jtMain = new JTable();

        //======== jfMain ========
        {
            jfMain.setTitle("@\uc804\uccb4\uc870\ud68c");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

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
                jlTop.setText("@\uc804\uccb4\uc870\ud68c");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);

                //---- jbSelectAll ----
                jbSelectAll.setText("\uc870\ud68c");
                jbSelectAll.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelectAll.addActionListener(e -> {try {
jbSelectAll(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbSelectAll);
            }
            jfMainContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(1, 1));

                //======== jspMain ========
                {

                    //---- jtMain ----
                    jtMain.setModel(new DefaultTableModel());
                    jspMain.setViewportView(jtMain);
                }
                jpMain.add(jspMain);
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
    private JButton jbSelectAll;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
