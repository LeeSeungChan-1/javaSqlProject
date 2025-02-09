/*
 * Created by JFormDesigner on Sat Feb 08 15:36:50 KST 2025
 */

package gui.material;

import DAO.Material;
import DAO.MaterialToCompany;
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
public class MaterialSelectAll {
    private String dept;
    private ArrayList<Material> materials = new ArrayList<>();
    private MaterialTbl materialTbl;
    private DefaultTableModel dtModel;
    private String materialUnit;
    private String[] unitList = {"mm", "ml", "g", "m", "l", "kg"};
    private int rowCount;
    private ArrayList<MaterialToCompany> materialToCompanys = new ArrayList<>();
    public MaterialSelectAll(String dept) throws SQLException {
        initComponents();

        this.materialTbl = new MaterialTbl();
        this.materialToCompanys = new ArrayList<>();
        this.dept = dept;

        this.jfMain.setTitle(this.dept + "전체조회");
        this.jlTop.setText(this.dept + "전체조회");
        this.dtModel = new DefaultTableModel();
        this.jtMain.setModel(dtModel);

        this.dtModel.addColumn("자재코드");
        this.dtModel.addColumn("자재명");
        this.dtModel.addColumn("자재단위");
        this.dtModel.addColumn("자재가격");
        this.dtModel.addColumn("자재회사");

    }

    private void jbSelectAll(ActionEvent e) throws SQLException {
        // TODO add your code here
        materialToCompanys = materialTbl.selectAllMaterialToCompany();
        rowCount = dtModel.getRowCount();

        for(int i = rowCount - 1; i >= 0; i--){
            dtModel.removeRow(i);
        }

        for (int i=0; i<materialToCompanys.size(); i++) {
            materialUnit = unitList[Integer.parseInt(materialToCompanys.get(i).getMaterialUnit()) - 1];
            dtModel.insertRow(
                    i,
                    new Object[]{
                            materialToCompanys.get(i).getMaterialCode(), materialToCompanys.get(i).getMaterialName(),
                            materialUnit,                                materialToCompanys.get(i).getPrice(),
                            materialToCompanys.get(i).getCompanyName()});
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
                ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
                . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
                propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
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
