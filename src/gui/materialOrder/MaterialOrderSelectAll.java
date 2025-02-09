/*
 * Created by JFormDesigner on Sun Feb 09 20:13:41 KST 2025
 */

package gui.materialOrder;

import DAO.MaterialOrderToMaterialOrderDetailToMaterial;
import DAO.User;
import controller.MaterialOrderTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author seung
 */
public class MaterialOrderSelectAll extends JFrame {
    private User user;
    private DefaultTableModel dtModel;
    private String dept;
    private MaterialOrderTbl materialOrderTbl;
    private MaterialOrderToMaterialOrderDetailToMaterial materialOrderToMaterialOrderDetailToMaterial;
    private ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> materialOrderToMaterialOrderDetailToMaterials;
    
    public MaterialOrderSelectAll(User user) throws SQLException {
        initComponents();
        
        this.user = user;

        this.materialOrderTbl = new MaterialOrderTbl();
        this.materialOrderToMaterialOrderDetailToMaterials = new ArrayList();

        if(this.user.getTblCompanyId() == 1){
            this.dept = "자재";
        }

        this.jfMain.setTitle(dept + "발주전체조회");
        this.jlTop.setText(dept + "발주전체조회");
        this.jbSelectAll.setText(dept + "발주전체조회");
        // 테이블 수정이 불가능하도록 변경
        this.dtModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 테이블에 디폴트테이블모델 추가
        this.jtMain.setModel(dtModel);
        // 상단 컬럼 추가
        this.dtModel.addColumn("발주번호");
        this.dtModel.addColumn("발주번호상세");
        this.dtModel.addColumn("발주자재코드");
        this.dtModel.addColumn("발주자재명");
        this.dtModel.addColumn("발주수량");
        this.dtModel.addColumn("발주일자");
        this.dtModel.addColumn("발주자");
    }

    private void jbSelectAll(ActionEvent e) throws SQLException {
        // TODO add your code here
        while(0 < dtModel.getRowCount()){
            dtModel.removeRow(0);
        }
        
        materialOrderToMaterialOrderDetailToMaterials = materialOrderTbl.selectAllToMaterialOrderDetailToMaterial();

        for(int i = 0; i < materialOrderToMaterialOrderDetailToMaterials.size(); i++){
            dtModel.addRow(new Object[]{null, null, null, null, null, null, null});
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumber(), i, 0);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumberDetail(), i, 1);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getMaterialCode(), i, 2);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getMaterialName(), i, 3);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getAmount(), i, 4);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderDate(), i, 5);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderer(), i, 6);
        }
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
            jfMain.setTitle("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);

                //---- jbSelectAll ----
                jbSelectAll.setText("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
                jbSelectAll.addActionListener(e -> jbSelectAll(e));
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
