/*
 * Created by JFormDesigner on Sat Feb 08 18:17:35 KST 2025
 */

package gui.material;

import DAO.MaterialToMaterialAmount;
import controller.MaterialTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author seung
 */
public class MaterialSelectToAmount extends JFrame {
    private MaterialTbl materialTbl;
    private DefaultTableModel dtModel;
    private ArrayList<MaterialToMaterialAmount> materialToMaterialAmounts;
    private int rowCount;
    private String materialUnit;
    private String[] unitList = {"mm", "ml", "g", "m", "l", "kg"};

    public MaterialSelectToAmount(String dept) throws SQLException {
        initComponents();
        
        this.materialTbl = new MaterialTbl();
        this.materialToMaterialAmounts = new ArrayList<>();
        this.jfMain.setTitle(dept + "수량조회");
        this.jlTop.setText(dept + "수량조회");

        this.dtModel = new DefaultTableModel();
        this.jtMain.setModel(dtModel);

        this.dtModel.addColumn("재료코드");
        this.dtModel.addColumn("재료명");
        this.dtModel.addColumn("재료단위");
        this.dtModel.addColumn("재료가격");
        this.dtModel.addColumn("재료수량");
    }

    private void jbSelectAmount(ActionEvent e) throws SQLException {
        // TODO add your code here
        materialToMaterialAmounts = materialTbl.selectAllMaterialToMaterialAmount();
        rowCount = dtModel.getRowCount();
        for(int i = rowCount - 1; i >= 0; i--){
            dtModel.removeRow(i);
        }

        for (int i=0; i<materialToMaterialAmounts.size(); i++) {
            materialUnit = unitList[Integer.parseInt(materialToMaterialAmounts.get(i).getMaterialUnit()) - 1];
            dtModel.insertRow(
                    i,
                    new Object[]{
                            materialToMaterialAmounts.get(i).getMaterialCode(), materialToMaterialAmounts.get(i).getMaterialName(),
                            materialUnit,                                       materialToMaterialAmounts.get(i).getPrice(),
                            materialToMaterialAmounts.get(i).getMaterialAmount()});
        }
        
    }

    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jbSelectAmount = new JButton();
        jpMain = new JPanel();
        jspMain = new JScrollPane();
        jtMain = new JTable();

        //======== jfMain ========
        {
            jfMain.setTitle("@\uc218\ub7c9\uc870\ud68c");
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
                jlTop.setText("@\uc218\ub7c9\uc870\ud68c");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);

                //---- jbSelectAmount ----
                jbSelectAmount.setText("\uc870\ud68c");
                jbSelectAmount.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSelectAmount.addActionListener(e -> {try {
jbSelectAmount(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbSelectAmount);
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
    private JButton jbSelectAmount;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
