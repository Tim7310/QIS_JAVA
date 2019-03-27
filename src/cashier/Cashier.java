package cashier;
import files.DatabaseConnect;        
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import print.Printer;
import javax.swing.DefaultListModel;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Cashier extends javax.swing.JFrame {

    /**
     * Creates new form Cashier
     * @param transType
     */
    private Icon check = new ImageIcon("src/src_images/check.png");
    private Icon cross = new ImageIcon("src/src_images/cross.png");
     String age,gen,cname,ap,fn,mn,ln,address,bdate,bill,email,referred,comment1,contact;
     DefaultListModel listModel = new DefaultListModel();
     public Cashier(String transType,String name) {
        initComponents();
        this.setLocationRelativeTo(null);
        f_transaction.setText(transType);
        transOption toption1 = new transOption(name);
        user.setText(name);
        f_age.setEditable(false);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //get current date time with Date()
        java.util.Date date = new java.util.Date();
        datetime.setText(String.valueOf(dateFormat.format(date)));
        tblitems.setShowVerticalLines(false);
    //set table no cell line
    //tbl1.setShowGrid(false);
    
        listing();
    //---------------TEST----------------
           try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select items,price from tbl_cashier12345");
            ResultSet result = command.executeQuery();
            tblitems.setModel(DbUtils.resultSetToTableModel(result));
    // set alignment column two to the right 
            TableColumnModel columnModel = tblitems.getColumnModel();
            TableColumn column = columnModel.getColumn(1); 
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.RIGHT);
            column.setCellRenderer(renderer);
    //padding in jframe depend on the list of jpanel        
            int rows = tblitems.getRowCount();
            printReceipt.setMinimumSize(new Dimension(250,500+(16*rows)));
            
            con.close();
            command.close();
            result.close();
            }
        catch(Exception ex)
            {
            }  
        ///****** AUTO CUSTOMERS LIST  ******************
       try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select CUSTOMERSID ,LAST_NAME AS LASTNAME, FIRST_NAME AS FIRSTNAME, BIRTH_DATE AS BIRTHDATE from tbl_customers");
            ResultSet result = command.executeQuery();
            tblpatient.setModel(DbUtils.resultSetToTableModel(result));
            con.close();
            command.close();
            result.close();
            }
        catch(Exception ex)
            {
            }
        
        if("CASH".equals(f_transaction.getText()))
        {
        //this form enable or disable
       // this.setEnabled(false);
       //if not selected
      
        toption1.setVisible(false);
        toption1.setAlwaysOnTop(false);
        
        }else
        {}
      
    }
     
     //  ***************** ALL FUNCTION ***********************
     
     // ******************* UPPER CASE***************
    public void UpperCase(){
            cname = f_cname.getText().toUpperCase();
            ap = f_ap.getText().toUpperCase();
            fn = f_fn.getText().toUpperCase();
            mn = f_mn.getText().toUpperCase();
            ln = f_ln.getText().toUpperCase();
            address = f_address.getText().toUpperCase();
            bdate =f_bdate.getText();
            age = f_age.getText();
            contact = f_cn.getText();
            bill = f_bill.getText().toUpperCase();
            email = f_ea.getText().toUpperCase();
            referred = f_reffered.getText().toUpperCase();
            comment1 = f_comment.getText().toUpperCase();
    }
     // ******* LIST OF ITEMS AND PACKAGES ******************
      public void listing(){
        try{
            DatabaseConnect.connect();
            Connection con= DatabaseConnect.con;
            if(f_transaction.getText().equals("CASH")){  
                 PreparedStatement command = con.prepareStatement("SELECT  pack_name FROM TBL_PACKAGES where comp_name ='CASH'");
                 ResultSet result = command.executeQuery();
                 while (result.next()){      
                        c_selectCompany.addItem(result.getString("PACK_NAME"));
                 }//END WHILE
            }// END IF
            if(f_transaction.getText().equals("ACCOUNT")){
                    PreparedStatement command = con.prepareStatement("SELECT  pack_name FROM TBL_PACKAGES where comp_name !='CASH'");
                    ResultSet result = command.executeQuery();
                    while (result.next()){      
                            c_selectCompany.addItem(result.getString("PACK_NAME"));
                    }// END WHILE
            }//END IF
            c_selectCompany.addItem("-------------------ITEM LIST-------------------");
            PreparedStatement command1 = con.prepareStatement("SELECT ITEMNAME FROM TBL_ITEMS");
            ResultSet result1 = command1.executeQuery();
                    while (result1.next()){      
                    c_selectCompany.addItem(result1.getString("ITEMNAME"));
                    }//END WHILE      
            con.close();
       
        }// END TRY
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());
                }
    } // END LIST OF ITEMS AND PACKAGES
      
      //*********** CALCULATE AGE ********* 
    public static int getAge(LocalDate dob) {
            LocalDate curDate = LocalDate.now();
            return Period.between(dob, curDate).getYears();
    }// END CALCULATE AGE
    
    //****** SEARCH PATIENT in TEXTFIELD ********
    public void searchPatient()
    {
        String search = spatient.getText().toUpperCase();  
        try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select CUSTOMERSID ,LAST_NAME AS LASTNAME, FIRST_NAME AS FIRSTNAME, BIRTH_DATE from tbl_customers where last_name like '%"+search+"%' or first_name like '%"+search+"%' or company_name like '%"+search+"%' order by last_name");
            ResultSet result = command.executeQuery();
            tblpatient.setModel(DbUtils.resultSetToTableModel(result));
            con.close();
            command.close();
            result.close();
           } //END TRY
        catch(Exception ex){
                    
                }
    }// **** END SEARCH PATIENT IN TEXT FIELD *******************
    
    //**** CLICK or Key UP/DOWN PATIENT************
    public void cudPatient()
    {
            int row = tblpatient.getSelectedRow();
            int t1;
            t1 = (Integer) tblpatient.getValueAt(row,0);
            try{
                DatabaseConnect.connect();
                Connection con = DatabaseConnect.con;
                PreparedStatement command = con.prepareStatement("select * from tbl_customers where customersid ="+t1+" ");
                ResultSet result = command.executeQuery();
                while(result.next()){
                    f_id.setText(String.valueOf(result.getInt(1)));
                    f_cname.setText(result.getString(2));
                    f_ap.setText(result.getString(3));
                    f_fn.setText(result.getString(4));
                    f_mn.setText(result.getString(5));
                    f_ln.setText(result.getString(6));
                    f_address.setText(result.getString(7));
                    f_bdate.setText(String.valueOf(result.getDate(8)));
                    f_age.setText(result.getString(9));
                    gen = result.getString(10);
                        if(gen.equals("MALE")){
                            r_male.setSelected(true);
                        }
                        else if(gen.equals("FEMALE")){
                            r_female.setSelected(true);
                        }
                        else{
                            rgGender.clearSelection();
                        }
                        f_cn.setText(result.getString(11));
                        f_ea.setText(result.getString(12));
                        f_bill.setText(result.getString(13));
                        f_reffered.setText(result.getString(14));
                        f_comment.setText(result.getString(15));
                        }// *** END  WHILE
            }// END TRY
            catch(Exception ex){
            } 
    }// END CLICK or Key UP/DOWN PATIENT
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgGender = new javax.swing.ButtonGroup();
        printReceipt = new javax.swing.JFrame();
        customerpanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pCompany = new javax.swing.JLabel();
        pAgeGen = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pDOB = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pReferred = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pCashier = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pTOT = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        tblitems = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        print_Receipt = new javax.swing.JButton();
        proceedTransaction = new javax.swing.JFrame();
        summaryInfo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        g_fn = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        g_cname = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        g_ap = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        g_address = new javax.swing.JLabel();
        g_bdate = new javax.swing.JLabel();
        g_age = new javax.swing.JLabel();
        g_gender = new javax.swing.JLabel();
        g_ea = new javax.swing.JLabel();
        g_bill = new javax.swing.JLabel();
        g_referred = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        g_cn = new javax.swing.JLabel();
        ptProceed = new javax.swing.JButton();
        availItem = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        ptBack = new javax.swing.JButton();
        addReferral = new javax.swing.JFrame();
        pReferral = new javax.swing.JPanel();
        d_license = new javax.swing.JTextField();
        d_fn = new javax.swing.JTextField();
        d_mn = new javax.swing.JTextField();
        d_ln = new javax.swing.JTextField();
        d_specialization = new javax.swing.JTextField();
        d_address = new javax.swing.JTextField();
        d_cn = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        arAdd = new javax.swing.JButton();
        container = new javax.swing.JPanel();
        pCustomer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        f_ea = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        f_bill = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        f_reffered = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        f_comment = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        f_bdate = new javax.swing.JTextField();
        f_age = new javax.swing.JTextField();
        f_address = new javax.swing.JTextField();
        r_female = new javax.swing.JRadioButton();
        r_male = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        f_cn = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        i_bday = new javax.swing.JLabel();
        proceed = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bAddReferral = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        f_mn = new javax.swing.JTextField();
        f_ln = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        f_cname = new javax.swing.JTextField();
        f_ap = new javax.swing.JTextField();
        f_fn = new javax.swing.JTextField();
        comment = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        f_id = new javax.swing.JTextField();
        pInfo = new javax.swing.JPanel();
        l_searchPatient = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpatient = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }};
            spatient = new javax.swing.JTextField();
            user = new javax.swing.JLabel();
            pItems = new javax.swing.JPanel();
            b_change = new javax.swing.JButton();
            f_transaction = new javax.swing.JTextField();
            l_typeofTransaction = new javax.swing.JLabel();
            c_selectCompany = new javax.swing.JComboBox<>();
            jScrollPane3 = new javax.swing.JScrollPane();
            item_list = new javax.swing.JList<>();

            customerpanel.setBackground(new java.awt.Color(255, 255, 255));

            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setText("QUEST PHIL DIAGNOSTICS");

            jLabel4.setText("McArthur Hi-Way Cor. Solome Rd. AC");

            jLabel5.setText("Name        :");

            pName.setText("DELA CRUZ, JUAN C");

            jLabel7.setText("Company  :");

            jLabel8.setText("Age/Gen   :");

            pCompany.setText("WALK IN");

            pAgeGen.setText("16/MALE");

            jLabel11.setText("DOB          :");

            pDOB.setText("1968-02-29");

            jLabel13.setText("Referrarl  :");

            pReferred.setText("WALK IN");

            jLabel15.setText("Casheir    :");

            pCashier.setText("Moises");

            jLabel17.setText("Type         :");

            pTOT.setText("CASH");

            jLabel34.setText("RCPT        :");

            jLabel35.setText("#00000000000001");

            jLabel36.setText("_____________________________");

            jLabel37.setText("||||||||||||||||||||||||||||||||||||||||||||");

            datetime.setText("Date and Time");

            tblitems.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null}
                },
                new String [] {
                    "Title 1", "Title 2"
                }
            ));

            jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel38.setText("Powerd By ");

            jLabel39.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
            jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel39.setText("QUEST INFO SYSTEM");

            jLabel40.setText("Receipt Totol           :");

            jLabel41.setText("Amount Tendered    :");

            jLabel42.setText("Change                     :");

            jLabel43.setText("800");

            jLabel44.setText("1000");

            jLabel45.setText("200");

            javax.swing.GroupLayout customerpanelLayout = new javax.swing.GroupLayout(customerpanel);
            customerpanel.setLayout(customerpanelLayout);
            customerpanelLayout.setHorizontalGroup(
                customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerpanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
                .addGroup(customerpanelLayout.createSequentialGroup()
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(customerpanelLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tblitems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(customerpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel40)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel43))
                                .addGroup(customerpanelLayout.createSequentialGroup()
                                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(customerpanelLayout.createSequentialGroup()
                                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(customerpanelLayout.createSequentialGroup()
                                            .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel34))
                                            .addGap(18, 18, 18)
                                            .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel35)
                                                .addComponent(pTOT)
                                                .addComponent(pCashier)
                                                .addComponent(pReferred)))
                                        .addGroup(customerpanelLayout.createSequentialGroup()
                                            .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addComponent(jLabel11))
                                            .addGap(18, 18, 18)
                                            .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(pDOB)
                                                .addComponent(pAgeGen)
                                                .addComponent(pName)
                                                .addComponent(pCompany)))
                                        .addComponent(datetime, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(customerpanelLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            customerpanelLayout.setVerticalGroup(
                customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(datetime, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4)
                    .addGap(11, 11, 11)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(pName))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(pCompany))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(pAgeGen))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(pDOB))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(pReferred, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(pCashier))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(pTOT))
                    .addGap(2, 2, 2)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35))
                    .addGap(2, 2, 2)
                    .addComponent(jLabel36)
                    .addGap(18, 18, 18)
                    .addComponent(tblitems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(jLabel43))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(jLabel44))
                    .addGap(11, 11, 11)
                    .addGroup(customerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(jLabel45))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel39)
                    .addGap(22, 22, 22)
                    .addComponent(jLabel37)
                    .addContainerGap())
            );

            print_Receipt.setText("PRINT");
            print_Receipt.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    print_ReceiptMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout printReceiptLayout = new javax.swing.GroupLayout(printReceipt.getContentPane());
            printReceipt.getContentPane().setLayout(printReceiptLayout);
            printReceiptLayout.setHorizontalGroup(
                printReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(printReceiptLayout.createSequentialGroup()
                    .addComponent(customerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printReceiptLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(print_Receipt)
                    .addGap(29, 29, 29))
            );
            printReceiptLayout.setVerticalGroup(
                printReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(printReceiptLayout.createSequentialGroup()
                    .addComponent(customerpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(print_Receipt))
            );

            proceedTransaction.setMinimumSize(new java.awt.Dimension(720, 480));

            jLabel46.setText("Full Name:");

            g_fn.setText("N/A");

            jLabel48.setText("Company Name:");

            g_cname.setText("N/A");

            jLabel50.setText("Applied Position: ");

            g_ap.setText("N/A");

            jLabel52.setText("Address:");

            jLabel53.setText("Birth Date:");

            jLabel54.setText("Age");

            jLabel55.setText("Gender:");

            jLabel56.setText("Email Address:");

            jLabel57.setText("Bill To:");

            jLabel59.setText("Reffered By:");

            g_address.setText("N/A");

            g_bdate.setText("N/A");

            g_age.setText("N/A");

            g_gender.setText("N/A");

            g_ea.setText("N/A");

            g_bill.setText("N/A");

            g_referred.setText("N/A");

            jLabel47.setText("Contact No:");

            g_cn.setText("N/A");

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel52)
                        .addComponent(jLabel53)
                        .addComponent(jLabel54)
                        .addComponent(jLabel55)
                        .addComponent(jLabel56)
                        .addComponent(jLabel57)
                        .addComponent(jLabel59)
                        .addComponent(jLabel47))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(g_cn)
                        .addComponent(g_referred)
                        .addComponent(g_bill)
                        .addComponent(g_ea)
                        .addComponent(g_gender)
                        .addComponent(g_age)
                        .addComponent(g_bdate)
                        .addComponent(g_address)
                        .addComponent(g_ap)
                        .addComponent(g_cname, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(g_fn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(g_cname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(g_ap)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(g_fn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(g_address))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(g_bdate))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(g_age))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(g_cn))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel55)
                        .addComponent(g_gender))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(g_ea))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57)
                        .addComponent(g_bill))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(g_referred))
                    .addContainerGap())
            );

            javax.swing.GroupLayout summaryInfoLayout = new javax.swing.GroupLayout(summaryInfo);
            summaryInfo.setLayout(summaryInfoLayout);
            summaryInfoLayout.setHorizontalGroup(
                summaryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(summaryInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE))
            );
            summaryInfoLayout.setVerticalGroup(
                summaryInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(summaryInfoLayout.createSequentialGroup()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(133, Short.MAX_VALUE))
            );

            ptProceed.setText("Submit");
            ptProceed.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ptProceedMouseClicked(evt);
                }
            });

            jLabel49.setText("IEMS/PACKAGE");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null}
                },
                new String [] {
                    "Items", "Price"
                }
            ));
            jScrollPane4.setViewportView(jTable1);

            jLabel51.setText("SubTotal                                        :");

            jLabel60.setText("Amount Tendered                          :");

            jLabel61.setText("Change                                          :");

            jLabel62.setText("Discount                                         :");

            jLabel63.setText("Total                                              :");

            javax.swing.GroupLayout availItemLayout = new javax.swing.GroupLayout(availItem);
            availItem.setLayout(availItemLayout);
            availItemLayout.setHorizontalGroup(
                availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(availItemLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(availItemLayout.createSequentialGroup()
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(availItemLayout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(availItemLayout.createSequentialGroup()
                            .addComponent(jLabel60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(availItemLayout.createSequentialGroup()
                            .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
            );
            availItemLayout.setVerticalGroup(
                availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(availItemLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel60)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(availItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61))
                    .addContainerGap(46, Short.MAX_VALUE))
            );

            ptBack.setText("Back");

            javax.swing.GroupLayout proceedTransactionLayout = new javax.swing.GroupLayout(proceedTransaction.getContentPane());
            proceedTransaction.getContentPane().setLayout(proceedTransactionLayout);
            proceedTransactionLayout.setHorizontalGroup(
                proceedTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(proceedTransactionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(proceedTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(proceedTransactionLayout.createSequentialGroup()
                            .addComponent(ptBack)
                            .addGap(39, 39, 39)
                            .addComponent(ptProceed)
                            .addGap(233, 233, 233))
                        .addGroup(proceedTransactionLayout.createSequentialGroup()
                            .addComponent(summaryInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(availItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(90, Short.MAX_VALUE))
            );
            proceedTransactionLayout.setVerticalGroup(
                proceedTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(proceedTransactionLayout.createSequentialGroup()
                    .addGroup(proceedTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(proceedTransactionLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(summaryInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(proceedTransactionLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(availItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(proceedTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ptProceed)
                        .addComponent(ptBack))
                    .addContainerGap(68, Short.MAX_VALUE))
            );

            addReferral.setMinimumSize(new java.awt.Dimension(424, 325));

            pReferral.setMinimumSize(new java.awt.Dimension(424, 285));
            pReferral.setPreferredSize(new java.awt.Dimension(424, 285));

            d_cn.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    d_cnKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    d_cnKeyTyped(evt);
                }
            });

            jLabel69.setText("Contact No:(Optional)");

            jLabel68.setText("Address:");

            jLabel70.setText("Specialization:");

            jLabel67.setText("Last Name:");

            jLabel66.setText("Middle Name:");

            jLabel65.setText("First Name:");

            jLabel64.setText("License No.");

            arAdd.setText("ADD");
            arAdd.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    arAddMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout pReferralLayout = new javax.swing.GroupLayout(pReferral);
            pReferral.setLayout(pReferralLayout);
            pReferralLayout.setHorizontalGroup(
                pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pReferralLayout.createSequentialGroup()
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pReferralLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pReferralLayout.createSequentialGroup()
                                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel64)
                                        .addComponent(jLabel65))
                                    .addGap(63, 63, 63)
                                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(d_license, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                        .addComponent(d_fn)))
                                .addGroup(pReferralLayout.createSequentialGroup()
                                    .addComponent(jLabel67)
                                    .addGap(65, 65, 65)
                                    .addComponent(d_ln))
                                .addGroup(pReferralLayout.createSequentialGroup()
                                    .addComponent(jLabel66)
                                    .addGap(53, 53, 53)
                                    .addComponent(d_mn))
                                .addGroup(pReferralLayout.createSequentialGroup()
                                    .addComponent(jLabel70)
                                    .addGap(48, 48, 48)
                                    .addComponent(d_specialization))
                                .addGroup(pReferralLayout.createSequentialGroup()
                                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel69)
                                        .addComponent(jLabel68))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(d_address)
                                        .addComponent(d_cn)))))
                        .addGroup(pReferralLayout.createSequentialGroup()
                            .addGap(222, 222, 222)
                            .addComponent(arAdd)))
                    .addContainerGap(81, Short.MAX_VALUE))
            );
            pReferralLayout.setVerticalGroup(
                pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pReferralLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(d_license))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65)
                        .addComponent(d_fn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(d_mn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel67)
                        .addComponent(d_ln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel70)
                        .addComponent(d_specialization, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel69)
                        .addComponent(d_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(d_cn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68))
                    .addGap(18, 18, 18)
                    .addComponent(arAdd)
                    .addContainerGap(19, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout addReferralLayout = new javax.swing.GroupLayout(addReferral.getContentPane());
            addReferral.getContentPane().setLayout(addReferralLayout);
            addReferralLayout.setHorizontalGroup(
                addReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addReferralLayout.createSequentialGroup()
                    .addComponent(pReferral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            addReferralLayout.setVerticalGroup(
                addReferralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addReferralLayout.createSequentialGroup()
                    .addComponent(pReferral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setMinimumSize(new java.awt.Dimension(2000, 2000));

            jLabel2.setBackground(new java.awt.Color(153, 153, 153));
            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(51, 102, 255));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("PATIENT INFORMATION");

            jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel29.setText("Email Address:");

            f_ea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_ea.setPreferredSize(new java.awt.Dimension(7, 30));

            jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel30.setText("Bill To:");

            jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel31.setText("Referred By:");

            jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel32.setText("Comment:");

            f_comment.setColumns(20);
            f_comment.setRows(5);
            jScrollPane2.setViewportView(f_comment);

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30)
                                .addComponent(jLabel31))
                            .addGap(14, 14, 14)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(f_ea, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addComponent(f_bill)
                                .addComponent(f_reffered))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_ea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(f_reffered, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32)
                            .addGap(100, 100, 100))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(49, Short.MAX_VALUE))))
            );

            jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel24.setText("Address:");

            jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel25.setText("Birth Date:");

            jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel26.setText("Age:");

            f_bdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_bdate.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    f_bdateKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    f_bdateKeyTyped(evt);
                }
            });

            f_age.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            f_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            rgGender.add(r_female);
            r_female.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            r_female.setText("FEMALE");

            rgGender.add(r_male);
            r_male.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            r_male.setText("MALE");

            jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel27.setText("Gender:");

            jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel28.setText("Contact No.");

            f_cn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_cn.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    f_cnKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    f_cnKeyTyped(evt);
                }
            });

            jLabel33.setForeground(new java.awt.Color(255, 0, 0));
            jLabel33.setText("DATE FORMAT:YYYY-MM-DD");

            i_bday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            proceed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            proceed.setText("Proceed");
            proceed.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    proceedMouseReleased(evt);
                }
            });

            bClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            bClear.setText("Clear");
            bClear.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    bClearMouseReleased(evt);
                }
            });

            bAddReferral.setText("Add");
            bAddReferral.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    bAddReferralMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(r_male)
                                            .addGap(18, 18, 18)
                                            .addComponent(r_female)
                                            .addGap(18, 18, 18)
                                            .addComponent(bAddReferral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(proceed, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(f_cn, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(f_bdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                            .addComponent(f_address, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(f_age, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(34, 34, 34)
                    .addComponent(i_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(f_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(i_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(f_bdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26)
                        .addComponent(f_age, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(r_male)
                        .addComponent(r_female)
                        .addComponent(bAddReferral))
                    .addGap(12, 12, 12)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(f_cn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(proceed, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            f_mn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_mn.setMinimumSize(new java.awt.Dimension(7, 30));
            f_mn.setPreferredSize(new java.awt.Dimension(7, 30));

            f_ln.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel23.setText("Last Name:");

            jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel22.setText("Middle Name: ");

            jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel21.setText("First Name:");

            jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel20.setText("Applied Postion:");

            jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel19.setText("Company Name:");

            f_cname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_cname.setPreferredSize(new java.awt.Dimension(25, 30));

            f_ap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_ap.setPreferredSize(new java.awt.Dimension(7, 30));

            f_fn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            f_fn.setMinimumSize(new java.awt.Dimension(7, 30));
            f_fn.setPreferredSize(new java.awt.Dimension(7, 30));

            comment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            comment.setText("jLabel3");

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(f_mn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                        .addComponent(f_fn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(f_ln))
                                    .addGap(3, 3, 3))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(f_cname, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                        .addComponent(f_ap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_cname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_ap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_fn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_mn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(f_ln, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22))
            );

            jLabel1.setText("Customer ID:");

            javax.swing.GroupLayout pCustomerLayout = new javax.swing.GroupLayout(pCustomer);
            pCustomer.setLayout(pCustomerLayout);
            pCustomerLayout.setHorizontalGroup(
                pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(339, 339, 339))
                .addGroup(pCustomerLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel1)
                    .addGap(68, 68, 68)
                    .addComponent(f_id, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pCustomerLayout.setVerticalGroup(
                pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pCustomerLayout.createSequentialGroup()
                    .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGroup(pCustomerLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(f_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pCustomerLayout.createSequentialGroup()
                            .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            l_searchPatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            l_searchPatient.setText("Search Patient:");

            tblpatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            tblpatient.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "LASTNAME", "FIRSTNAME", "MIDDLE", "COMPANY"
                }
            ));
            tblpatient.setRowHeight(25);
            tblpatient.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tblpatientMouseClicked(evt);
                }
            });
            tblpatient.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tblpatientKeyReleased(evt);
                }
            });
            jScrollPane1.setViewportView(tblpatient);

            spatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            spatient.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    spatientKeyReleased(evt);
                }
            });

            user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            user.setText("jLabel3");

            javax.swing.GroupLayout pInfoLayout = new javax.swing.GroupLayout(pInfo);
            pInfo.setLayout(pInfoLayout);
            pInfoLayout.setHorizontalGroup(
                pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pInfoLayout.createSequentialGroup()
                            .addComponent(l_searchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(spatient, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(139, 139, 139)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pInfoLayout.setVerticalGroup(
                pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pInfoLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_searchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spatient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(85, 85, 85))
            );

            b_change.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            b_change.setText("Change");
            b_change.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    b_changeMouseClicked(evt);
                }
            });

            f_transaction.setEditable(false);
            f_transaction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            l_typeofTransaction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            l_typeofTransaction.setText("Type of Transaction:");

            c_selectCompany.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            c_selectCompany.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------Select Package-------------------" }));
            c_selectCompany.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    c_selectCompanyActionPerformed(evt);
                }
            });

            jScrollPane3.setViewportView(item_list);

            javax.swing.GroupLayout pItemsLayout = new javax.swing.GroupLayout(pItems);
            pItems.setLayout(pItemsLayout);
            pItemsLayout.setHorizontalGroup(
                pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pItemsLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pItemsLayout.createSequentialGroup()
                            .addComponent(l_typeofTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(f_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(b_change)
                            .addGap(173, 173, 173))
                        .addGroup(pItemsLayout.createSequentialGroup()
                            .addGroup(pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(c_selectCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(96, 96, 96))))
            );
            pItemsLayout.setVerticalGroup(
                pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pItemsLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(pItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_typeofTransaction)
                        .addComponent(f_transaction)
                        .addComponent(b_change))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(c_selectCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
            container.setLayout(containerLayout);
            containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 1117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(containerLayout.createSequentialGroup()
                            .addComponent(pItems, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(pCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void proceedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedMouseReleased
        // TODO add your handling code here:
        if(f_cname.getText().equals("")){
                comment.setText("*Company Name is required");
                f_cname.requestFocusInWindow();
        }
        else if(f_ap.getText().equals("")){
                comment.setText("*Applied position is required");
                f_ap.requestFocusInWindow();
        }
        else if(f_fn.getText().equals("")){
                comment.setText("*First Name is required");
                f_fn.requestFocusInWindow();
        }
        else if(f_mn.getText().equals("")){
                comment.setText("*Middle Name is required");
                f_mn.requestFocusInWindow();
        }
        else if(f_ln.getText().equals("")){
                comment.setText("*Last Name is required");
                f_ln.requestFocusInWindow();
        }
        else if(f_address.getText().equals("")){
                comment.setText("*Address is required");
                f_address.requestFocusInWindow();
        }
        else if(f_bdate.getText().equals("")){
                comment.setText("*Birth Date is required");
                f_bdate.requestFocusInWindow();
        }
        else if(!r_male.isSelected()&&!r_female.isSelected()){
                comment.setText("*Gender is required");
        }
        else if(f_cn.getText().equals("")){
                comment.setText("*Contact Number is required");
                f_cn.requestFocusInWindow();
        }
        else{
            if(r_male.isSelected()){
                    gen = "MALE";
            }
            else{
                    gen = "FEMALE";
            }
            //****  PROCEED TRANSACTION ******
            UpperCase();
            g_cname.setText(cname);
            g_ap.setText(ap);
            g_fn.setText(ln + ", " + fn +" " + mn);
            g_address.setText(address);
            g_bdate.setText(bdate);
            g_age.setText(age);
            g_cn.setText(contact);
            g_gender.setText(gen);
            g_ea.setText(email);
            g_bill.setText(bill);
            g_referred.setText(referred);
       
            //*************PRINT *******************
            pName.setText(g_fn.getText());
            pCompany.setText(g_cname.getText());
            pAgeGen.setText(g_age.getText()+ "/" + g_gender.getText());
            pDOB.setText(g_bdate.getText());
            pReferred.setText(g_referred.getText());
            pCashier.setText(user.getText());
            pTOT.setText(f_transaction.getText());
       
            this.setVisible(false);
            proceedTransaction.setVisible(true);
            proceedTransaction.setLocationRelativeTo(null);    
        }
        
         
    }//GEN-LAST:event_proceedMouseReleased

    private void b_changeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_changeMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    this.dispose();
                    transOption toption1 = new transOption(user.getText());
                    toption1.setVisible(true);
        }
    }//GEN-LAST:event_b_changeMouseClicked

    private void spatientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spatientKeyReleased
        // TODO add your handling code here:
        searchPatient();
        
    }//GEN-LAST:event_spatientKeyReleased

    private void tblpatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpatientMouseClicked
        // TODO add your handling code here:
            cudPatient();
    }//GEN-LAST:event_tblpatientMouseClicked

    private void bClearMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bClearMouseReleased
        // TODO add your handling code here:
        new Cashier(f_transaction.getText(),user.getText()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bClearMouseReleased

    private void f_bdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_bdateKeyTyped
        // TODO add your handling code here:
         char enter = evt.getKeyChar();
         if(f_bdate.getText().length()>=10){
                evt.consume();
            }
         if(!(Character.isDigit(enter))){
            evt.consume();
            }      
    }//GEN-LAST:event_f_bdateKeyTyped

    private void f_bdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_bdateKeyReleased
        // TODO add your handling code here:
        String text = f_bdate.getText();
        int counter = text.length();
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){  
                f_age.setText("");
        }
        else{
                if(counter==4){
                        f_bdate.setText(f_bdate.getText()+"-");
                }
                if(counter==7){
                        f_bdate.setText(f_bdate.getText()+"-");
                }
                if(counter==10){
                            try{
                                LocalDate dob = LocalDate.parse(f_bdate.getText());
                                age = String.valueOf(getAge(dob));
                                f_age.setText(age);
                        }
                            catch(Exception ex){
                                // System.out.print("invalid birthdate");
                            }           
                }
            }      
    }//GEN-LAST:event_f_bdateKeyReleased

    private void print_ReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_ReceiptMouseClicked
        // TODO add your handling code here:
         try{
            PrinterJob pjob = PrinterJob.getPrinterJob();
           //PageFormat preformat = pjob.defaultPage();
           //PageFormat pf1 = (PageFormat) preformat.clone();
           //Paper p = preformat.getPaper();
           //p.setImageableArea(0, 0, preformat.getWidth(), preformat.getHeight());
           //  p.setImageableArea(18, 30, 559, 783);
            Paper p = new Paper();
            PageFormat pf1 = new PageFormat();
           // ******* 72 wag papalitan *******
            p.setSize(3 * 72, 6 * 72); 
            double RLmargin = 21.6; // half inch
            double TBmargin1 = 36; // half inch
            p.setImageableArea(RLmargin, TBmargin1, p.getWidth() - RLmargin * 2, p.getHeight()- TBmargin1 * 2);
            pf1.setPaper(p);
            PageFormat pf2 = pjob.validatePage(pf1);
            pf2.setOrientation(PageFormat.PORTRAIT);
            PageFormat postformat = pjob.pageDialog(pf2);
            if(pf2!= postformat){
               //set print component
                pjob.setPrintable(new Printer(customerpanel), postformat);
                if(pjob.printDialog()){
                    pjob.print(); 
                    UpperCase();
                    String query = "INSERT INTO TBL_customers(COMPANY_NAME,APP_POSITION,FIRST_NAME,MIDDLE_NAME,"
                        + "LAST_NAME,ADDRESS,BIRTH_DATE,AGE,GENDER,CONTACT,EMAIL,BILLTO,REFFERED,COMMENT)"
                        + " VALUES "
                        + "('"+cname+"','"+ap+"','"+fn+"','"+mn+"',"
                        + "'"+ln+"','"+address+"','"+bdate+"','"+age+"','"+gen+"',"
                        + "'"+contact+"','"+email+"','"+bill+"','"+referred+"','"+comment1+"')";
                        DatabaseConnect.connect();
                        DatabaseConnect.runQuery(query);
            
               //**** PATIENT RECORDS ********************            
                        
                        if(DatabaseConnect.runQuery(query)==true){
                                int c_id=0;
                                Connection con = DatabaseConnect.con;
                                PreparedStatement command = con.prepareStatement("SELECT CUSTOMERSID FROM TBL_CUSTOMERS ORDER BY CUSTOMERSID DESC LIMIT 1" );
                                ResultSet result = command.executeQuery();
                                while(result.next()){
                                        c_id = result.getInt(1);
                                }
                        for(int count=0; count<item_list.getModel().getSize();count++){
                                String element = item_list.getModel().getElementAt(count);
                                query ="INSERT INTO TBL_CUSTOMERSRECORD VALUES ('"+c_id+"','"+element+"','0000-00-00')";
                                DatabaseConnect.connect();
                                DatabaseConnect.runQuery(query);
                        }
                        }
                }
               
            }  
        }
        catch(Exception ex){
        }
         
        
    }//GEN-LAST:event_print_ReceiptMouseClicked

    private void f_cnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_cnKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(f_cn.getText().length()>=11){
                evt.consume();
        }
        if(!(Character.isDigit(enter))){
                evt.consume();   
        }      
    }//GEN-LAST:event_f_cnKeyTyped

    private void ptProceedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptProceedMouseClicked
        // TODO add your handling code here:
        proceedTransaction.dispose();
        printReceipt.setVisible(true);
        printReceipt.setLocationRelativeTo(null);
    }//GEN-LAST:event_ptProceedMouseClicked

    private void f_cnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_cnKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                f_cn.setText(" ");                   
        }    
    }//GEN-LAST:event_f_cnKeyReleased

    private void bAddReferralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAddReferralMouseClicked
        // TODO add your handling code here:
        this.setEnabled(false);
        addReferral.setVisible(true);
        addReferral.setLocationRelativeTo(null);    
    }//GEN-LAST:event_bAddReferralMouseClicked

    private void arAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arAddMouseClicked
        // TODO add your handling code here:
        String license,g_fn,g_mn,g_ln,g_add,g_specialization,g_cn,g_fname;
        license = d_license.getText();
        g_fn = d_fn.getText().toUpperCase();
        g_mn = d_mn.getText().toUpperCase();
        g_ln = d_ln.getText().toUpperCase();
        g_specialization = d_specialization.getText().toUpperCase(); 
        g_add = d_address.getText().toUpperCase();
        g_cn = d_cn.getText();
        g_fname = g_ln + ", " + g_fn +" " + g_mn;
        String query = "INSERT INTO TBL_referral "
                     + "values("
                     + " '"+license+"','"+g_fname+"','"+g_specialization+"',"
                     + " '"+g_add+"','"+g_cn +"')";
                     DatabaseConnect.connect();
                     DatabaseConnect.runQuery(query);
                     addReferral.dispose();
                     this.setEnabled(true);    
    }//GEN-LAST:event_arAddMouseClicked

    private void d_cnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d_cnKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(d_cn.getText().length()>=11){
                evt.consume();
        }
            if(d_cn.getText().equals(" "))
                  {
            if(d_cn.getText().length()>=1){
                evt.consume();
                     }
            }
            if(!(Character.isDigit(enter))){
                evt.consume();   
                 
             } 
    }//GEN-LAST:event_d_cnKeyTyped

    private void d_cnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d_cnKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            d_cn.setText(" ");                   
            }
    }//GEN-LAST:event_d_cnKeyReleased

    private void c_selectCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_selectCompanyActionPerformed
        // TODO add your handling code here:
        String item = c_selectCompany.getSelectedItem().toString();
        try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select itemname from tbl_items inner join tbl_packofitem on tbl_items.ITEMID = tbl_packofitem.ITEMID where tbl_packofitem.PACK_NAME like '"+item+"' ");
            ResultSet result = command.executeQuery();
            while(result.next()){
                item_list.setModel(listModel);
                listModel.addElement(result.getString("ITEMNAME"));      
            }
            }
        catch(Exception ex){
        }
    }//GEN-LAST:event_c_selectCompanyActionPerformed

    private void tblpatientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblpatientKeyReleased
        // TODO add your handling code here:
        cudPatient();
    }//GEN-LAST:event_tblpatientKeyReleased

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
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cashier(null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame addReferral;
    private javax.swing.JButton arAdd;
    private javax.swing.JPanel availItem;
    private javax.swing.JButton bAddReferral;
    private javax.swing.JButton bClear;
    private javax.swing.JButton b_change;
    private javax.swing.JComboBox<String> c_selectCompany;
    private javax.swing.JLabel comment;
    private javax.swing.JPanel container;
    private javax.swing.JPanel customerpanel;
    private javax.swing.JTextField d_address;
    private javax.swing.JTextField d_cn;
    private javax.swing.JTextField d_fn;
    private javax.swing.JTextField d_license;
    private javax.swing.JTextField d_ln;
    private javax.swing.JTextField d_mn;
    private javax.swing.JTextField d_specialization;
    private javax.swing.JLabel datetime;
    private javax.swing.JTextField f_address;
    private javax.swing.JTextField f_age;
    private javax.swing.JTextField f_ap;
    private javax.swing.JTextField f_bdate;
    private javax.swing.JTextField f_bill;
    private javax.swing.JTextField f_cn;
    private javax.swing.JTextField f_cname;
    private javax.swing.JTextArea f_comment;
    private javax.swing.JTextField f_ea;
    private javax.swing.JTextField f_fn;
    private javax.swing.JTextField f_id;
    private javax.swing.JTextField f_ln;
    private javax.swing.JTextField f_mn;
    private javax.swing.JTextField f_reffered;
    private javax.swing.JTextField f_transaction;
    private javax.swing.JLabel g_address;
    private javax.swing.JLabel g_age;
    private javax.swing.JLabel g_ap;
    private javax.swing.JLabel g_bdate;
    private javax.swing.JLabel g_bill;
    private javax.swing.JLabel g_cn;
    private javax.swing.JLabel g_cname;
    private javax.swing.JLabel g_ea;
    private javax.swing.JLabel g_fn;
    private javax.swing.JLabel g_gender;
    private javax.swing.JLabel g_referred;
    private javax.swing.JLabel i_bday;
    private javax.swing.JList<String> item_list;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel l_searchPatient;
    private javax.swing.JLabel l_typeofTransaction;
    private javax.swing.JLabel pAgeGen;
    private javax.swing.JLabel pCashier;
    private javax.swing.JLabel pCompany;
    private javax.swing.JPanel pCustomer;
    private javax.swing.JLabel pDOB;
    private javax.swing.JPanel pInfo;
    private javax.swing.JPanel pItems;
    private javax.swing.JLabel pName;
    private javax.swing.JPanel pReferral;
    private javax.swing.JLabel pReferred;
    private javax.swing.JLabel pTOT;
    private javax.swing.JFrame printReceipt;
    private javax.swing.JButton print_Receipt;
    private javax.swing.JButton proceed;
    private javax.swing.JFrame proceedTransaction;
    private javax.swing.JButton ptBack;
    private javax.swing.JButton ptProceed;
    private javax.swing.JRadioButton r_female;
    private javax.swing.JRadioButton r_male;
    private javax.swing.ButtonGroup rgGender;
    private javax.swing.JTextField spatient;
    private javax.swing.JPanel summaryInfo;
    private javax.swing.JTable tblitems;
    private javax.swing.JTable tblpatient;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
