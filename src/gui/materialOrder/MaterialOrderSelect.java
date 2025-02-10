/*
 * Created by JFormDesigner on Sun Feb 09 19:15:14 KST 2025
 */

package gui.materialOrder;

import DAO.MaterialOrder;
import DAO.MaterialOrderDetail;
import DAO.MaterialOrderToMaterialOrderDetailToMaterial;
import DAO.User;
import controller.MaterialOrderDetailTbl;
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
public class MaterialOrderSelect extends JFrame {
    private User user;
    private String dept;
    private DefaultTableModel dtModel;
    private String orderNumber;
    private MaterialOrderTbl materialOrderTbl;
    private MaterialOrderToMaterialOrderDetailToMaterial materialOrderToMaterialOrderDetailToMaterial;
    private ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> materialOrderToMaterialOrderDetailToMaterials;
    private MaterialOrderDetail materialOrderDetail;
    private ArrayList<MaterialOrderDetail> materialOrderDetails;
    private MaterialOrderDetailTbl materialOrderDetailTbl;
    private MaterialOrder materialOrder;


    public MaterialOrderSelect(User user) throws SQLException {
        initComponents();
        
        this.user = user;
        this.materialOrderTbl = new MaterialOrderTbl();
        this.materialOrderToMaterialOrderDetailToMaterials = new ArrayList();
        this.materialOrderDetails = new ArrayList();
        this.materialOrderDetailTbl = new MaterialOrderDetailTbl();

        if(this.user.getTblCompanyId() == 1){
            this.dept = "자재";
        }

        this.jfMain.setTitle(dept + "발주조회");
        this.jlTop.setText(dept + "발주조회");
        this.jbSelect.setText(dept + "발주조회");
        // 테이블 수정이 불가능하도록 변경
        this.dtModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column != 4){
                    return false;    
                }
                return true;
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

    private void jbSelect(ActionEvent e) throws SQLException {
        // TODO add your code here
        materialOrderToMaterialOrderDetailToMaterials = null;
        orderNumber = jtfSelect.getText().trim();
        if(orderNumber.equals("")){
            JOptionPane.showMessageDialog(null, "발주번호를 입력하세요.");
            return;
        }

        while(0 < dtModel.getRowCount()){
            dtModel.removeRow(0);
        }

        materialOrderToMaterialOrderDetailToMaterials = materialOrderTbl.selectByOrderNumber(orderNumber);

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

    private void jbUpdate(ActionEvent e) throws SQLException {
        // TODO add your code here
        if(JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            materialOrderDetails.clear();
            for(int i = 0; i < materialOrderToMaterialOrderDetailToMaterials.size(); i++){
                // 각행에 입력받은 발주수량으로 업데이트
                materialOrderToMaterialOrderDetailToMaterials.get(i).setAmount(Integer.parseInt(String.valueOf( dtModel.getValueAt(i, 4))));
                // 객체생성
                materialOrderDetail = new MaterialOrderDetail(
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialOrderDetailId(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumberDetail(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getAmount(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialId(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getId()
                );
                materialOrderDetails.add(materialOrderDetail);
            }
            if(materialOrderDetailTbl.updateAll(materialOrderDetails)){
                JOptionPane.showMessageDialog(null, "수정성공");
            }else{
                JOptionPane.showMessageDialog(null, "수정실패");
            }
        }
        
    }

    private void jbDelete(ActionEvent e) throws SQLException {
        // TODO add your code here
        if(JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            materialOrderDetails.clear();
            for(int i = 0; i < materialOrderToMaterialOrderDetailToMaterials.size(); i++){
                // 객체생성
                materialOrderDetail = new MaterialOrderDetail(
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialOrderDetailId(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumberDetail(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getAmount(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialId(),
                        materialOrderToMaterialOrderDetailToMaterials.get(i).getId()
                );
                materialOrderDetails.add(materialOrderDetail);
            }
            // 하위 테이블 먼저 삭제
            if(materialOrderDetailTbl.deleteAll(materialOrderDetails)){
                materialOrder = new MaterialOrder(
                        materialOrderToMaterialOrderDetailToMaterials.get(0).getId(), materialOrderToMaterialOrderDetailToMaterials.get(0).getOrderNumber(),
                        materialOrderToMaterialOrderDetailToMaterials.get(0).getOrderDate(), materialOrderToMaterialOrderDetailToMaterials.get(0).getOrderer()
                );
                // 상위 테이블 삭제
                if(materialOrderTbl.deleteMaterialOrder(materialOrder)){
                    JOptionPane.showMessageDialog(null, "삭제성공");
                }else{
                    JOptionPane.showMessageDialog(null, "삭제실패");
                }
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
        jtfSelect = new JTextField();
        jbSelect = new JButton();
        jbUpdate = new JButton();
        jbDelete = new JButton();
        jpMain = new JPanel();
        jspMain = new JScrollPane();
        jtMain = new JTable();

        //======== jfMain ========
        {
            jfMain.setTitle("@\ubc1c\uc8fc\uc870\ud68c");
            jfMain.setVisible(true);
            jfMain.setPreferredSize(new Dimension(800, 500));
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
                new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
                , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
                , new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 )
                , java. awt. Color. red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ubc1c\uc8fc\uc870\ud68c");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
                jpTop.add(jtfSelect);

                //---- jbSelect ----
                jbSelect.setText("@\ubc1c\uc8fc\uc870\ud68c");
                jbSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jbSelect.addActionListener(e -> {try {
jbSelect(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbSelect);

                //---- jbUpdate ----
                jbUpdate.setText("\uc218\uc815");
                jbUpdate.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jbUpdate.addActionListener(e -> {try {
jbUpdate(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbUpdate);

                //---- jbDelete ----
                jbDelete.setText("\uc0ad\uc81c");
                jbDelete.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jbDelete.addActionListener(e -> {try {
jbDelete(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbDelete);
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
    private JTextField jtfSelect;
    private JButton jbSelect;
    private JButton jbUpdate;
    private JButton jbDelete;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
