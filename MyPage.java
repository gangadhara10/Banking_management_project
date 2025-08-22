package banking_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MyPage  extends javax.swing.JFrame 
{
	
	

	/**
	 *
	 * @author User
	 */
	
	    Connection conn;
	    ResultSet rs;
	    PreparedStatement pst;

	    /**
	     * Creates new form MyPage
	     */
	    public MyPage() throws ClassNotFoundException, SQLException {
	        super("Home");
	        initComponents();
	        conn = JavaConnect.ConnectDb();
	        Calender();
	        Account();
	        table1();
	        table2();
	        showStatementTable();
	    }

	    public void Calender() {
	        Calendar cal = new GregorianCalendar();
	        int month = cal.get(Calendar.MONTH);
	        int year = cal.get(Calendar.YEAR);
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        jTextField2.setText(+day + "/" + (month + 1) + "/" + year);

	    }

	    public void table1() {

	        String sql = "SELECT acc,name,dob,acc_type,gender,mob FROM account order by name desc limit 1000";
	        try {
	            pst = conn.prepareStatement(sql);
	            rs = pst.executeQuery();
	            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
	            while (rs.next()) {

	                m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("dob"), rs.getString("acc_type"), rs.getString("gender"), rs.getString("mobile")});
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                pst.close();
	                rs.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }

	    }

	    public void table2() {

	        String sql = "SELECT acc,name,micr_no,balance from balances order by name desc limit 1000";
	        try {
	            pst = conn.prepareStatement(sql);
	            rs = pst.executeQuery();
	            DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
	            while (rs.next()) {

	                m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("micr_no"), rs.getString("balance")});
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {

	            try {
	                pst.close();
	                rs.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }

	    }

	    public void removeRows() {
	        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
	        int n = m.getRowCount();
	        for (int i = n - 1; i >= 0; i--) {
	            m.removeRow(i);
	        }
	    }

	    public void removeRowstracgaction() {
	        DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
	        int n = m.getRowCount();
	        for (int i = n - 1; i >= 0; i--) {
	            m.removeRow(i);
	        }

	    }

	    public void removeRowStatement() {
	        DefaultTableModel m = (DefaultTableModel) jTable3.getModel();
	        int n = m.getRowCount();
	        for (int i = n - 1; i >= 0; i--) {
	            m.removeRow(i);
	        }

	    }

	    public void filterData(String name) {
	        if (conn != null) {
	            String sql = "SELECT * FROM banking_accounts.accounts where name like '%" + name + "%';";
	            PreparedStatement pst;
	            removeRows();
	            try {
	                pst = conn.prepareStatement(sql);
	                ResultSet rs = pst.executeQuery();
	                DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
	                while (rs.next()) {
	                    m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("dob"), rs.getString("acc_type"), rs.getString("gender"), rs.getString("mob")});
	                }
	            } catch (SQLException ex) {

	            } finally {
	                try {
	                    rs.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }
	    }

	    public void filterDataTranjaction(String name) {
	        if (conn != null) {
	            String sql = "SELECT * FROM banking_accounts.balances where name like '%" + name + "%';";
	            PreparedStatement pst;
	            removeRowstracgaction();
	            try {
	                pst = conn.prepareStatement(sql);
	                ResultSet rs = pst.executeQuery();
	                DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
	                while (rs.next()) {
	                    m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("micr_no"), rs.getString("balance")});
	                }
	            } catch (SQLException ex) {

	            } finally {
	                try {
	                    rs.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }
	    }

	    public void filterDataStatement(String name) {
	        if (conn != null) {
	            String sql = "SELECT * FROM banking_accounts.statement where  name like '%" + name + "%'";
	            PreparedStatement pst;
	            removeRowStatement();
	            try {
	                pst = conn.prepareStatement(sql);
	                ResultSet rs = pst.executeQuery();
	                DefaultTableModel m = (DefaultTableModel) jTable3.getModel();
	                while (rs.next()) {
	                    m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("date"), rs.getString("depo"), rs.getString("transfer"), rs.getString("transfar_acc"), rs.getString("withdraw")});
	                }
	            } catch (SQLException ex) {

	            } finally {
	                try {
	                    rs.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }
	    }

	    /**
	     * This method is called from within the constructor to initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is always
	     * regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {

	        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jTabbedPane1 = new javax.swing.JTabbedPane();
	        jPanel1 = new javax.swing.JPanel();
	        jPanel9 = new javax.swing.JPanel();
	        jLabel10 = new javax.swing.JLabel();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jTextField5 = new javax.swing.JTextField();
	        jTextField6 = new javax.swing.JTextField();
	        jTextField7 = new javax.swing.JTextField();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        jLabel16 = new javax.swing.JLabel();
	        jTextField8 = new javax.swing.JTextField();
	        jTextField9 = new javax.swing.JTextField();
	        jTextField10 = new javax.swing.JTextField();
	        jTextField11 = new javax.swing.JTextField();
	        jTextField12 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jPanel2 = new javax.swing.JPanel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel17 = new javax.swing.JLabel();
	        jLabel18 = new javax.swing.JLabel();
	        jTextField13 = new javax.swing.JTextField();
	        jTextField14 = new javax.swing.JTextField();
	        jTextField15 = new javax.swing.JTextField();
	        jTextField16 = new javax.swing.JTextField();
	        jTextField17 = new javax.swing.JTextField();
	        jTextField19 = new javax.swing.JTextField();
	        jButton4 = new javax.swing.JButton();
	        jButton5 = new javax.swing.JButton();
	        jButton6 = new javax.swing.JButton();
	        jButton18 = new javax.swing.JButton();
	        jPanel3 = new javax.swing.JPanel();
	        jLabel19 = new javax.swing.JLabel();
	        jLabel20 = new javax.swing.JLabel();
	        jLabel21 = new javax.swing.JLabel();
	        jLabel22 = new javax.swing.JLabel();
	        jLabel23 = new javax.swing.JLabel();
	        jLabel24 = new javax.swing.JLabel();
	        jTextField18 = new javax.swing.JTextField();
	        jTextField20 = new javax.swing.JTextField();
	        jTextField21 = new javax.swing.JTextField();
	        jTextField22 = new javax.swing.JTextField();
	        jTextField23 = new javax.swing.JTextField();
	        jTextField24 = new javax.swing.JTextField();
	        jButton7 = new javax.swing.JButton();
	        jComboBox1 = new javax.swing.JComboBox();
	        jTextField25 = new javax.swing.JTextField();
	        jTextField26 = new javax.swing.JTextField();
	        jButton8 = new javax.swing.JButton();
	        jButton9 = new javax.swing.JButton();
	        jButton10 = new javax.swing.JButton();
	        jButton19 = new javax.swing.JButton();
	        jPanel4 = new javax.swing.JPanel();
	        jLabel25 = new javax.swing.JLabel();
	        jLabel26 = new javax.swing.JLabel();
	        jLabel27 = new javax.swing.JLabel();
	        jLabel28 = new javax.swing.JLabel();
	        jLabel29 = new javax.swing.JLabel();
	        jLabel30 = new javax.swing.JLabel();
	        jTextField27 = new javax.swing.JTextField();
	        jButton11 = new javax.swing.JButton();
	        jTextField28 = new javax.swing.JTextField();
	        jTextField29 = new javax.swing.JTextField();
	        jTextField30 = new javax.swing.JTextField();
	        jTextField31 = new javax.swing.JTextField();
	        jTextField32 = new javax.swing.JTextField();
	        jButton12 = new javax.swing.JButton();
	        jButton13 = new javax.swing.JButton();
	        jButton20 = new javax.swing.JButton();
	        jPanel5 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jTextField33 = new javax.swing.JTextField();
	        jButton14 = new javax.swing.JButton();
	        jPanel6 = new javax.swing.JPanel();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        jTable2 = new javax.swing.JTable();
	        jTextField34 = new javax.swing.JTextField();
	        jButton15 = new javax.swing.JButton();
	        jPanel7 = new javax.swing.JPanel();
	        jLabel31 = new javax.swing.JLabel();
	        jLabel32 = new javax.swing.JLabel();
	        jLabel33 = new javax.swing.JLabel();
	        jLabel34 = new javax.swing.JLabel();
	        jLabel35 = new javax.swing.JLabel();
	        jLabel36 = new javax.swing.JLabel();
	        jLabel37 = new javax.swing.JLabel();
	        jLabel38 = new javax.swing.JLabel();
	        jTextField35 = new javax.swing.JTextField();
	        jTextField36 = new javax.swing.JTextField();
	        jTextField37 = new javax.swing.JTextField();
	        jTextField38 = new javax.swing.JTextField();
	        jTextField39 = new javax.swing.JTextField();
	        jTextField40 = new javax.swing.JTextField();
	        jTextField41 = new javax.swing.JTextField();
	        jTextField42 = new javax.swing.JTextField();
	        jButton16 = new javax.swing.JButton();
	        jButton17 = new javax.swing.JButton();
	        jPanel10 = new javax.swing.JPanel();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        jTable3 = new javax.swing.JTable();
	        jButton23 = new javax.swing.JButton();
	        jTextField47 = new javax.swing.JTextField();
	        jButton24 = new javax.swing.JButton();
	        jPanel8 = new javax.swing.JPanel();
	        jLabel39 = new javax.swing.JLabel();
	        jLabel40 = new javax.swing.JLabel();
	        jTextField43 = new javax.swing.JTextField();
	        jTextField44 = new javax.swing.JTextField();
	        jButton21 = new javax.swing.JButton();
	        jButton22 = new javax.swing.JButton();
	        jLabel41 = new javax.swing.JLabel();
	        jTextField46 = new javax.swing.JTextField();
	        jPanel11 = new javax.swing.JPanel();
	        jScrollPane4 = new javax.swing.JScrollPane();
	        jTextArea1 = new javax.swing.JTextArea();
	        jLabel43 = new javax.swing.JLabel();
	        jTextField2 = new javax.swing.JTextField();
	        jButton3 = new javax.swing.JButton();

	        jCheckBoxMenuItem1.setSelected(true);
	        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
	        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
	        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("bankl.jpg"))); // NOI18N

	        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jLabel2.setText("User");

	        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jLabel3.setText("Date");

	        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));

	        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));

	        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel10.setText("Name");

	        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel11.setText("Date of birth");

	        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel12.setText("Nationality");

	        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel13.setText("Gender");

	        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel14.setText("Address");

	        jTextField3.setEditable(false);

	        jTextField4.setEditable(false);

	        jTextField5.setEditable(false);

	        jTextField6.setEditable(false);

	        jTextField7.setEditable(false);

	        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel5.setText("Account No.");

	        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel6.setText("Account Type");

	        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel7.setText("Caste");

	        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel15.setText("Mobile");

	        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel16.setText("Security Q.");

	        jTextField8.setEditable(false);

	        jTextField9.setEditable(false);

	        jTextField10.setEditable(false);

	        jTextField11.setEditable(false);

	        jTextField12.setEditable(false);

	        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Save.png"))); // NOI18N
	        jButton1.setText("Save");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Edit.png"))); // NOI18N
	        jButton2.setText("Edit");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton2ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
	        jPanel9.setLayout(jPanel9Layout);
	        jPanel9Layout.setHorizontalGroup(
	            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel9Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel11)
	                    .addComponent(jLabel12)
	                    .addComponent(jLabel13)
	                    .addComponent(jLabel10)
	                    .addComponent(jLabel14))
	                .addGap(10, 10, 10)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(44, 44, 44)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel9Layout.createSequentialGroup()
	                        .addComponent(jLabel5)
	                        .addGap(29, 29, 29)
	                        .addComponent(jTextField8))
	                    .addGroup(jPanel9Layout.createSequentialGroup()
	                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel7)
	                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel15))
	                        .addGap(11, 11, 11)
	                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField11)
	                            .addComponent(jTextField9)
	                            .addComponent(jTextField10)))
	                    .addGroup(jPanel9Layout.createSequentialGroup()
	                        .addComponent(jLabel16)
	                        .addGap(37, 37, 37)
	                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField12)
	                            .addGroup(jPanel9Layout.createSequentialGroup()
	                                .addComponent(jButton2)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
	                                .addComponent(jButton1)))))
	                .addContainerGap())
	        );
	        jPanel9Layout.setVerticalGroup(
	            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel9Layout.createSequentialGroup()
	                .addContainerGap(41, Short.MAX_VALUE)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel5)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel10)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(27, 27, 27)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6)
	                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(30, 30, 30)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel13)
	                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7)
	                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(33, 33, 33)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel15)
	                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(35, 35, 35)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel14)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel16)
	                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton2)
	                    .addComponent(jButton1))
	                .addGap(56, 56, 56))
	        );

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 22, Short.MAX_VALUE))
	        );

	        jTabbedPane1.addTab("Profile", jPanel1);

	        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));

	        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel4.setText("User");

	        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel8.setText("Name");

	        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel9.setText("Account No.");

	        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel17.setText("Avable Balance");

	        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel18.setText("Depost Amount");

	        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("rerch.png"))); // NOI18N
	        jButton4.setText("Serch");
	        jButton4.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton4ActionPerformed(evt);
	            }
	        });

	        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("total.png"))); // NOI18N
	        jButton5.setText("Totalol");
	        jButton5.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton5ActionPerformed(evt);
	            }
	        });

	        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("total.png"))); // NOI18N
	        jButton6.setText("Depost");
	        jButton6.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton6ActionPerformed(evt);
	            }
	        });

	        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("clear.png"))); // NOI18N
	        jButton18.setText("Clear");
	        jButton18.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton18ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addGap(142, 142, 142)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel9)
	                            .addComponent(jLabel17)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel4))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                .addComponent(jButton6)
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(jPanel2Layout.createSequentialGroup()
	                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                        .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
	                                        .addComponent(jTextField15, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
	                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addComponent(jLabel18))
	                .addGap(58, 58, 58)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jButton18)
	                    .addComponent(jButton5)
	                    .addComponent(jButton4))
	                .addContainerGap(41, Short.MAX_VALUE))
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addGap(46, 46, 46)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel4)
	                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton4))
	                .addGap(23, 23, 23)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel8)
	                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(24, 24, 24)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel9)
	                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(28, 28, 28)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel17)
	                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(22, 22, 22)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel18)
	                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton5))
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton6))
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addGap(38, 38, 38)
	                        .addComponent(jButton18)))
	                .addContainerGap(62, Short.MAX_VALUE))
	        );

	        jTabbedPane1.addTab("Deposit", jPanel2);

	        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

	        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel19.setText("User");

	        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel20.setText("Name");

	        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel21.setText("Account No.");

	        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel22.setText("Available Balance");

	        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel23.setText("Transfer Amount");

	        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel24.setText("Credit Account");

	        jButton7.setText("Total");
	        jButton7.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton7ActionPerformed(evt);
	            }
	        });

	        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
	            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
	            }
	            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
	                jComboBox1PopupMenuWillBecomeInvisible(evt);
	            }
	            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
	            }
	        });

	        jButton8.setText("Show");
	        jButton8.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton8ActionPerformed(evt);
	            }
	        });

	        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("total.png"))); // NOI18N
	        jButton9.setText("Transfer");
	        jButton9.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton9ActionPerformed(evt);
	            }
	        });

	        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("rerch.png"))); // NOI18N
	        jButton10.setText("Serch");
	        jButton10.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton10ActionPerformed(evt);
	            }
	        });

	        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("clear.png"))); // NOI18N
	        jButton19.setText("Clear");
	        jButton19.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton19ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addGap(144, 144, 144)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel21)
	                    .addComponent(jLabel22)
	                    .addComponent(jLabel23)
	                    .addComponent(jLabel24)
	                    .addComponent(jLabel20)
	                    .addComponent(jLabel19))
	                .addGap(31, 31, 31)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel3Layout.createSequentialGroup()
	                        .addComponent(jButton8)
	                        .addGap(29, 29, 29)
	                        .addComponent(jButton9)
	                        .addGap(34, 34, 34)
	                        .addComponent(jButton19)
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addGroup(jPanel3Layout.createSequentialGroup()
	                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
	                                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
	                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel3Layout.createSequentialGroup()
	                                .addComponent(jTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
	                                .addGap(18, 18, 18)
	                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel3Layout.createSequentialGroup()
	                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jButton7)
	                                    .addComponent(jButton10))
	                                .addGap(0, 0, Short.MAX_VALUE)))))
	                .addContainerGap())
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addGap(41, 41, 41)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel19)
	                    .addComponent(jButton10))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel20)
	                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel21)
	                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(24, 24, 24)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel22)
	                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(23, 23, 23)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel23)
	                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton7))
	                .addGap(22, 22, 22)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel24)
	                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton9)
	                    .addComponent(jButton8)
	                    .addComponent(jButton19))
	                .addGap(45, 45, 45))
	        );

	        jTabbedPane1.addTab("Transfer", jPanel3);

	        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

	        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel25.setText("User");

	        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel26.setText("Name");

	        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel27.setText("Account No.");

	        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel28.setText("Available Balance");

	        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel29.setText("Amount");

	        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel30.setText("Total");

	        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("rerch.png"))); // NOI18N
	        jButton11.setText("Serch");
	        jButton11.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton11ActionPerformed(evt);
	            }
	        });

	        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("total.png"))); // NOI18N
	        jButton12.setText("Show");
	        jButton12.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton12ActionPerformed(evt);
	            }
	        });

	        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jButton13.setForeground(new java.awt.Color(51, 51, 51));
	        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("withdraqw.png"))); // NOI18N
	        jButton13.setText("Withdraw");
	        jButton13.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton13ActionPerformed(evt);
	            }
	        });

	        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("clear.png"))); // NOI18N
	        jButton20.setText("Clear");
	        jButton20.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton20ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
	        jPanel4.setLayout(jPanel4Layout);
	        jPanel4Layout.setHorizontalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel4Layout.createSequentialGroup()
	                .addGap(139, 139, 139)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel25)
	                    .addComponent(jLabel26)
	                    .addComponent(jLabel27)
	                    .addComponent(jLabel28)
	                    .addComponent(jLabel29)
	                    .addComponent(jLabel30))
	                .addGap(32, 32, 32)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton11))
	                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jButton13)
	                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(18, 18, 18)
	                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jButton12)
	                            .addComponent(jButton20))))
	                .addContainerGap(52, Short.MAX_VALUE))
	        );
	        jPanel4Layout.setVerticalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel4Layout.createSequentialGroup()
	                .addGap(30, 30, 30)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel25)
	                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton11))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel26)
	                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel27)
	                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel28)
	                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel29)
	                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel30)
	                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton12))
	                .addGap(28, 28, 28)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton13)
	                    .addComponent(jButton20))
	                .addContainerGap(75, Short.MAX_VALUE))
	        );

	        jTabbedPane1.addTab("Withdrawl", jPanel4);

	        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));

	        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	        jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Acc", "Name", "Dob", "Acc_Type", "Gender", "Mob"
	            }
	        ));
	        jScrollPane1.setViewportView(jTable1);

	        jTextField33.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTextField33MouseClicked(evt);
	            }
	        });

	        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("refrash.png"))); // NOI18N
	        jButton14.setText("Refresh");
	        jButton14.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton14ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
	        jPanel5.setLayout(jPanel5Layout);
	        jPanel5Layout.setHorizontalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel5Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton14)))
	                .addContainerGap())
	        );
	        jPanel5Layout.setVerticalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton14))
	                .addGap(18, 18, 18)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(31, 31, 31))
	        );

	        jTabbedPane1.addTab("Customer List", jPanel5);

	        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 2));

	        jTable2.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Acc", "Name", "MICR_NO.", "Balance"
	            }
	        ));
	        jScrollPane2.setViewportView(jTable2);

	        jTextField34.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTextField34MouseClicked(evt);
	            }
	        });

	        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
	        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("refrash.png"))); // NOI18N
	        jButton15.setText("Refresh");
	        jButton15.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton15ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
	        jPanel6.setLayout(jPanel6Layout);
	        jPanel6Layout.setHorizontalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jButton15)))
	                .addContainerGap())
	        );
	        jPanel6Layout.setVerticalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
	                .addContainerGap(32, Short.MAX_VALUE)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton15))
	                .addGap(18, 18, 18)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(34, 34, 34))
	        );

	        jTabbedPane1.addTab("Transaction", jPanel6);

	        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));

	        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel31.setText("User");

	        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel32.setText("Name");

	        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel33.setText("Account No.");

	        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel34.setText("MICR No.");

	        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel35.setText("Rate of Interest");

	        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel36.setText("Available balance");

	        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel37.setText("Mod balance");

	        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel38.setText("Nomination Registered");

	        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("rerch.png"))); // NOI18N
	        jButton16.setText("Serch");
	        jButton16.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton16ActionPerformed(evt);
	            }
	        });

	        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("clear.png"))); // NOI18N
	        jButton17.setText("Clear");
	        jButton17.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton17ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
	        jPanel7.setLayout(jPanel7Layout);
	        jPanel7Layout.setHorizontalGroup(
	            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel7Layout.createSequentialGroup()
	                .addGap(107, 107, 107)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel32)
	                    .addComponent(jLabel31)
	                    .addComponent(jLabel33)
	                    .addComponent(jLabel34)
	                    .addComponent(jLabel35)
	                    .addComponent(jLabel36)
	                    .addComponent(jLabel37)
	                    .addComponent(jLabel38))
	                .addGap(21, 21, 21)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton16))
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton17))
	                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(59, Short.MAX_VALUE))
	        );
	        jPanel7Layout.setVerticalGroup(
	            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel7Layout.createSequentialGroup()
	                .addGap(32, 32, 32)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel31)
	                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton16))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel32)
	                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel33)
	                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel34)
	                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel35)
	                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel36)
	                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel37)
	                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel7Layout.createSequentialGroup()
	                        .addGap(21, 21, 21)
	                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel38)
	                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
	                        .addComponent(jButton17)
	                        .addGap(51, 51, 51))))
	        );

	        jTabbedPane1.addTab("View Balance", jPanel7);

	        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));

	        jTable3.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Acc_No.", "Nmae", "Date", "Deposit", "Transfer", "Came From", "Withdraw"
	            }
	        ));
	        jScrollPane3.setViewportView(jTable3);

	        jButton23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("refrash.png"))); // NOI18N
	        jButton23.setText("Refresh");
	        jButton23.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton23ActionPerformed(evt);
	            }
	        });

	        jButton24.setText("Serch");
	        jButton24.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton24ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
	        jPanel10.setLayout(jPanel10Layout);
	        jPanel10Layout.setHorizontalGroup(
	            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel10Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addGroup(jPanel10Layout.createSequentialGroup()
	                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
	                            .addContainerGap())
	                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                            .addGap(0, 0, Short.MAX_VALUE)
	                            .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                            .addComponent(jButton24)
	                            .addGap(18, 18, 18)
	                            .addComponent(jButton23)
	                            .addGap(25, 25, 25))))
	            );
	            jPanel10Layout.setVerticalGroup(
	                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
	                    .addContainerGap(51, Short.MAX_VALUE)
	                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jButton23)
	                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jButton24))
	                    .addGap(31, 31, 31)
	                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGap(49, 49, 49))
	            );

	            jTabbedPane1.addTab("Statement", jPanel10);

	            jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));

	            jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	            jLabel39.setText("Your Old Pin");

	            jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	            jLabel40.setText("Enter New Pin");

	            jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	            jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("refrash.png"))); // NOI18N
	            jButton21.setText("Change");
	            jButton21.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent evt) {
	                    jButton21ActionPerformed(evt);
	                }
	            });

	            jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	            jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("clear.png"))); // NOI18N
	            jButton22.setText("Clear");
	            jButton22.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent evt) {
	                    jButton22ActionPerformed(evt);
	                }
	            });

	            jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	            jLabel41.setText("User Name");

	            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
	            jPanel8.setLayout(jPanel8Layout);
	            jPanel8Layout.setHorizontalGroup(
	                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel8Layout.createSequentialGroup()
	                    .addGap(194, 194, 194)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel39)
	                        .addComponent(jLabel40)
	                        .addComponent(jLabel41))
	                    .addGap(18, 18, 18)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGroup(jPanel8Layout.createSequentialGroup()
	                            .addComponent(jButton21)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                            .addComponent(jButton22))
	                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addComponent(jTextField46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
	                            .addComponent(jTextField43, javax.swing.GroupLayout.Alignment.LEADING)))
	                    .addContainerGap(124, Short.MAX_VALUE))
	            );
	            jPanel8Layout.setVerticalGroup(
	                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel8Layout.createSequentialGroup()
	                    .addGap(83, 83, 83)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel41)
	                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGap(18, 18, 18)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel39))
	                    .addGap(18, 18, 18)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel40)
	                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGap(33, 33, 33)
	                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jButton21)
	                        .addComponent(jButton22))
	                    .addContainerGap(143, Short.MAX_VALUE))
	            );

	            jTabbedPane1.addTab("Change Pin", jPanel8);

	            jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
	            jPanel11.setEnabled(false);

	            jTextArea1.setEditable(false);
	            jTextArea1.setColumns(20);
	            jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
	            jTextArea1.setRows(5);
	            jTextArea1.setText("\n\n1.Call 01765-515354 or\nvisit www.dbblbanking.com\nFor more info.\n");
	            jScrollPane4.setViewportView(jTextArea1);

	            jLabel43.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
	            jLabel43.setForeground(new java.awt.Color(255, 0, 0));
	            jLabel43.setText("Dutch-Bangla Bank");

	            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
	            jPanel11.setLayout(jPanel11Layout);
	            jPanel11Layout.setHorizontalGroup(
	                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel11Layout.createSequentialGroup()
	                    .addGap(107, 107, 107)
	                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addContainerGap(117, Short.MAX_VALUE))
	            );
	            jPanel11Layout.setVerticalGroup(
	                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel11Layout.createSequentialGroup()
	                    .addGap(39, 39, 39)
	                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGap(40, 40, 40)
	                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addContainerGap(88, Short.MAX_VALUE))
	            );

	            jTabbedPane1.addTab("HelpLine", jPanel11);

	            jTextField2.setEditable(false);

	            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("eye.png"))); // NOI18N
	            jButton3.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent evt) {
	                    jButton3ActionPerformed(evt);
	                }
	            });

	            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	            getContentPane().setLayout(layout);
	            layout.setHorizontalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jLabel1)
	                    .addGap(73, 73, 73)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                        .addComponent(jLabel2)
	                        .addComponent(jLabel3))
	                    .addGap(28, 28, 28)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addGroup(layout.createSequentialGroup()
	                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addComponent(jTextField2))
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addContainerGap())
	            );
	            layout.setVerticalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addGroup(layout.createSequentialGroup()
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(jLabel2)
	                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                            .addGap(17, 17, 17)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addComponent(jLabel3)
	                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            );

	            setSize(new java.awt.Dimension(681, 564));
	            setLocationRelativeTo(null);
	        }// </editor-fold>//GEN-END:initComponents

	        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
	            // TODO add your handling code here:// Edit Button
	            jTextField5.setEditable(true);
	            jTextField6.setEditable(true);
	            jTextField7.setEditable(true);
	            jTextField10.setEditable(true);
	            jTextField11.setEditable(true);
	            jTextField12.setEditable(true);

	        }//GEN-LAST:event_jButton2ActionPerformed

	        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
	            String sql = "select * from accounts where name =?";
	            try {
	                // TODO add your handling code here:            
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, jTextField1.getText());
	                rs = pst.executeQuery();
	                if (rs.next()) {
	                    String add1 = rs.getString("name");
	                    jTextField3.setText(add1);

	                    String add2 = rs.getString("acc");
	                    jTextField8.setText(add2);

	                    String add3 = rs.getString("dob");
	                    jTextField4.setText(add3);

	                    String add4 = rs.getString("acc_type");
	                    jTextField9.setText(add4);

	                    String add5 = rs.getString("nationality");
	                    jTextField5.setText(add5);

	                    String add6 = rs.getString("caste");
	                    jTextField10.setText(add6);

	                    String add7 = rs.getString("gender");
	                    jTextField6.setText(add7);

	                    String add8 = rs.getString("mob");
	                    jTextField11.setText(add8);

	                    String add9 = rs.getString("address");
	                    jTextField7.setText(add9);

	                    String add10 = rs.getString("sec_q");
	                    jTextField12.setText(add10);
	                    pst.close();
	                    rs.close();

	                } else {

	                    JOptionPane.showMessageDialog(null, "Enter Correct Name");
	                }
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    pst.close();
	                    rs.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }
	        }//GEN-LAST:event_jButton3ActionPerformed

	        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	            try {
	                // TODO add your handling code here:
	                String value7 = jTextField1.getText();
	                String value1 = jTextField5.getText();
	                String value2 = jTextField6.getText();
	                String value3 = jTextField7.getText();
	                String value4 = jTextField10.getText();
	                String value5 = jTextField11.getText();
	                String value6 = jTextField12.getText();
	                String sql = "update accounts set nationality='" + value1 + "',gender='" + value2 + "',address='" + value3 + "',caste='" + value4 + "',mob='" + value5 + "',sec_q='" + value6 + "'where name ='" + value7 + "'";

	                pst = conn.prepareStatement(sql);
	                pst.execute();
	                JOptionPane.showMessageDialog(null, "Profile Updated");
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }//GEN-LAST:event_jButton1ActionPerformed

	        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
	            try {
	                // TODO add your handling code here: deposit serch button
	                String sql = "select * from balances where name =?";
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, jTextField13.getText());
	                rs = pst.executeQuery();
	                if (rs.next()) {
	                    String add1 = rs.getString("name");
	                    jTextField14.setText(add1);

	                    String add2 = rs.getString("acc");
	                    jTextField15.setText(add2);

	                    String add3 = rs.getString("balance");
	                    jTextField16.setText(add3);
	                    rs.close();
	                    pst.close();

	                } else {
	                    JOptionPane.showMessageDialog(null, "Enter Currect Name");
	                }
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {

	                try {
	                    rs.close();
	                    pst.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }//GEN-LAST:event_jButton4ActionPerformed

	        private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
	            // TODO add your handling code here:deposit total button
	            try {
	                String a1 = jTextField16.getText();
	                String a2 = jTextField17.getText();
	                int sum = Integer.parseInt(a1) + Integer.parseInt(a2);
	                String sum1 = String.valueOf(sum);
	                jTextField19.setText(sum1);

	            } catch (Exception e) {
	            }

	        }//GEN-LAST:event_jButton5ActionPerformed

	        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
	            try {
	                // TODO add your handling code here:
	                String a1 = jTextField16.getText();
	                String a2 = jTextField17.getText();
	                int sum = Integer.parseInt(a1) + Integer.parseInt(a2);
	                String value1 = jTextField13.getText();
	                String value2 = String.valueOf(sum);
	                String sql = "update balances set balance ='" + value2 + "' where name='" + value1 + "'";
	                pst = conn.prepareStatement(sql);
	                pst.execute();
	                JOptionPane.showMessageDialog(null, "Sucessfully Deposited");
	                stamentDeposit();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }//GEN-LAST:event_jButton6ActionPerformed

	        private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
	            // TODO add your handling code here:
	            try {
	                String sql = "select * from balances where name =?";
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, jTextField18.getText());
	                rs = pst.executeQuery();
	                if (rs.next()) {
	                    String add1 = rs.getString("name");
	                    jTextField20.setText(add1);

	                    String add2 = rs.getString("acc");
	                    jTextField21.setText(add2);

	                    String add3 = rs.getString("balance");
	                    jTextField22.setText(add3);
	                    rs.close();
	                    pst.close();

	                } else {
	                    JOptionPane.showMessageDialog(null, "Enter Currect Name");
	                }
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {

	                try {
	                    rs.close();
	                    pst.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }//GEN-LAST:event_jButton10ActionPerformed

	        private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
	            // TODO add your handling code here:tranasfer button tottal
	            try {
	                String a1 = jTextField22.getText();
	                String a2 = jTextField23.getText();
	                int sum = Integer.parseInt(a1) - Integer.parseInt(a2);
	                String sum1 = String.valueOf(sum);
	                jTextField24.setText(sum1);

	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }

	        }//GEN-LAST:event_jButton7ActionPerformed

	        private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
	            // TODO add your handling code here: show button]\
	            try {
	                String a1 = jTextField23.getText();
	                String a2 = jTextField25.getText();
	                int sum = Integer.parseInt(a1) + Integer.parseInt(a2);
	                String sum1 = String.valueOf(sum);
	                jTextField26.setText(sum1);

	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }
	        }//GEN-LAST:event_jButton8ActionPerformed
	        public void Account() {

	            try {
	                String sql = "select * from balances";
	                pst = conn.prepareStatement(sql);
	                rs = pst.executeQuery();
	                while (rs.next()) {
	                    String account = rs.getString("acc");
	                    jComboBox1.addItem(account);

	                }

	            } catch (Exception e) {

	                JOptionPane.showMessageDialog(null, e);
	            }

	        }
	        private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
	            // TODO add your handling code here://combo box
	            try {
	                String a1 = (String) jComboBox1.getSelectedItem();
	                String sql = "select * from balances where acc=?";
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, a1);
	                rs = pst.executeQuery();
	                if (rs.next()) {

	                    String add = rs.getString("balance");
	                    jTextField25.setText(add);

	                }

	            } catch (Exception e) {

	                JOptionPane.showMessageDialog(null, e);
	            }
	        }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible
	        public void Transferc() {
	            try {
	                String value1 = (String) jComboBox1.getSelectedItem();
	                String value2 = jTextField26.getText();
	                String sql = "update balances set balance ='" + value2 + "' where acc='" + value1 + "'";
	                pst = conn.prepareStatement(sql);
	                pst.execute();
	                JOptionPane.showMessageDialog(null, "Succesfully Transfered");
	            } catch (Exception e) {

	                JOptionPane.showMessageDialog(null, e);

	            }

	        }

	        public void TransferD() {
	            try {
	                String value1 = jTextField20.getText();
	                String value2 = jTextField24.getText();
	                String sql = "update balances set balance='" + value2 + "'where name='" + value1 + "'";
	                pst = conn.prepareStatement(sql);
	                pst.execute();

	            } catch (Exception e) {

	                JOptionPane.showMessageDialog(null, e);

	            }

	        }


	        private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
	            // TODO add your handling code here:
	            TransferD();
	            Transferc();
	            stamentTransfer();


	        }//GEN-LAST:event_jButton9ActionPerformed

	        private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
	            // TODO add your handling code here: widwow
	            try {
	                String sql = "select * from balances where name =?";
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, jTextField27.getText());
	                rs = pst.executeQuery();
	                if (rs.next()) {
	                    String add1 = rs.getString("name");
	                    jTextField28.setText(add1);

	                    String add2 = rs.getString("acc");
	                    jTextField29.setText(add2);

	                    String add3 = rs.getString("balance");
	                    jTextField30.setText(add3);
	                    rs.close();
	                    pst.close();

	                } else {
	                    JOptionPane.showMessageDialog(null, "Enter Currect Name");
	                }
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {

	                try {
	                    rs.close();
	                    pst.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                }

	            }

	        }//GEN-LAST:event_jButton11ActionPerformed

	        private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
	            // TODO add your handling code here: w show
	            try {
	                String a1 = jTextField30.getText();
	                String a2 = jTextField31.getText();
	                int sum = Integer.parseInt(a1) - Integer.parseInt(a2);
	                String sum1 = String.valueOf(sum);
	                jTextField32.setText(sum1);

	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }

	        }//GEN-LAST:event_jButton12ActionPerformed

	        private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
	            // TODO add your handling code here: widrow
	            try {

	                String a1 = jTextField27.getText();
	                String a2 = jTextField30.getText();
	                String a3 = jTextField31.getText();
	                int sum = Integer.parseInt(a2) - Integer.parseInt(a3);
	                String sum1 = String.valueOf(sum);
	                String sql = "update balances set balance='" + sum1 + "' where name='" + a1 + "'";
	                pst = conn.prepareStatement(sql);
	                pst.execute();
	                JOptionPane.showMessageDialog(null, "Withdraw Successful");
	                stamentWithdrow();

	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }
	        }//GEN-LAST:event_jButton13ActionPerformed

	        private void jTextField33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField33MouseClicked
	            // TODO add your handling code here:serch
	            String key = jTextField33.getText();
	            this.filterData(key);
	        }//GEN-LAST:event_jTextField33MouseClicked

	        private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
	            // TODO add your handling code here:refrash
	            removeRows();
	            table1();

	        }//GEN-LAST:event_jButton14ActionPerformed

	        private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
	            // TODO add your handling code here:
	            removeRowstracgaction();
	            table2();
	        }//GEN-LAST:event_jButton15ActionPerformed

	        private void jTextField34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField34MouseClicked
	            // TODO add your handling code here: tranjaction serce         
	            String key = jTextField34.getText();
	            filterDataTranjaction(key);

	        }//GEN-LAST:event_jTextField34MouseClicked

	        private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
	            try {
	                // TODO add your handling code here:
	                String sql = "select * from balances where name ='" + jTextField35.getText() + "'";
	                pst = conn.prepareStatement(sql);
	                rs = pst.executeQuery();
	                if (rs.next()) {
	                    String add1 = rs.getString("name");
	                    jTextField36.setText(add1);
	                    String add2 = rs.getString("acc");
	                    jTextField37.setText(add2);
	                    String add3 = rs.getString("micr_no");
	                    jTextField38.setText(add3);
	                    String add4 = rs.getString("balance");
	                    jTextField40.setText(add4);
	                    jTextField39.setText("4.0%");
	                    jTextField41.setText("TK 0.00");
	                    jTextField42.setText("No");

	                }
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }//GEN-LAST:event_jButton16ActionPerformed

	        private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
	            // TODO add your handling code here: balance view clew button 
	            jTextField35.setText("");
	            jTextField36.setText("");
	            jTextField37.setText("");
	            jTextField38.setText("");
	            jTextField39.setText("");
	            jTextField40.setText("");
	            jTextField41.setText("");
	            jTextField42.setText("");

	        }//GEN-LAST:event_jButton17ActionPerformed

	        private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
	            // TODO add your handling code here: deposit clear button 
	            dipositClear();

	        }//GEN-LAST:event_jButton18ActionPerformed

	        private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
	            // TODO add your handling code here: trnasfer clear
	            trnasferClear();
	        }//GEN-LAST:event_jButton19ActionPerformed

	        private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
	            // TODO add your handling code here: clear widrow
	            widrowClear();
	        }//GEN-LAST:event_jButton20ActionPerformed

	        private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
	            // TODO add your handling code here:change password clear button
	            jTextField43.setText("");
	            jTextField44.setText("");
	            jTextField46.setText("");
	        }//GEN-LAST:event_jButton22ActionPerformed

	        private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
	            // TODO add your handling code here: password change button

	            String value1 = jTextField43.getText();
	            String value2 = jTextField44.getText();
	            String value3 = jTextField46.getText();

	            String sql = "update accounts set pin='" + value2 + "' where pin='" + value1 + "' and name ='" + value3 + "'";
	            try {
	                pst = conn.prepareStatement(sql);
	                pst.execute();
	                JOptionPane.showMessageDialog(null, "pin Succesfully change");
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }


	        }//GEN-LAST:event_jButton21ActionPerformed

	        private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
	            try {
	                // TODO add your handling code here:
	                removeRowStatement();
	                showStatementTable();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }//GEN-LAST:event_jButton23ActionPerformed

	        private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
	            // TODO add your handling code here:        
	            String name = jTextField47.getText();
	            filterDataStatement(name);
	        }//GEN-LAST:event_jButton24ActionPerformed

	        public void dipositClear() {

	            jTextField13.setText("");
	            jTextField14.setText("");
	            jTextField15.setText("");
	            jTextField16.setText("");
	            jTextField17.setText("");
	            jTextField19.setText("");

	        }

	        public void trnasferClear() {

	            jTextField18.setText("");
	            jTextField19.setText("");
	            jTextField20.setText("");
	            jTextField21.setText("");
	            jTextField22.setText("");
	            jTextField23.setText("");
	            jTextField24.setText("");
	            jTextField25.setText("");
	            jTextField26.setText("");

	        }

	        public void widrowClear() {

	            jTextField27.setText("");
	            jTextField28.setText("");
	            jTextField29.setText("");
	            jTextField30.setText("");
	            jTextField31.setText("");
	            jTextField32.setText("");

	        }

	        public void stamentDeposit() {
	            String sql2 = "insert into statement (name,acc,date,depo,transfer,transfar_acc,withdraw) values (?,?,?,?,?,?,?)";
	            try {
	                pst = conn.prepareStatement(sql2);
	                pst.setString(1, jTextField14.getText());
	                pst.setString(2, jTextField15.getText());
	                pst.setString(3, jTextField2.getText().toString());
	                pst.setString(4, jTextField17.getText());
	                pst.setString(5, "-");
	                pst.setString(6, "-");
	                pst.setString(7, "-");
	                pst.execute();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }

	        public void stamentTransfer() {
	            String sql2 = "insert into statement (name,acc,date,depo,transfer,transfar_acc,withdraw) values (?,?,?,?,?,?,?)";
	            try {
	                pst = conn.prepareStatement(sql2);
	                pst.setString(1, jTextField20.getText());
	                pst.setString(2, jTextField21.getText());
	                pst.setString(3, jTextField2.getText().toString());
	                pst.setString(4, "-");
	                pst.setString(5, jTextField23.getText());
	                pst.setString(6, (String) jComboBox1.getSelectedItem());
	                pst.setString(7, "-");
	                pst.execute();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }

	        public void stamentWithdrow() {
	            String sql2 = "insert into statement (name,acc,date,depo,transfer,transfar_acc,withdraw) values (?,?,?,?,?,?,?)";
	            try {
	                pst = conn.prepareStatement(sql2);
	                pst.setString(1, jTextField28.getText());
	                pst.setString(2, jTextField29.getText());
	                pst.setString(3, jTextField2.getText().toString());
	                pst.setString(4, "-");
	                pst.setString(5, "-");
	                pst.setString(6, "-");
	                pst.setString(7, jTextField31.getText());
	                pst.execute();
	            } catch (SQLException ex) {
	                Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }

	        public void showStatementTable() throws SQLException {
	            String sql = "select * from statement";
	            pst = conn.prepareStatement(sql);
	            rs = pst.executeQuery();
	            DefaultTableModel m = (DefaultTableModel) jTable3.getModel();
	            while (rs.next()) {
	                m.addRow(new Object[]{rs.getString("acc"), rs.getString("name"), rs.getString("date"), rs.getString("depo"), rs.getString("transfer"), rs.getString("transfar_acc"), rs.getString("withdraw")});
	            }

	        }

	        /**
	         * @param args the command line arguments
	         */
	        public static void main(String args[]) {
	            /* Set the Nimbus look and feel */
	            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	             */
	            try {
	                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                    if ("Nimbus".equals(info.getName())) {
	                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                        break;
	                    }
	                }
	            } catch (ClassNotFoundException ex) {
	                java.util.logging.Logger.getLogger(MyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (InstantiationException ex) {
	                java.util.logging.Logger.getLogger(MyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (IllegalAccessException ex) {
	                java.util.logging.Logger.getLogger(MyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	                java.util.logging.Logger.getLogger(MyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	            }
	            //</editor-fold>

	            /* Create and display the form */
	            java.awt.EventQueue.invokeLater(new Runnable() {
	                public void run() {
	                    try {
	                        new MyPage().setVisible(true);
	                    } catch (ClassNotFoundException ex) {
	                        Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                    } catch (SQLException ex) {
	                        Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
	                    }
	                }
	            });
	        }

	        // Variables declaration - do not modify//GEN-BEGIN:variables
	        private javax.swing.JButton jButton1;
	        private javax.swing.JButton jButton10;
	        private javax.swing.JButton jButton11;
	        private javax.swing.JButton jButton12;
	        private javax.swing.JButton jButton13;
	        private javax.swing.JButton jButton14;
	        private javax.swing.JButton jButton15;
	        private javax.swing.JButton jButton16;
	        private javax.swing.JButton jButton17;
	        private javax.swing.JButton jButton18;
	        private javax.swing.JButton jButton19;
	        private javax.swing.JButton jButton2;
	        private javax.swing.JButton jButton20;
	        private javax.swing.JButton jButton21;
	        private javax.swing.JButton jButton22;
	        private javax.swing.JButton jButton23;
	        private javax.swing.JButton jButton24;
	        private javax.swing.JButton jButton3;
	        private javax.swing.JButton jButton4;
	        private javax.swing.JButton jButton5;
	        private javax.swing.JButton jButton6;
	        private javax.swing.JButton jButton7;
	        private javax.swing.JButton jButton8;
	        private javax.swing.JButton jButton9;
	        private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
	        private javax.swing.JComboBox jComboBox1;
	        private javax.swing.JLabel jLabel1;
	        private javax.swing.JLabel jLabel10;
	        private javax.swing.JLabel jLabel11;
	        private javax.swing.JLabel jLabel12;
	        private javax.swing.JLabel jLabel13;
	        private javax.swing.JLabel jLabel14;
	        private javax.swing.JLabel jLabel15;
	        private javax.swing.JLabel jLabel16;
	        private javax.swing.JLabel jLabel17;
	        private javax.swing.JLabel jLabel18;
	        private javax.swing.JLabel jLabel19;
	        private javax.swing.JLabel jLabel2;
	        private javax.swing.JLabel jLabel20;
	        private javax.swing.JLabel jLabel21;
	        private javax.swing.JLabel jLabel22;
	        private javax.swing.JLabel jLabel23;
	        private javax.swing.JLabel jLabel24;
	        private javax.swing.JLabel jLabel25;
	        private javax.swing.JLabel jLabel26;
	        private javax.swing.JLabel jLabel27;
	        private javax.swing.JLabel jLabel28;
	        private javax.swing.JLabel jLabel29;
	        private javax.swing.JLabel jLabel3;
	        private javax.swing.JLabel jLabel30;
	        private javax.swing.JLabel jLabel31;
	        private javax.swing.JLabel jLabel32;
	        private javax.swing.JLabel jLabel33;
	        private javax.swing.JLabel jLabel34;
	        private javax.swing.JLabel jLabel35;
	        private javax.swing.JLabel jLabel36;
	        private javax.swing.JLabel jLabel37;
	        private javax.swing.JLabel jLabel38;
	        private javax.swing.JLabel jLabel39;
	        private javax.swing.JLabel jLabel4;
	        private javax.swing.JLabel jLabel40;
	        private javax.swing.JLabel jLabel41;
	        private javax.swing.JLabel jLabel43;
	        private javax.swing.JLabel jLabel5;
	        private javax.swing.JLabel jLabel6;
	        private javax.swing.JLabel jLabel7;
	        private javax.swing.JLabel jLabel8;
	        private javax.swing.JLabel jLabel9;
	        private javax.swing.JPanel jPanel1;
	        private javax.swing.JPanel jPanel10;
	        private javax.swing.JPanel jPanel11;
	        private javax.swing.JPanel jPanel2;
	        private javax.swing.JPanel jPanel3;
	        private javax.swing.JPanel jPanel4;
	        private javax.swing.JPanel jPanel5;
	        private javax.swing.JPanel jPanel6;
	        private javax.swing.JPanel jPanel7;
	        private javax.swing.JPanel jPanel8;
	        private javax.swing.JPanel jPanel9;
	        private javax.swing.JScrollPane jScrollPane1;
	        private javax.swing.JScrollPane jScrollPane2;
	        private javax.swing.JScrollPane jScrollPane3;
	        private javax.swing.JScrollPane jScrollPane4;
	        private javax.swing.JTabbedPane jTabbedPane1;
	        private javax.swing.JTable jTable1;
	        private javax.swing.JTable jTable2;
	        private javax.swing.JTable jTable3;
	        private javax.swing.JTextArea jTextArea1;
	        private javax.swing.JTextField jTextField1;
	        private javax.swing.JTextField jTextField10;
	        private javax.swing.JTextField jTextField11;
	        private javax.swing.JTextField jTextField12;
	        private javax.swing.JTextField jTextField13;
	        private javax.swing.JTextField jTextField14;
	        private javax.swing.JTextField jTextField15;
	        private javax.swing.JTextField jTextField16;
	        private javax.swing.JTextField jTextField17;
	        private javax.swing.JTextField jTextField18;
	        private javax.swing.JTextField jTextField19;
	        private javax.swing.JTextField jTextField2;
	        private javax.swing.JTextField jTextField20;
	        private javax.swing.JTextField jTextField21;
	        private javax.swing.JTextField jTextField22;
	        private javax.swing.JTextField jTextField23;
	        private javax.swing.JTextField jTextField24;
	        private javax.swing.JTextField jTextField25;
	        private javax.swing.JTextField jTextField26;
	        private javax.swing.JTextField jTextField27;
	        private javax.swing.JTextField jTextField28;
	        private javax.swing.JTextField jTextField29;
	        private javax.swing.JTextField jTextField3;
	        private javax.swing.JTextField jTextField30;
	        private javax.swing.JTextField jTextField31;
	        private javax.swing.JTextField jTextField32;
	        private javax.swing.JTextField jTextField33;
	        private javax.swing.JTextField jTextField34;
	        private javax.swing.JTextField jTextField35;
	        private javax.swing.JTextField jTextField36;
	        private javax.swing.JTextField jTextField37;
	        private javax.swing.JTextField jTextField38;
	        private javax.swing.JTextField jTextField39;
	        private javax.swing.JTextField jTextField4;
	        private javax.swing.JTextField jTextField40;
	        private javax.swing.JTextField jTextField41;
	        private javax.swing.JTextField jTextField42;
	        private javax.swing.JTextField jTextField43;
	        private javax.swing.JTextField jTextField44;
	        private javax.swing.JTextField jTextField46;
	        private javax.swing.JTextField jTextField47;
	        private javax.swing.JTextField jTextField5;
	        private javax.swing.JTextField jTextField6;
	        private javax.swing.JTextField jTextField7;
	        private javax.swing.JTextField jTextField8;
	        private javax.swing.JTextField jTextField9;
	        // End of variables declaration//GEN-END:variables
	    }  


