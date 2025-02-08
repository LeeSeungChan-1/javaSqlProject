/*
 * Created by JFormDesigner on Sat Feb 08 13:23:19 KST 2025
 */

package gui;

import DAO.ComboItem;
import DAO.Material;
import connection.DBCon;
import controller.CompanyTbl;
import controller.MaterialTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author seung
 */
public class Insert  {
    private CompanyTbl companyTbl;
    private ArrayList<ComboItem> comboItems = new ArrayList<>();
    private String[] unitList = {"mm", "ml", "g", "m", "l", "kg"};
    private MaterialTbl materialTbl;
    private Material material;


    public Insert(String dept) throws SQLException {
        initComponents();
        DBCon dbCon = new DBCon();
        materialTbl = new MaterialTbl();
        companyTbl = new CompanyTbl();

        for(String unit: unitList){
            jcbUnit.addItem(unit);
        }

        comboItems = this.companyTbl.selectCompanyAll();
        for (ComboItem item : comboItems) {
            jcbCompany.addItem(item.getKey() + ":" + item.getValue());
        }


        jfMain.setTitle(dept + "등록");
        jlTop.setText(dept + "등록");

    }

    private void jbSave(ActionEvent e) throws SQLException {
        // TODO add your code here
        String materialCode = jtfCode.getText().trim();
        String materialName = jtfName.getText().trim();
        String materialUnit = Integer.toString(jcbUnit.getSelectedIndex() + 1);
        int materialPrice = Integer.parseInt(jtfPrice.getText().trim());
        long tblCompanyId = comboItems.get(jcbCompany.getSelectedIndex()).getKey();

        System.out.println(materialCode + " " + materialName + " " + materialUnit + " " + materialPrice + " " + tblCompanyId);
        material = new Material(materialCode, materialName, materialUnit, materialPrice, tblCompanyId);
        if(this.materialTbl.insertMaterial(material)){
            JOptionPane.showMessageDialog(null, "저장완료");
        }else{
            JOptionPane.showMessageDialog(null, "저장실패");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpMain = new JPanel();
        jlCode = new JLabel();
        jtfCode = new JTextField();
        jlName = new JLabel();
        jtfName = new JTextField();
        jlUnit = new JLabel();
        jcbUnit = new JComboBox();
        jlPrice = new JLabel();
        jtfPrice = new JTextField();
        jlCompany = new JLabel();
        jcbCompany = new JComboBox();
        blank = new JLabel();
        jbSave = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(400, 300));
            jfMain.setTitle("@\ub4f1\ub85d");
            jfMain.setVisible(true);
            jfMain.setResizable(false);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing.border
                .TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),java.awt
                .Color.red),jpTop. getBorder()));jpTop. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
                propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException()
                ;}});
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ub4f1\ub85d");
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 30));
                jpTop.add(jlTop);
            }
            jfMainContentPane.add(jpTop, BorderLayout.NORTH);

            //======== jpMain ========
            {
                jpMain.setLayout(new GridLayout(6, 2));

                //---- jlCode ----
                jlCode.setText("\ucf54\ub4dc:");
                jlCode.setHorizontalAlignment(SwingConstants.CENTER);
                jlCode.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlCode);

                //---- jtfCode ----
                jtfCode.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jtfCode.setColumns(6);
                jpMain.add(jtfCode);

                //---- jlName ----
                jlName.setText("\uc774\ub984:");
                jlName.setHorizontalAlignment(SwingConstants.CENTER);
                jlName.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlName);

                //---- jtfName ----
                jtfName.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jtfName.setColumns(50);
                jpMain.add(jtfName);

                //---- jlUnit ----
                jlUnit.setText("\ub2e8\uc704:");
                jlUnit.setHorizontalAlignment(SwingConstants.CENTER);
                jlUnit.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlUnit);

                //---- jcbUnit ----
                jcbUnit.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jcbUnit);

                //---- jlPrice ----
                jlPrice.setText("\uac00\uaca9:");
                jlPrice.setHorizontalAlignment(SwingConstants.CENTER);
                jlPrice.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlPrice);

                //---- jtfPrice ----
                jtfPrice.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jtfPrice.setColumns(10);
                jpMain.add(jtfPrice);

                //---- jlCompany ----
                jlCompany.setText("\ud68c\uc0ac:");
                jlCompany.setHorizontalAlignment(SwingConstants.CENTER);
                jlCompany.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jlCompany);

                //---- jcbCompany ----
                jcbCompany.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpMain.add(jcbCompany);
                jpMain.add(blank);

                //---- jbSave ----
                jbSave.setText("\uc800\uc7a5");
                jbSave.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbSave.addActionListener(e -> {try {
jbSave(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbSave);
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
    private JLabel jlCode;
    private JTextField jtfCode;
    private JLabel jlName;
    private JTextField jtfName;
    private JLabel jlUnit;
    private JComboBox jcbUnit;
    private JLabel jlPrice;
    private JTextField jtfPrice;
    private JLabel jlCompany;
    private JComboBox jcbCompany;
    private JLabel blank;
    private JButton jbSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
