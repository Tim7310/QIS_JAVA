/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashier;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import files.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import javax.swing.text.JTextComponent;
//import javax.swing.Icon
/*
 *
 * @author Administrator
 */
public class transOption extends javax.swing.JFrame {
    private Icon male = new ImageIcon("src/src_images/male.png");
    private Icon female = new ImageIcon("src/src_images/female.png");
    private Icon check = new ImageIcon("src/src_images/check.png");
    private Icon cross = new ImageIcon("src/src_images/cross.png");
    boolean pw,pw1;

    public transOption(String name) {
        initComponents();
        this.setLocationRelativeTo(null);
        user.setText(name);
        userCheck();
        f_password1.setEnabled(false); 
    }
        public void changePass(){
        comment.setText("");       
        String foldpass =  new String(f_oldpass.getPassword());
        String fpassword = new String(f_newpass.getPassword());
        String username =user.getText();

        if(foldpass.equals("")){
                          comment.setText("*Old Password is required");
                          f_oldpass.requestFocusInWindow();
                                    }
        else if(fpassword.equals("")){
                          comment.setText("*Password is required");
                          f_newpass.requestFocusInWindow();
                                    }
        else                        {
                          //boolean check
                                if(pw==false){f_newpass.requestFocusInWindow();                                             
                                            comment.setText("*Password must atleast contain 8 characters");}
                          else if(pw1==false){f_password1.requestFocusInWindow();                                             
                                            comment.setText("*Password mismatch");}
                          else              {
                              //------
                              //get existing data username and password
                              //and update
                              try{
                                DatabaseConnect.connect();
                                Connection con = DatabaseConnect.con;
                                PreparedStatement command = con.prepareStatement("UPDATE tbl_account SET password = '"+fpassword+"' where username like '"+username+"' and password like '"+foldpass+"'");
                                int a = command.executeUpdate();
                                if(a==1){
                                                JOptionPane.showMessageDialog(null, "New password is now set.","Success",1);
                                                change_pass.dispose();
                                                this.setEnabled(true);
                                        }
                                else{//error
                                                JOptionPane.showMessageDialog(null, "Old Password mismatch.","Failed",3);
                                        }
                                con.close();
                              }catch(Exception ex){
                                  System.out.print(ex.getMessage());
                                        }
                                    }
        }
                                }
        public void passCheck(){
    String fpassword = new String(f_newpass.getPassword());
    String fpassword1 = new String(f_password1.getPassword());
if( fpassword.equals(fpassword1)){            
                            repass_label.setIcon(check);
                            pw1=true;
                           }
else                        {
                            repass_label.setIcon(cross);
                            pw1=false;
                            }
                            }
    
    public void userCheck(){
        //------------------------
        try{
            String temp=null;
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("SELECT gender FROM TBL_ACCOUNT WHERE USERNAME LIKE '"+user.getText()+"'");
            ResultSet result = command.executeQuery();
            while(result.next()){
                        temp= result.getString(1);
                                }
        //------------------------  
        if("MALE".equals(temp)){
        logout.setIcon(male);}
        else if("FEMALE".equals(temp)){
        logout.setIcon(female);}
        }
        catch(Exception e){
                }
        }
    
    public void logout(){
                   if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out??", "Logging out",
        JOptionPane.YES_NO_OPTION,3) == JOptionPane.YES_OPTION) 
            {
                this.dispose();
                //new Login().setVisible(false);
                new signup.Login().setVisible(true);
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

        gender = new javax.swing.ButtonGroup();
        hamburger = new javax.swing.JPopupMenu();
        m_changepass = new javax.swing.JMenuItem();
        m_logout = new javax.swing.JMenuItem();
        change_pass = new javax.swing.JFrame();
        panel_changepass = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        l_old = new javax.swing.JLabel();
        l_new = new javax.swing.JLabel();
        comment = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        l_retype1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        f_oldpass = new javax.swing.JPasswordField();
        f_newpass = new javax.swing.JPasswordField();
        f_password1 = new javax.swing.JPasswordField();
        pass_label = new javax.swing.JLabel();
        repass_label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbcash = new javax.swing.JRadioButton();
        rbaccount = new javax.swing.JRadioButton();
        ok = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        hamburger_menu = new javax.swing.JButton();

        m_changepass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/change_pass.png"))); // NOI18N
        m_changepass.setText("Change Password");
        m_changepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                m_changepassMouseReleased(evt);
            }
        });
        hamburger.add(m_changepass);

        m_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/logout.png"))); // NOI18N
        m_logout.setText("Log Out");
        m_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                m_logoutMouseReleased(evt);
            }
        });
        m_logout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                m_logoutKeyReleased(evt);
            }
        });
        hamburger.add(m_logout);

        change_pass.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        change_pass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        change_pass.setMinimumSize(new java.awt.Dimension(480, 330));
        change_pass.setResizable(false);
        change_pass.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                change_passWindowClosing(evt);
            }
        });

        panel_changepass.setMinimumSize(new java.awt.Dimension(500, 300));
        panel_changepass.setPreferredSize(new java.awt.Dimension(470, 300));

        l_old.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_old.setText("Old Password: ");

        l_new.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_new.setText("New Password: ");

        comment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comment.setForeground(new java.awt.Color(255, 0, 0));
        comment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Submit");
        jButton1.setMaximumSize(new java.awt.Dimension(85, 35));
        jButton1.setMinimumSize(new java.awt.Dimension(85, 35));
        jButton1.setPreferredSize(new java.awt.Dimension(85, 35));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        l_retype1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_retype1.setText("Re-type Password: ");

        f_newpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_newpassKeyReleased(evt);
            }
        });

        f_password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_password1ActionPerformed(evt);
            }
        });
        f_password1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_password1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(f_password1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(f_newpass, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(f_oldpass, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pass_label, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(repass_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(f_oldpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(f_newpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass_label, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(f_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repass_label, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(l_new, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l_old, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(l_retype1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(l_old, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(l_new, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_retype1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/change_pass.png"))); // NOI18N
        jLabel6.setText("Change Password");

        javax.swing.GroupLayout panel_changepassLayout = new javax.swing.GroupLayout(panel_changepass);
        panel_changepass.setLayout(panel_changepassLayout);
        panel_changepassLayout.setHorizontalGroup(
            panel_changepassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_changepassLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_changepassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_changepassLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_changepassLayout.setVerticalGroup(
            panel_changepassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_changepassLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout change_passLayout = new javax.swing.GroupLayout(change_pass.getContentPane());
        change_pass.getContentPane().setLayout(change_passLayout);
        change_passLayout.setHorizontalGroup(
            change_passLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(change_passLayout.createSequentialGroup()
                .addComponent(panel_changepass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        change_passLayout.setVerticalGroup(
            change_passLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_changepass, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECT TRANSACTION");

        gender.add(rbcash);
        rbcash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbcash.setText("CASH");

        gender.add(rbaccount);
        rbaccount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbaccount.setText("ACCOUNT");

        ok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ok.setText("OK");
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });

        logout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        logout.setForeground(new java.awt.Color(0, 102, 255));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/male.png"))); // NOI18N
        logout.setBorder(null);
        logout.setContentAreaFilled(false);

        user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CASHIER: ");

        hamburger_menu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hamburger_menu.setForeground(new java.awt.Color(0, 102, 255));
        hamburger_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/hamburger.png"))); // NOI18N
        hamburger_menu.setBorder(null);
        hamburger_menu.setContentAreaFilled(false);
        hamburger_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hamburger_menuMouseReleased(evt);
            }
        });
        hamburger_menu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hamburger_menuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hamburger_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(rbcash)
                .addGap(81, 81, 81)
                .addComponent(rbaccount)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hamburger_menu)
                    .addComponent(logout)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbaccount)
                    .addComponent(rbcash))
                .addGap(18, 18, 18)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
        // TODO add your handling code here:
        if(rbcash.isSelected()) {
              Cashier cashier1 = new Cashier(rbcash.getText(),user.getText());
              this.dispose();
              cashier1.setVisible(true);
            }
        else if(rbaccount.isSelected())
            {
              Cashier cashier1 = new Cashier(rbaccount.getText(),user.getText());
              this.dispose();
              cashier1.setVisible(true);
            }
        else
        {
          JOptionPane.showMessageDialog (null, "Select Transaction" );  
        }
    }//GEN-LAST:event_okMouseClicked

    private void hamburger_menuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamburger_menuMouseReleased
        // TODO add your handling code here:
        if(evt.getButton()== MouseEvent.BUTTON1){ 
        hamburger.show(this,hamburger_menu.getX()-70,hamburger_menu.getY()+70);}
    }//GEN-LAST:event_hamburger_menuMouseReleased

    private void m_logoutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_logoutKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){logout();hamburger.setVisible(false);}
        
    }//GEN-LAST:event_m_logoutKeyReleased

    private void m_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_logoutMouseReleased
        // TODO add your handling code here:
        if(evt.getButton()== MouseEvent.BUTTON1){logout();} 
    }//GEN-LAST:event_m_logoutMouseReleased

    private void m_changepassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_changepassMouseReleased
        if(evt.getButton()== MouseEvent.BUTTON1){
            change_pass.setVisible(true);
            change_pass.setLocationRelativeTo(null);
        
            Component[] components = jPanel2.getComponents();
            //---------------------
            int n = jPanel2.getComponentCount();
            if(n>0){
                for(int i =0;i <components.length; i++){
                        if(components[i] instanceof JLabel){
                        JLabel label =(JLabel) components[i];
                        label.setIcon(null);                }
                                                       }
                    }
          //-----------------------
         
          for (Component component: components){
              if(component instanceof JTextField){
              JTextComponent specificObject = (JTextComponent) component;
              specificObject.setText("");
                                                    }
                                                }
          //-----------------------
            this.setEnabled(false);
        } 

    }//GEN-LAST:event_m_changepassMouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String[] options = new String[2];
        options[0] = new String("Proceed");
        options[1] = new String("Cancel");
            if(JOptionPane.showOptionDialog(null,"Closing this menu will automatically log you  out.   \n", "Logging out",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,options, null)==JOptionPane.YES_OPTION)
              {
                setDefaultCloseOperation(transOption.DISPOSE_ON_CLOSE);
                new signup.Login().setVisible(true);
                  }
             else{
                setDefaultCloseOperation(transOption.DO_NOTHING_ON_CLOSE);
                 }
    }//GEN-LAST:event_formWindowClosing

    private void f_newpassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_newpassKeyReleased
        // TODO: Check if atleast 8 characters
        String f_password = new String(f_newpass.getPassword());
                                if(f_password.equals("")){
                                  //pass_label.setText("");
                                    pass_label.setIcon(null);
                                  //repass_label.setText("");
                                  f_password1.setText("");
                                  repass_label.setIcon(null);
                                 }
                else{
        boolean passpattern = Pattern.compile("(.{8,})").matcher(f_password).matches();
        if(!passpattern==true){
            pass_label.setIcon(cross);
            //System.out.println("being pressed");
            pw=false;
            f_password1.setEnabled(false);
        }
        else{
            pw=true;
            pass_label.setIcon(check);
            f_password1.setEnabled(true);
            }passCheck();   
                                }
                             
    }//GEN-LAST:event_f_newpassKeyReleased

    private void f_password1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_password1KeyReleased
    String fpassword1 = new String(f_password1.getPassword());                   
        if(fpassword1.equals("")){
        repass_label.setIcon(null);
        pw1=false;
                                 }
    else{passCheck();}
    }//GEN-LAST:event_f_password1KeyReleased

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        // TODO add your handling code here:
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){changePass();}    
    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        if(evt.getButton()== MouseEvent.BUTTON1){changePass();}
    }//GEN-LAST:event_jButton1MouseReleased

    private void hamburger_menuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hamburger_menuKeyReleased
        // TODO add your handling code here:
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){hamburger.show(this,hamburger_menu.getX()-70,hamburger_menu.getY()+70);}    
    }//GEN-LAST:event_hamburger_menuKeyReleased

    private void change_passWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_change_passWindowClosing
        this.setEnabled(true);
    }//GEN-LAST:event_change_passWindowClosing

    private void f_password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_password1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel*/ 
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(transOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transOption(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame change_pass;
    private javax.swing.JLabel comment;
    private javax.swing.JPasswordField f_newpass;
    private javax.swing.JPasswordField f_oldpass;
    private javax.swing.JPasswordField f_password1;
    private javax.swing.ButtonGroup gender;
    private javax.swing.JPopupMenu hamburger;
    private javax.swing.JButton hamburger_menu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l_new;
    private javax.swing.JLabel l_old;
    private javax.swing.JLabel l_retype1;
    private javax.swing.JButton logout;
    private javax.swing.JMenuItem m_changepass;
    private javax.swing.JMenuItem m_logout;
    private javax.swing.JButton ok;
    private javax.swing.JPanel panel_changepass;
    private javax.swing.JLabel pass_label;
    private javax.swing.JRadioButton rbaccount;
    private javax.swing.JRadioButton rbcash;
    private javax.swing.JLabel repass_label;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
