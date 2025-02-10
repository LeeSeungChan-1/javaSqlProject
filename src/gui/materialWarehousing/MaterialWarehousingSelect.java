/*
 * Created by JFormDesigner on Mon Feb 10 12:10:15 KST 2025
 */

package gui.materialWarehousing;

import DAO.*;
import DAO.MaterialWarehousing;
import controller.MaterialOrderTbl;
import controller.MaterialWarehousingDetailTbl;
import controller.MaterialWarehousingTbl;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author seung
 */
public class MaterialWarehousingSelect extends JFrame {
    private User user;
    private String dept;
    private DefaultTableModel dtModel;
    private ArrayList<MaterialOrderToMaterialOrderDetailToMaterial> materialOrderToMaterialOrderDetailToMaterials;
    private String warehousingNumber;
    private String orderNumber;
    private MaterialOrderTbl materialOrderTbl;
    private Date date;
    private MaterialWarehousing materialWarehousing;
    private MaterialWarehousingTbl materialWarehousingTbl;
    private MaterialWarehousingDetail materialWarehousingDetail;
    private ArrayList<MaterialWarehousingDetail> materialWarehousingDetails;
    private MaterialWarehousingDetailTbl materialWarehousingDetailTbl;

    public MaterialWarehousingSelect(User user) throws SQLException {
        initComponents();
        this.user = user;
        this.materialOrderToMaterialOrderDetailToMaterials = new ArrayList<>();
        this.materialOrderTbl = new MaterialOrderTbl();
        this.materialWarehousingTbl = new MaterialWarehousingTbl();
        this.materialWarehousingDetails = new ArrayList<>();
        this.materialWarehousingDetailTbl = new MaterialWarehousingDetailTbl();
        if(this.user.getTblCompanyId() == 1){
            this.dept = "자재";
        }

        this.jfMain.setTitle(dept + "입고등록");
        this.jlTop.setText(dept + "입고등록");
        this.jbInsert.setText(dept + "입고등록");
        this.jlSelect.setText(dept + "발주번호");
        this.jbSelect.setText(dept + "발주번호조회");
        // 테이블 수정이 불가능하도록 변경
        this.dtModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column != 5){
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
        this.dtModel.addColumn("입고수량");
        this.dtModel.addColumn("발주일자");
        this.dtModel.addColumn("발주자");
    }

    private void jbInsert(ActionEvent e) throws SQLException {
        // TODO add your code here
        int count = 0;
        for(int i = 0; i < dtModel.getRowCount(); i++){
            if(Integer.parseInt((String) dtModel.getValueAt(i, 5)) != 0){
                count++;
            }else{
                break;
            }
        }
        if(count == 0){
            JOptionPane.showMessageDialog(null, "최소 1개 이상의 자재를 입고해야합니다.");
            return;
        }

        if(JOptionPane.showConfirmDialog(null, count + "개의 자재를 입고하시겠습니까?", "입고", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            // 오늘기준 자재발주번호 생성
            date = new Date();
            // SimpleDateFormat을 사용하여 날짜를 원하는 형식으로 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatSt = new SimpleDateFormat("yyyyMMdd");
            // 오늘 날짜를 "YYYY-MM-DD" 형식으로 변환
            String formattedDate = dateFormat.format(date);
            String formattedDateSt = dateFormatSt.format(date);
            // 오늘 날짜 기준 발주번호 조회
            warehousingNumber = materialWarehousingTbl.selectByDateMaxWarehousingNumber(formattedDate);
            System.out.println(warehousingNumber);
            // 오늘 첫 발주 시 YYYYMMDD + 001 번으로 생성
            if(warehousingNumber.equals("0")){
                warehousingNumber = formattedDateSt + "001";
            }

            System.out.println(warehousingNumber);
            // 부모 테이블에 입력한 객체 생성
            materialWarehousing = new MaterialWarehousing(
                    warehousingNumber, formattedDate, user.getIdNumber(), materialOrderToMaterialOrderDetailToMaterials.get(0).getId()
            );

            // 테이블 데이터 가져오기
            int index = 0;
            materialWarehousing = materialWarehousingTbl.insert(materialWarehousing);

            for(int i = 0; i < dtModel.getRowCount(); i++){
                String warehousingDetailNumber;
                if(Integer.parseInt((String) dtModel.getValueAt(i, 5)) != 0){
                    index++;
                    warehousingDetailNumber = String.format("%3s", Integer.toString(index)).replace(" ", "0");

                    materialWarehousingDetail = new MaterialWarehousingDetail(
                            warehousingDetailNumber, Integer.parseInt((String) dtModel.getValueAt(i, 5)),
                            materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialId(), materialWarehousing.getId()
                    );

                    System.out.println(
                            warehousingDetailNumber +  Integer.parseInt((String) dtModel.getValueAt(i, 5)) +
                            materialOrderToMaterialOrderDetailToMaterials.get(i).getTblMaterialId() + materialWarehousing.getId()
                    );

                    materialWarehousingDetails.add(materialWarehousingDetail);
                }else{
                    break;
                }
            }

            if(materialWarehousing != null){
                materialWarehousingDetailTbl.insertAll(materialWarehousingDetails);
                JOptionPane.showMessageDialog(null, "등록성공");
            }else{
                JOptionPane.showMessageDialog(null, "등록실패");
            }
        }
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
            dtModel.addRow(new Object[]{null, null, null, null, null, null, null, null});
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumber(), i, 0);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderNumberDetail(), i, 1);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getMaterialCode(), i, 2);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getMaterialName(), i, 3);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getAmount(), i, 4);
            dtModel.setValueAt(0, i, 5);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderDate(), i, 6);
            dtModel.setValueAt(materialOrderToMaterialOrderDetailToMaterials.get(i).getOrderer(), i, 7);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        panel2 = new JPanel();
        jlSelect = new JLabel();
        jtfSelect = new JTextField();
        jbSelect = new JButton();
        panel1 = new JPanel();
        jlTop = new JLabel();
        jbInsert = new JButton();
        jpMain = new JPanel();
        jspMain = new JScrollPane();
        jtMain = new JTable();

        //======== jfMain ========
        {
            jfMain.setTitle("@\uc785\uace0\ub4f1\ub85d");
            jfMain.setVisible(true);
            jfMain.setPreferredSize(new Dimension(800, 500));
            var jfMainContentPane = jfMain.getContentPane();
            jfMainContentPane.setLayout(new BorderLayout());

            //======== jpTop ========
            {
                jpTop.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
                . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder
                . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .
                awt .Font .BOLD ,12 ), java. awt. Color. red) ,jpTop. getBorder( )) )
                ; jpTop. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
                ;
                jpTop.setLayout(new GridLayout(2, 1));

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayout(1, 3));

                    //---- jlSelect ----
                    jlSelect.setText("@\ubc1c\uc8fc\ubc88\ud638");
                    jlSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                    jlSelect.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(jlSelect);

                    //---- jtfSelect ----
                    jtfSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                    panel2.add(jtfSelect);

                    //---- jbSelect ----
                    jbSelect.setText("@\ubc1c\uc8fc\ubc88\ud638\uc870\ud68c");
                    jbSelect.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                    jbSelect.addActionListener(e -> {try {
jbSelect(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                    panel2.add(jbSelect);
                }
                jpTop.add(panel2);

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(1, 2));

                    //---- jlTop ----
                    jlTop.setText("@\uc785\uace0\ub4f1\ub85d");
                    jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                    jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                    panel1.add(jlTop);

                    //---- jbInsert ----
                    jbInsert.setText("@\uc785\uace0\ub4f1\ub85d");
                    jbInsert.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 20));
                    jbInsert.addActionListener(e -> {try {
jbInsert(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
}});
                    panel1.add(jbInsert);
                }
                jpTop.add(panel1);
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
    private JPanel panel2;
    private JLabel jlSelect;
    private JTextField jtfSelect;
    private JButton jbSelect;
    private JPanel panel1;
    private JLabel jlTop;
    private JButton jbInsert;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
