/*
 * Created by JFormDesigner on Sat Feb 08 13:22:52 KST 2025
 */

package gui;

import DAO.ComboItem;
import DAO.Material;
import connection.DBCon;
import controller.CompanyTbl;
import controller.MaterialTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author seung
 */
public class Select extends JFrame {
    private MaterialTbl materialTbl;
    private Material material;
    private CompanyTbl companyTbl;
    private String[] unitList = {"mm", "ml", "g", "m", "l", "kg"};
    private ArrayList<ComboItem> comboItems = new ArrayList<>();

    public Select(String dept) throws SQLException {
        initComponents();
        materialTbl = new MaterialTbl();
        companyTbl = new CompanyTbl();

        for(String unit: unitList){
            jcbUnit.addItem(unit);
        }

        comboItems = this.companyTbl.selectCompanyAll();
        for (ComboItem item : comboItems) {
            jcbCompany.addItem(item.getKey() + ":" + item.getValue());
        }


        jfMain.setTitle(dept + "조회, 수정, 삭제");
        jlTop.setText(dept + "조회, 수정, 삭제");
        jlSelect.setText(dept + "코드");
    }

    private void jbUpdate(ActionEvent e) throws SQLException {
        // TODO add your code here
        if(JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            material.setMaterialName(jtfName.getText().trim());
            material.setMaterialUnit(Integer.toString(jcbUnit.getSelectedIndex() + 1));
            material.setPrice(Integer.parseInt(jtfPrice.getText().trim()));
            material.setTblCompanyId(comboItems.get(jcbCompany.getSelectedIndex()).getKey());

            System.out.println(
                    material.getId() + " " + material.getMaterialCode() + " " + 
                    material.getMaterialName() + " " + material.getMaterialUnit() + " " + 
                    material.getPrice() + " " + material.getTblCompanyId());
            if(materialTbl.updateMaterial(material)){
                JOptionPane.showMessageDialog(null, "수정성공");
            }else{
                JOptionPane.showMessageDialog(null, "수정실패");
            }
        }

    }

    private void jbSelect(ActionEvent e) throws SQLException {
        // TODO add your code here
        material = materialTbl.selectMaterial(jtfSelect.getText().trim());
        if (material == null) {
            JOptionPane.showMessageDialog(null, "조회실패");
        }else{
            jtfCode.setText(material.getMaterialCode());
            jtfCode.setEditable(false);
            jtfName.setText(material.getMaterialName());
            jcbUnit.setSelectedIndex(Integer.parseInt(material.getMaterialUnit()) - 1);
            jtfPrice.setText(Integer.toString(material.getPrice()));
            for(int i=0; i<comboItems.size(); i++){
                if(comboItems.get(i).getKey().equals(material.getTblCompanyId())){
                    jcbCompany.setSelectedIndex(i);
                }
            }
        }
    }

    private void jbDelete(ActionEvent e) throws SQLException {
        // TODO add your code here
        if(JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(materialTbl.deleteMaterial(material.getId())){
                JOptionPane.showMessageDialog(null, "삭제성공");
            }else{
                JOptionPane.showMessageDialog(null, "삭제실패");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jpSelect = new JPanel();
        jlSelect = new JLabel();
        jtfSelect = new JTextField();
        jbSelect = new JButton();
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
        jbDelete = new JButton();
        jbUpdate = new JButton();

        //======== jfMain ========
        {
            jfMain.setPreferredSize(new Dimension(400, 300));
            jfMain.setTitle("@\uc870\ud68c, \uc218\uc815, \uc0ad\uc81c");
            jfMain.setVisible(true);
            jfMain.setResizable(false);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
                EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
                . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
                java. awt. Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
                { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
                throw new RuntimeException( ); }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\uc870\ud68c, \uc218\uc815, \uc0ad\uc81c");
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jpTop.add(jlTop);

                //======== jpSelect ========
                {
                    jpSelect.setLayout(new GridLayout(1, 3));

                    //---- jlSelect ----
                    jlSelect.setText("@\ucf54\ub4dc");
                    jlSelect.setHorizontalAlignment(SwingConstants.CENTER);
                    jpSelect.add(jlSelect);
                    jpSelect.add(jtfSelect);

                    //---- jbSelect ----
                    jbSelect.setText("\uc870\ud68c");
                    jbSelect.addActionListener(e -> {try {
jbSelect(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                    jpSelect.add(jbSelect);
                }
                jpTop.add(jpSelect);
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

                //---- jbDelete ----
                jbDelete.setText("\uc0ad\uc81c");
                jbDelete.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbDelete.addActionListener(e -> {try {
jbDelete(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpMain.add(jbDelete);

                //---- jbUpdate ----
                jbUpdate.setText("\uc218\uc815");
                jbUpdate.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbUpdate.addActionListener(e -> {
			jbSave(e);
			try {
jbUpdate(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}
		});
                jpMain.add(jbUpdate);
            }
            jfMainContentPane.add(jpMain, BorderLayout.CENTER);
            jfMain.pack();
            jfMain.setLocationRelativeTo(jfMain.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void jbSave(ActionEvent e) {
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - LeeSeungChan
    private JFrame jfMain;
    private JPanel jpTop;
    private JLabel jlTop;
    private JPanel jpSelect;
    private JLabel jlSelect;
    private JTextField jtfSelect;
    private JButton jbSelect;
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
    private JButton jbDelete;
    private JButton jbUpdate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
