//Toolkit.getDefaultToolkit().getImage(Signup.class.getResource("/src_images/QPDLogo.png"))



package signup;

import files.DatabaseConnect;
import java.awt.Color;
import java.io.*;
import java.util.regex.*;
//import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Signup extends javax.swing.JFrame {

    boolean ln,fn,bd,un,em,pw,pw1;
    private Icon check = new ImageIcon("src/src_images/check.png");
    private Icon cross = new ImageIcon("src/src_images/cross.png");
    
    
    public Signup() {
        initComponents();
        f_password1.setEnabled(false); 
        //jDialog1.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        textArea1 = new java.awt.TextArea();
        r_gender = new javax.swing.ButtonGroup();
        signup_label = new javax.swing.JLabel();
        c_position = new javax.swing.JComboBox<>();
        view_license_text = new javax.swing.JLabel();
        signup_button = new javax.swing.JButton();
        position_label = new javax.swing.JLabel();
        license_checkbox = new javax.swing.JCheckBox();
        comment = new javax.swing.JLabel();
        Separator1 = new javax.swing.JSeparator();
        Separator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        f_fname = new javax.swing.JTextField();
        f_lname = new javax.swing.JTextField();
        lname_label = new javax.swing.JLabel();
        password_label2 = new javax.swing.JLabel();
        fnamel_label = new javax.swing.JLabel();
        mname_label = new javax.swing.JLabel();
        f_birthdate = new javax.swing.JTextField();
        f_mname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bday_label = new javax.swing.JLabel();
        password_label3 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        f_password = new javax.swing.JPasswordField();
        f_password1 = new javax.swing.JPasswordField();
        password_label1 = new javax.swing.JLabel();
        f_email = new javax.swing.JTextField();
        username_label = new javax.swing.JLabel();
        email_label = new javax.swing.JLabel();
        password_label = new javax.swing.JLabel();
        f_username = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        use_label = new javax.swing.JLabel();
        e_label = new javax.swing.JLabel();
        pass_label = new javax.swing.JLabel();
        repass_label = new javax.swing.JLabel();
        Separator3 = new javax.swing.JSeparator();

        jDialog1.setIconImage(null);
        jDialog1.setLocation(new java.awt.Point(0, 0));
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(400, 300));
        jDialog1.setType(java.awt.Window.Type.POPUP);

        jLabel2.setText("jLabel2");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("adsadsa");
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        setTitle("QuestPhil Diagnostics");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        signup_label.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        signup_label.setForeground(new java.awt.Color(51, 102, 255));
        signup_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/male.png"))); // NOI18N
        signup_label.setText("Sign Up");

        c_position.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c_position.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASHIER", "DOCTOR", "NURSE", "RECEPTION", "RAD TECH", "MED TECH" }));

        view_license_text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        view_license_text.setForeground(new java.awt.Color(255, 0, 0));
        view_license_text.setText("View License Agreement");
        view_license_text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view_license_textMouseClicked(evt);
            }
        });

        signup_button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        signup_button.setText("Sign Up");
        signup_button.setEnabled(false);
        signup_button.setMaximumSize(new java.awt.Dimension(85, 35));
        signup_button.setMinimumSize(new java.awt.Dimension(85, 35));
        signup_button.setPreferredSize(new java.awt.Dimension(85, 35));
        signup_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                signup_buttonMousePressed(evt);
            }
        });

        position_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        position_label.setText("Position: ");

        license_checkbox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        license_checkbox.setText(" Accept End User's License Agreement");
        license_checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                license_checkboxActionPerformed(evt);
            }
        });

        comment.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        comment.setForeground(new java.awt.Color(204, 0, 0));

        f_fname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        f_lname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lname_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lname_label.setText("Last Name: ");

        password_label2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        password_label2.setText("Birth Date: ");

        fnamel_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        fnamel_label.setText("First Name: ");

        mname_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        mname_label.setText("Middle Name: ");

        f_birthdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_birthdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_birthdateKeyReleased(evt);
            }
        });

        f_mname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("YYYY-MM-DD eg.(2012-02-15)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(bday_label, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bday_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        password_label3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        password_label3.setText("Gender:");

        r_gender.add(male);
        male.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        male.setText("   Male");

        r_gender.add(female);
        female.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        female.setText("   Female");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mname_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fnamel_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lname_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(password_label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(password_label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(female))
                            .addComponent(f_birthdate)
                            .addComponent(f_fname, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(f_lname)
                            .addComponent(f_mname))))
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lname_label)
                    .addComponent(f_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnamel_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_mname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mname_label))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_label2)
                    .addComponent(f_birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_label3)
                    .addComponent(male)
                    .addComponent(female))
                .addGap(22, 22, 22))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        f_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_passwordKeyReleased(evt);
            }
        });

        f_password1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_password1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_password1KeyReleased(evt);
            }
        });

        password_label1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        password_label1.setText("Retype Password: ");

        f_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_emailKeyReleased(evt);
            }
        });

        username_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        username_label.setText("Username: ");

        email_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        email_label.setText("Email Address:");

        password_label.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        password_label.setText("Password: ");

        f_username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_usernameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(repass_label, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(pass_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(e_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(use_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(use_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(e_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(pass_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(repass_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password_label1)
                            .addComponent(password_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(f_password, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(f_password1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(username_label, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f_username, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(email_label, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f_email)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_label)
                    .addComponent(f_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password_label))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_label1)
                    .addComponent(f_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(signup_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(position_label, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(c_position, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(license_checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(view_license_text, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(107, 107, 107)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Separator1)
                            .addComponent(Separator3)
                            .addComponent(Separator2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(signup_label)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(signup_label, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_position, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(position_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(license_checkbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(view_license_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(signup_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        getAccessibleContext().setAccessibleName("SignUp");
        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO: Close the window(automatic not overide) then call login
        //new Login().setVisible(false);
       // new Login().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void license_checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_license_checkboxActionPerformed
        // TODO add your handling code here:
            if(license_checkbox.isSelected()== true){
                signup_button.setEnabled(true);
                                                    }
            else{signup_button.setEnabled(false);}
    }//GEN-LAST:event_license_checkboxActionPerformed

    private void view_license_textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view_license_textMouseClicked
        // TODO:
        //open a text file containing the EULA
        try{
        //Runtime.getRuntime().exec("notepad.exe, ../src/files/Qis_eula.txt");
        //Runtime.getRuntime().exec("Taskkill/ IM notepad.exe/ F");
        //Runtime.getRuntime().exec("nircmd closeprocess notepad.exe");
        Runtime.getRuntime().exec("notepad.exe");
        }
        catch(IOException e){}
        try{Thread.sleep(2000);}catch(InterruptedException e){}
        view_license_text.setForeground(Color.blue);
    }//GEN-LAST:event_view_license_textMouseClicked

    private void signup_buttonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signup_buttonMousePressed
        // TODO: save actions here
                // TODO add your handling code here:
        comment.setText("");       
        String fusername = f_username.getText();
        String flname = f_lname.getText();
        String ffname = f_fname.getText();
        String fmname = f_mname.getText();
        //String fbirthdate = ("01/01/2001");
        String fbirthdate = f_birthdate.getText();
        String femail = f_email.getText();
        String fpassword = new String(f_password.getPassword());
        String fprivilege = c_position.getItemAt(c_position.getSelectedIndex());
        
/////////////////////////////////////
try{
        if(flname.equals("")){
                          comment.setText("*Last Name is required");
                          f_lname.requestFocusInWindow();
                         }
        else if(ffname.equals("")){
                          comment.setText("*First Name is required");
                          f_fname.requestFocusInWindow();
                                     }
        else if(fbirthdate.equals("")){
                          comment.setText("*Birthday is required");
                          f_birthdate.requestFocusInWindow();
                                    }
        else if(!male.isSelected()&&!female.isSelected()){
                          comment.setText("*Gender is required");
                          f_birthdate.requestFocusInWindow();
                                    }
        else if(fusername.equals("")){
                          comment.setText("*Username is required");
                          f_username.requestFocusInWindow();
                                    }
        else if(femail.equals("")){
                          comment.setText("*Email is required");
                          f_email.requestFocusInWindow();
                                    }
        else if(fpassword.equals("")){
                          comment.setText("*Password is required");
                          f_password.requestFocusInWindow();
                                    }
        else                        {
                          //boolean check
                          if(bd==false)     {f_birthdate.requestFocusInWindow();
                                            comment.setText("*Date input is invalid");}
                          else if(un==false){f_username.requestFocusInWindow();
                                            comment.setText("*Username already taken");}
                          else if(em==false){f_email.requestFocusInWindow();
                                            comment.setText("*Email input is invalid");}
                          else if(pw==false){f_password.requestFocusInWindow();                                             
                                            comment.setText("*Password must atleast contain 8 characters");}
                          else if(pw1==false){f_password1.requestFocusInWindow();                                             
                                            comment.setText("*Password mismatch");}
                          else              {
                                        String gender=null;
                                        if(male.isSelected()){gender="MALE";}
                                        else{gender="FEMALE";}
                                        String query = "insert into tbl_account (username,lastname,firstname,middlename,birthdate,gender,emailaddress,password,privilege,status) values('"+fusername+"','"+flname+"','"+ffname+"','"+fmname+"','"+fbirthdate+"','"+gender+"','"+femail+"','"+fpassword+"','"+fprivilege+"', 1)";
                                        DatabaseConnect.connect();
                                        if(DatabaseConnect.runQuery(query)==true){
 
                                        JOptionPane.showMessageDialog(null,"Record successfully saved.","QuestPhil Diagnostics",1);
                                        this.dispose();
                                            }
                                        else{}
                                    }
        }
        
    }
     catch(Exception ex){
                     JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());            
   
                }
    }//GEN-LAST:event_signup_buttonMousePressed

    private void f_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_passwordKeyReleased
        // TODO: Check if atleast 8 characters
        String fpassword = new String(f_password.getPassword());
                                if(fpassword.equals("")){
                                  //pass_label.setText("");
                                    pass_label.setIcon(null);
                                  //repass_label.setText("");
                                  f_password1.setText("");
                                  repass_label.setIcon(null);
                                 }
                else{
        boolean passpattern = Pattern.compile("(.{8,})").matcher(fpassword).matches();
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
            }
                                
        //can also use:
        //Pattern p =Pattern.compile("");
        //Matcher m.= p.matches("");
        //boolean passpatern b= m.matches();
    passCheck();
                                }
    }//GEN-LAST:event_f_passwordKeyReleased

    private void f_password1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_password1KeyReleased
    // TODO: MATCH THE TWO PASSWORD
    //clear field     
    String fpassword1 = new String(f_password1.getPassword());                   
        if(fpassword1.equals("")){
        repass_label.setIcon(null);
        pw1=false;
                                 }
    else{passCheck();}
     //System.out.println(""+fpassword+"  "+fpassword1+"  ");
    }//GEN-LAST:event_f_password1KeyReleased

    private void f_emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_emailKeyReleased
        // TODO: check email validity via regex
        //\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b
        String femail= f_email.getText();
                        if(femail.equals("")){
                                  e_label.setIcon(null);
                                 }
                else{
        boolean emailpattern = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b").matcher(femail).matches();
        if(!emailpattern==true){
            e_label.setIcon(cross);
            em=false;
            //System.out.println("being pressed");
        }
        else{
            em=true;
            e_label.setIcon(check);
            
            }
                        }
    }//GEN-LAST:event_f_emailKeyReleased

    private void f_birthdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_birthdateKeyReleased
        // TODO:  Validationfor date
        //(((0[13578]|10|12)([-./])(0[1-9]|[12][0-9]|3[01])([-./])(\d{4}))|((0[469]|11)([-./])([0][1-9]|[12][0-9]|30)([-./])(\d{4}))|((2)([-./])(0[1-9]|1[0-9]|2[0-8])([-./])(\d{4}))|((2)(\.|-|\/)(29)([-./])([02468][048]00))|((2)([-./])(29)([-./])([13579][26]00))|((2)([-./])(29)([-./])([0-9][0-9][0][48]))|((2)([-./])(29)([-./])([0-9][0-9][2468][048]))|((2)([-./])(29)([-./])([0-9][0-9][13579][26])))
        String fbirthdate= f_birthdate.getText();
                if(fbirthdate.equals("")){
                                  bday_label.setIcon(null);
                                 }
                else{
        //boolean bdpattern = Pattern.compile("^((((0[13578])|(1[02]))[\\/]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\/]?(([0-2][0-9])|(30)))|(02[\\/]?[0-2][0-9]))[\\/]?\\d{4}$").matcher(fbirthdate).matches();
        boolean bdpattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(fbirthdate).matches();

        if(!bdpattern==true){
            bday_label.setIcon(cross);
            bd= false;
        }
        else{
            bday_label.setIcon(check);
            bd=true;
            }
                }        
    }//GEN-LAST:event_f_birthdateKeyReleased

    private void f_usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_usernameKeyReleased
        // TODO: check database for duplicate then return a boolean check
        String username= f_username.getText();
        if(username.equals("")){
                                  use_label.setIcon(null);
                                 }
        else{
        String col ="username";
        String query;
            query = "SELECT "+col+" FROM TBL_ACCOUNT where username like '"+username+"'";
            DatabaseConnect.connect();
            String value=(DatabaseConnect.getValue(query,col));
            if(value==""){
                            use_label.setIcon(check);
                            un=true;
                          }
            else{
                use_label.setIcon(cross);
                un=false;
                }
            }
    }//GEN-LAST:event_f_usernameKeyReleased
    public void passCheck(){
    String fpassword = new String(f_password.getPassword());
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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Signup().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JSeparator Separator1;
    javax.swing.JSeparator Separator2;
    javax.swing.JSeparator Separator3;
    javax.swing.JLabel bday_label;
    javax.swing.JComboBox<String> c_position;
    javax.swing.JLabel comment;
    javax.swing.JLabel e_label;
    javax.swing.JLabel email_label;
    javax.swing.JTextField f_birthdate;
    javax.swing.JTextField f_email;
    javax.swing.JTextField f_fname;
    javax.swing.JTextField f_lname;
    javax.swing.JTextField f_mname;
    javax.swing.JPasswordField f_password;
    javax.swing.JPasswordField f_password1;
    javax.swing.JTextField f_username;
    javax.swing.JRadioButton female;
    javax.swing.JLabel fnamel_label;
    javax.swing.JDialog jDialog1;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel4;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JTextArea jTextArea1;
    javax.swing.JTextPane jTextPane1;
    javax.swing.JCheckBox license_checkbox;
    javax.swing.JLabel lname_label;
    javax.swing.JRadioButton male;
    javax.swing.JLabel mname_label;
    javax.swing.JLabel pass_label;
    javax.swing.JLabel password_label;
    javax.swing.JLabel password_label1;
    javax.swing.JLabel password_label2;
    javax.swing.JLabel password_label3;
    javax.swing.JLabel position_label;
    javax.swing.ButtonGroup r_gender;
    javax.swing.JLabel repass_label;
    javax.swing.JButton signup_button;
    javax.swing.JLabel signup_label;
    java.awt.TextArea textArea1;
    javax.swing.JLabel use_label;
    javax.swing.JLabel username_label;
    javax.swing.JLabel view_license_text;
    // End of variables declaration//GEN-END:variables
}
