/*
 * Created by JFormDesigner on Sat Feb 08 22:36:19 KST 2025
 */

package gui.materialOrder;

import DAO.Material;
import DAO.MaterialOrder;
import DAO.MaterialOrderDetail;
import DAO.User;
import connection.DBCon;
import controller.MaterialOrderDetailTbl;
import controller.MaterialOrderTbl;
import controller.MaterialTbl;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

/**
 * @author seung
 */
public class MaterialOrderInsert extends JFrame {
    private String dept;
    private DefaultTableModel dtModel;
    private MaterialTbl materialTbl;
    private ArrayList<Material> materials;
    private MaterialOrder materialOrder;
    private ArrayList<MaterialOrder> materialOrders;
    private MaterialOrderTbl materialOrderTbl;
    private Date date;
    private ArrayList<MaterialOrderDetail> materialOrderDetails;
    private MaterialOrderDetail materialOrderDetail;
    private User user;
    private MaterialOrderDetailTbl materialOrderDetailTbl;

    public MaterialOrderInsert(User user) throws SQLException {
        initComponents();

        this.materialTbl = new MaterialTbl();
        this.materials = new ArrayList<>();
        this.materialOrders = new ArrayList<>();
        this.materialOrderTbl = new MaterialOrderTbl();
        this.materialOrderDetails = new ArrayList<>();
        this.materialOrderDetailTbl = new MaterialOrderDetailTbl();

        this.user = user;

        if(this.user.getTblCompanyId() == 1){
            this.dept = "자재";
        }


        this.jfMain.setTitle(dept + "발주등록");
        this.jlTop.setText(dept + "발주등록");
        this.jbInsert.setText(dept + "발주등록");
        // 테이블 1, 3번 열을 수정이 불가능하도록 변경
        this.dtModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 1 || column == 3){
                    return false;
                }
                return true;
            }
        };

        // 테이블에 디폴트테이블모델 추가
        this.jtMain.setModel(dtModel);
        // 상단 컬럼 추가
        this.dtModel.addColumn("자재코드");
        this.dtModel.addColumn("자재명");
        this.dtModel.addColumn("발주수량");
        this.dtModel.addColumn("자재ID");
        // 기본으로 10개의 빈 행이 존재
        for(int i = 0; i < 10; i++){
            this.dtModel.addRow(new Object[]{null, null, null, null});
        }

        // 0번째 컬럼을 JComboBox로 설정
        TableColumn materialCodeCol = jtMain.getColumnModel().getColumn(0);
        JComboBox materialCodeBox = new JComboBox();

        this.materials = materialTbl.selectAllMaterial();
        for(Material material : materials) {
            materialCodeBox.addItem(material.getMaterialCode());
        }

        materialCodeCol.setCellEditor(new DefaultCellEditor(materialCodeBox));

        // 테이블의 값이 수정되었을 때 실행
        this.dtModel.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e){
                if(e.getType() == TableModelEvent.UPDATE){
                    // 수정된 테이블의 행과 열을 가져온다.
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    // 테이블의 0번째 컬림이 수정되었을 때만 실행
                    if(col == 0){
                        // 수정된 데이터 가져오기
                        Object updatedValue = dtModel.getValueAt(row, col);
                        System.out.println(updatedValue);
                        for(int i = 0; i < materials.size(); i++) {
                            if(materials.get(i).getMaterialCode().equals(updatedValue)){
                                dtModel.setValueAt(materials.get(i).getMaterialName(), row, 1);
                                dtModel.setValueAt(materials.get(i).getId(), row, 3);
                            }
                        }
                    }
                }
            }
        });
    }

    private void jbInsert(ActionEvent e) throws SQLException {
        // TODO add your code here
        // 테이블 데이터 개수 확인
        int count = 0;
        for(int i = 0; i < 10; i++){
            if(dtModel.getValueAt(i, 0) != null && dtModel.getValueAt(i, 1) != null && dtModel.getValueAt(i, 2) != null){
                count++;
            }else{
                break;
            }
        }

        if(count < 0){
            JOptionPane.showMessageDialog(null, "최소 1개 이상의 자재를 발주해야합니다.");
            return;
        }

        if(JOptionPane.showConfirmDialog(null, count + "개의 자재를 발주하시겠습니까?", "발주", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            // 오늘기준 자재발주번호 생성
            date = new Date();
            // SimpleDateFormat을 사용하여 날짜를 원하는 형식으로 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatSt = new SimpleDateFormat("yyyyMMdd");
            // 오늘 날짜를 "YYYY-MM-DD" 형식으로 변환
            String formattedDate = dateFormat.format(date);
            String formattedDateSt = dateFormatSt.format(date);

            materialOrders = materialOrderTbl.selectByDate(formattedDate);
            String orderNumber = String.format("%3s", Integer.toString(materialOrders.size() + 1)).replace(" ", "0");
            orderNumber = formattedDateSt + orderNumber;

            materialOrder = new MaterialOrder(
                    orderNumber, formattedDate, user.getIdNumber()
            );

            // 테이블 데이터 가져오기
            int index = 0;
            materialOrder = materialOrderTbl.insert(materialOrder);

            for(int i = 0; i < 10; i++){
                String orderDetailNumber;
                if(dtModel.getValueAt(i, 0) != null && dtModel.getValueAt(i, 1) != null && dtModel.getValueAt(i, 2) != null){
                    index++;
                    orderDetailNumber = String.format("%3s", Integer.toString(index)).replace(" ", "0");

                    materialOrderDetail = new MaterialOrderDetail(
                            orderDetailNumber, Integer.parseInt((String) dtModel.getValueAt(i, 2)),
                            (Long) dtModel.getValueAt(i, 3),      materialOrder.getId()
                    );

                    System.out.println(
                            materialOrderDetail.getId() + " " +     materialOrderDetail.getOrderNumberDetail() + " " +
                            materialOrderDetail.getAmount() + " " + materialOrderDetail.getTblMaterialId() + " " +
                            materialOrderDetail.getTblMaterialOrderId());

                    materialOrderDetails.add(materialOrderDetail);
                }else{
                    break;
                }
            }

            if(materialOrder != null){
                materialOrderDetailTbl.insertAll(materialOrderDetails);
                JOptionPane.showMessageDialog(null, "등록성공");
            }else{
                JOptionPane.showMessageDialog(null, "등록실패");
            }
        }

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jbInsert = new JButton();
        jpMain = new JPanel();
        jspMain = new JScrollPane();
        jtMain = new JTable();

        //======== jfMain ========
        {
            jfMain.setTitle("@\ubc1c\uc8fc\ub4f1\ub85d");
            jfMain.setVisible(true);
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,jpTop. getBorder( )) ); jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ubc1c\uc8fc\ub4f1\ub85d");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);

                //---- jbInsert ----
                jbInsert.setText("@\ubc1c\uc8fc\ub4f1\ub85d");
                jbInsert.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                jbInsert.addActionListener(e -> {try {
jbInsert(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                jpTop.add(jbInsert);
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
    private JButton jbInsert;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
