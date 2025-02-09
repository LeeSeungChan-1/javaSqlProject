/*
 * Created by JFormDesigner on Sun Feb 09 20:13:41 KST 2025
 */

package gui.materialOrder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author seung
 */
public class MaterialOrderSelectAll extends JFrame {
    public MaterialOrderSelectAll() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - LeeSeungChan
        jfMain = new JFrame();
        jpTop = new JPanel();
        jlTop = new JLabel();
        jtfSelectAll = new JTextField();
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
                jpTop.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing
                .border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),
                java.awt.Color.red),jpTop. getBorder()));jpTop. addPropertyChangeListener(new java.beans.PropertyChangeListener()
                {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))
                throw new RuntimeException();}});
                jpTop.setLayout(new GridLayout(1, 1));

                //---- jlTop ----
                jlTop.setText("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
                jlTop.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.PLAIN, 16));
                jlTop.setHorizontalAlignment(SwingConstants.CENTER);
                jpTop.add(jlTop);
                jpTop.add(jtfSelectAll);

                //---- jbSelectAll ----
                jbSelectAll.setText("@\ubc1c\uc8fc\uc804\uccb4\uc870\ud68c");
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
    private JTextField jtfSelectAll;
    private JButton jbSelectAll;
    private JPanel jpMain;
    private JScrollPane jspMain;
    private JTable jtMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
