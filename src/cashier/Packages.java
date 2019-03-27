/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashier;
import java.text.DecimalFormat;
import java.util.Date;
import files.DatabaseConnect;
import java.sql.*;
import java.sql.Connection;
import files.AutoCompletion;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Packages extends javax.swing.JFrame {
DefaultListModel listModel = new DefaultListModel();

    private Icon check = new ImageIcon("src/src_images/check.png");
    private Icon cross = new ImageIcon("src/src_images/cross.png");
    float total =0;
    int size=0;
    boolean expd,pn;
    float finalamount =0;
    DecimalFormat df= new DecimalFormat("###,###,###,###,###,##0.00");
    String Type;
    String existing;
//-------------------------
   public void disableElements(){
                            f_company.setEnabled(false);
                            f_company.setText("");
                            f_package.setEnabled(false);
                            f_package.setText("");
                            c_items.setEnabled(false);
                            add.setEnabled(false);
                            item_list.setEnabled(false);
                            b_remove.setEnabled(false);
                            f_expdate.setEnabled(false);
                            f_expdate.setText("");
                            exp_label.setIcon(null);
                            t_description.setEnabled(false);
                            t_description.setText("");                          
                            discount.setEnabled(false);
                            discount.setText("");
                            amount.setEnabled(false);
                            percentage.setEnabled(false);
                            procceed1.setEnabled(false);
                            date.setText("Date Created: 0000-00-00");
                            date_today.setText("Date last modified: 0000-00-00");
                            orig_amount.setText("00.00");
                            final_amount.setText("00.00");
                            comment.setText("");
                            itemcount.setText("Item Count: 0");

                                }
//-------------------------
      public void enableElements(){
                            comment.setText("");
                            f_company.setEnabled(true);
                            f_package.setEnabled(true);
                            c_items.setEnabled(true);
                            add.setEnabled(true);
                            item_list.setEnabled(true);
                            b_remove.setEnabled(true);
                            f_expdate.setEnabled(true);
                            t_description.setEnabled(true);
                            discount.setEnabled(true);
                            amount.setEnabled(true);
                            percentage.setEnabled(true);
                            procceed1.setEnabled(true);
                                }
//-------------------------
   public void computeFinal(){
        if(!discount.getText().equals("")){
                    float disc = Float.parseFloat(discount.getText());
                    //----check button group disc
                    if(percentage.isSelected())
                                    {disc= (disc*total)/100;
                                    }
                    //------------------
                    finalamount =total-disc;
                    final_amount.setText(String.valueOf(df.format(finalamount)));
                                          }
        else{
           final_amount.setText(String.valueOf(df.format(total)));   
            }
                    }         
//-------------------------
    public Packages(String a) {
        initComponents();
        listing();
        AutoCompletion.enable(c_items);
        if(a.equals("CREATE")){
                    package_label.setText("  Create Package");
                    search.setVisible(false);
                    c_name.setVisible(false);
                    date_today.setText("Date created: "+showDate());
                    Type="CREATE";
        }
        else if(a.equals("EDIT")){
                    disableElements();
                    package_label.setText("  Edit Package");
                    AutoCompletion.enable(search);
                    searchlisting();
                    Type="EDIT";
                   
        }
    }
//--------------------
    public void removeItem(){
                int si = item_list.getSelectedIndex();
                String sn =item_list.getSelectedValue();
                    if(si!= -1){
                        item_list.setModel(listModel);
                        //System.out.println(sn);
                           String col="ITEMPRICE";
                           String query ="SELECT "+col+" FROM TBL_ITEMS WHERE ITEMNAME LIKE '"+sn+"'";
                           DatabaseConnect.connect();                          
                           total= total-Float.parseFloat(DatabaseConnect.getValue(query, col));
                           orig_amount.setText(String.valueOf(df.format(total)));
                           listModel.remove(si);
                           size= listModel.getSize();
                           itemcount.setText("Item Count: "+String.valueOf(size));
                           computeFinal();
                            }
                    }
//--------------------
    public void addtolist(){
        String item = c_items.getSelectedItem().toString();
        item_list.setModel(listModel);
       listModel.addElement(item);
       //check all elements available on list
       addItem();
                       }
//-------------------------
        public void populateList(){
int si = search.getSelectedIndex();
if(si==0){
        disableElements();
         }
else{   enableElements();
        String datecomp= null;
        existing = search.getSelectedItem().toString();
        String pack =null;
        finalamount =0;
            try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select * from TBL_PACKAGES where PACK_NAME ='"+existing+"' ");
            ResultSet result = command.executeQuery();
            while(result.next()){
                    f_company.setText(result.getString(1));
                    f_package.setText(result.getString(2));
                    pack = result.getString(2);
                    f_expdate.setText(result.getString(5));
                    datecomp =  result.getString(5);
                    t_description.setText(result.getString(3));
                    finalamount = Float.parseFloat(result.getString(4));
                    final_amount.setText(String.valueOf(df.format(finalamount)));
                    date.setText("Date Created: "+ result.getString(6));
                    date_today.setText("Date last modified: 0000-00-00");
                    try{
                    date_today.setText("Date last modified: "+result.getString(7));}
                    catch(Exception ex){}
            }
                                            //-----Check dateValidity  
                    if (datecomp.compareTo(showDate()) >= 0) {
                        exp_label.setIcon(check);
                        expd=true;}
                        else if (datecomp.compareTo(showDate()) < 0) {
                        exp_label.setIcon(cross);
                        expd=false;}
                               //-------fetch items on package
                                total = 0;
            DatabaseConnect.connect();
            PreparedStatement command2 = con.prepareStatement("Select itemname,itemprice from tbl_items inner join tbl_packofitem on tbl_items.itemid = tbl_packofitem.itemid where PACK_NAME ='"+pack+"'");
            ResultSet result2 = command2.executeQuery();
                            item_list.setModel(listModel);
                            while(result2.next()){
                            listModel.addElement(result2.getString("itemname"));
                            total = total+ result2.getFloat("itemprice");
                            }
       
       size= listModel.getSize();
       itemcount.setText("Item Count: "+String.valueOf(size));
       orig_amount.setText(String.valueOf(df.format(total)));
       discount.setText(String.valueOf(df.format(total-finalamount)));
       //-------------------------                                            
            }
            catch(Exception ex)
            {
            //JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage()); 
            }
        }
        }

//-------------------------
    /*   public void computePrice(){
           DecimalFormat df= new DecimalFormat("0.00");
        item_list.setModel(listModel);
       //check all elements available on list
       int size= listModel.getSize();
       for(int counter=0;counter!=size;counter++){
                           //----
             
                           String col="ITEMPRICE";
                           String query ="SELECT "+col+" FROM TBL_ITEMS WHERE ITEMNAME LIKE '"+listModel.getElementAt(counter)+"'";
                           DatabaseConnect.connect();
                           System.out.println(DatabaseConnect.getValue(query, col));
                           DatabaseConnect.connect();                           
                           total= total+Float.parseFloat(DatabaseConnect.getValue(query, col));
                                                 }
       //System.out.println(total);
       orig_amount.setText(String.valueOf(df.format(total)));
                       } 
*/
    public void addItem(){
        item_list.setModel(listModel);
       size= listModel.getSize();
       itemcount.setText("Item Count: "+String.valueOf(size));
                           String col="ITEMPRICE";
                           String item = c_items.getSelectedItem().toString();
                           String query ="SELECT "+col+" FROM TBL_ITEMS WHERE ITEMNAME LIKE '"+item+"'";
                           DatabaseConnect.connect();                           
                           total= total+Float.parseFloat(DatabaseConnect.getValue(query, col));
                                
       orig_amount.setText(String.valueOf(df.format(total)));
       computeFinal();
                       }
//-------------------------                
    public void proceed(){
        if(f_company.getText().equals("")){
                          comment.setText("*Company Name is required");
                          f_company.requestFocusInWindow();
                         }
        else if(f_package.getText().equals("")){
                          comment.setText("*Package Name is required");
                          f_package.requestFocusInWindow();
                                     }
        else if(listModel.getSize()==0){
                          comment.setText("*Must Contain atleast 1 item on package");
                                    }
        else if(f_expdate.getText().equals("")){
                          comment.setText("*Enter a valid expiration date");
                          f_expdate.requestFocusInWindow();
                                    }
        else                        {
                          if(expd==false)   {f_expdate.requestFocusInWindow();
                                            comment.setText("*Date input is invalid");}
                          else if(pn==false&&Type.equals("CREATE")){f_package.requestFocusInWindow();
                                            comment.setText("*Package Name already taken");}
                          else              {
        try{
                    String cname =f_company.getText().toUpperCase();
                    String pname =f_package.getText().toUpperCase();
                    String desc = t_description.getText().toUpperCase();
                    Float amount =Float.parseFloat(final_amount.getText());
                    String expdate =f_expdate.getText();
                    String created =showDate();
                    String modify = null;
                    String query=null;
                    if(Type.equals("CREATE")){
                        query="INSERT INTO TBL_PACKAGES VALUES ('"+cname+"','"+pname+"','"+desc+"',"+amount+",'"+expdate+"','"+created+"','0000-00-00')";}
                    else{
                        query="UPDATE TBL_PACKAGES SET "
                                + "COMP_NAME='"+cname+"',"
                                + "PACK_NAME='"+pname+"',"
                                + "DESCRIPTION='"+desc+"',"
                                + "AMOUNT="+amount+","
                                + "EXPDATE='"+expdate+"',"
                                + "DATEUPDATED='"+created+"'"
                                + "WHERE PACK_NAME ='"+existing+"'";
                    }
                    DatabaseConnect.connect();
                    if(DatabaseConnect.runQuery(query)==true){
                        ///----delete all saved data on the listing
                        if(!Type.equals("CREATE")){
                            query="DELETE  FROM TBL_PACKOFITEM WHERE PACK_NAME = '"+existing+"'";
                            DatabaseConnect.connect();
                            DatabaseConnect.runQuery(query);
                             }
                        ///----add to new listing to database
                        int ret =0;                
                        for(ret=ret; ret<item_list.getModel().getSize();ret++){
                        String element = item_list.getModel().getElementAt(ret);
                        int index;
                        String col ="ITEMID";
                        query ="Select "+col+" from TBL_ITEMS WHERE ITEMNAME LIKE '"+element+"'";
                        DatabaseConnect.connect();
                        index = Integer.parseInt(DatabaseConnect.getValue(query, col));
                        
                        query ="INSERT INTO TBL_PACKOFITEM VALUES ('"+pname+"','"+index+"')";
                        DatabaseConnect.connect();
                        DatabaseConnect.runQuery(query);
                        }
                        if(ret==item_list.getModel().getSize()){
                                        JOptionPane.showMessageDialog(null,"Record successfully saved.","QuestPhil Diagnostics",1);
                                        this.dispose();                                   
                        }
                                            }
                    //------end of adding
            }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());            
        }}}}
//----------------------
    public static String showDate()
                    {
                        Date d = new Date();
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                        String today= (sf.format(d));                                            //yyyy-MM-dd-hh.mm.ss
                     return today;                                               
                    }
//---------------------
    public void listing(){
            try{
        DatabaseConnect.connect();
        Connection con= DatabaseConnect.con;
        PreparedStatement command = con.prepareStatement("SELECT ITEMNAME FROM TBL_ITEMS");
        ResultSet result = command.executeQuery();
             while (result.next())
             {      
                String name = result.getString("ITEMNAME");
                c_items.addItem(result.getString("ITEMNAME"));
             }
        con.close();
        command.close();
        result.close();
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());
                }
    }
    //-------------------
    public void searchlisting(){
            try{
        DatabaseConnect.connect();
        Connection con= DatabaseConnect.con;
        PreparedStatement command = con.prepareStatement("SELECT PACK_NAME FROM TBL_PACKAGES");
        ResultSet result = command.executeQuery();
             while (result.next())
             {      
                String name = result.getString("PACK_NAME");
                search.addItem(result.getString("PACK_NAME"));
             }
        con.close();
        command.close();
        result.close();
        }
        catch(Exception ex){
        //JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());
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

        item = new javax.swing.JComboBox<>();
        disc_type = new javax.swing.ButtonGroup();
        package_label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_description = new javax.swing.JTextArea();
        orig_amount = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        procceed1 = new javax.swing.JButton();
        pack_desc = new javax.swing.JLabel();
        amount1 = new javax.swing.JLabel();
        label_amount2 = new javax.swing.JLabel();
        label_discount = new javax.swing.JLabel();
        final_amount = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        date_today = new javax.swing.JLabel();
        exp_date = new javax.swing.JLabel();
        f_expdate = new javax.swing.JTextField();
        exp_label = new javax.swing.JLabel();
        comment = new javax.swing.JLabel();
        label_discount1 = new javax.swing.JLabel();
        amount = new javax.swing.JRadioButton();
        percentage = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        b_remove = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        item_list = new javax.swing.JList<>();
        pname_check = new javax.swing.JLabel();
        f_package = new javax.swing.JTextField();
        f_company = new javax.swing.JTextField();
        p_name = new javax.swing.JLabel();
        c_name = new javax.swing.JLabel();
        p_item = new javax.swing.JLabel();
        c_items = new javax.swing.JComboBox<>();
        itemcount = new javax.swing.JLabel();
        search = new javax.swing.JComboBox<>();
        c_name1 = new javax.swing.JLabel();

        item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        package_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        package_label.setForeground(new java.awt.Color(51, 102, 255));
        package_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src_images/package.png"))); // NOI18N
        package_label.setText("Packages");

        t_description.setColumns(20);
        t_description.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        t_description.setRows(5);
        jScrollPane2.setViewportView(t_description);

        orig_amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        orig_amount.setText("00.00");

        discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                discountKeyTyped(evt);
            }
        });

        procceed1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        procceed1.setForeground(new java.awt.Color(51, 102, 255));
        procceed1.setText("Proceed");
        procceed1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                procceed1MouseReleased(evt);
            }
        });
        procceed1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                procceed1KeyReleased(evt);
            }
        });

        pack_desc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pack_desc.setText("Package Description:");

        amount1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        amount1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        amount1.setText("Total Amount:");

        label_amount2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_amount2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_amount2.setText("Amount:");

        label_discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_discount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_discount.setText("Discount: ");

        final_amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        final_amount.setText("00.00");

        date.setText("   ");

        exp_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exp_date.setText("Expiration Date:");

        f_expdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_expdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_expdateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                f_expdateKeyTyped(evt);
            }
        });

        comment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comment.setForeground(new java.awt.Color(255, 0, 0));
        comment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        label_discount1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_discount1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_discount1.setText("Discount Type: ");

        disc_type.add(amount);
        amount.setSelected(true);
        amount.setText("Amount");
        amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amountMouseClicked(evt);
            }
        });

        disc_type.add(percentage);
        percentage.setText("Percentage");
        percentage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                percentageMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(procceed1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date_today, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f_expdate, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exp_label))
                    .addComponent(pack_desc)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_discount1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(amount1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_amount2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(orig_amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(discount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(final_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(amount)
                                .addGap(18, 18, 18)
                                .addComponent(percentage)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_expdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exp_label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pack_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orig_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_amount2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_discount1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount)
                    .addComponent(percentage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(final_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_today, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(procceed1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        b_remove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_remove.setText("Remove");
        b_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b_removeMouseReleased(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add.setText("Add");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addMouseReleased(evt);
            }
        });
        add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addKeyReleased(evt);
            }
        });

        item_list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        item_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_listKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(item_list);

        f_package.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f_package.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                f_packageKeyReleased(evt);
            }
        });

        f_company.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        p_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p_name.setText("Package Name:");

        c_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c_name.setText("Package to Modify:");

        p_item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p_item.setText("Items on package:");

        c_items.setEditable(true);
        c_items.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c_items.setToolTipText("");
        c_items.setLightWeightPopupEnabled(false);
        c_items.setVerifyInputWhenFocusTarget(false);

        itemcount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        itemcount.setText("Item Count: 0");

        search.setEditable(true);
        search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Available Packages---" }));
        search.setVerifyInputWhenFocusTarget(false);
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        c_name1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c_name1.setText("Company Name:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(itemcount, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p_item, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(c_items, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(search, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(f_package, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f_company, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pname_check, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(f_company, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(f_package, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(c_items, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(p_item, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pname_check, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(itemcount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
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
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(package_label, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(package_label, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void procceed1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procceed1MouseReleased
        proceed();
    }//GEN-LAST:event_procceed1MouseReleased

    private void addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseReleased
        // TODO add your handling code here:
        addtolist();
    }//GEN-LAST:event_addMouseReleased

    private void addKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        addtolist();}
    }//GEN-LAST:event_addKeyReleased

    private void procceed1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_procceed1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        proceed();}
    }//GEN-LAST:event_procceed1KeyReleased

    private void b_removeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_removeMouseReleased
 removeItem();
    }//GEN-LAST:event_b_removeMouseReleased

    private void f_expdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_expdateKeyReleased
        //---------
                // TODO add your handling code here:
        String text = f_expdate.getText();
        int counter = text.length();
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
         {  
            f_expdate.setEditable(true);
            if(counter==10)
            f_expdate.setText( f_expdate.getText().substring(0, f_expdate.getText().length()-1));
         }
       else
        {
             if(counter==4)
            f_expdate.setText(f_expdate.getText()+"-");
             if(counter==7)
                 f_expdate.setText(f_expdate.getText()+"-");
             if(counter==10)
             {
                 if(evt.getKeyCode() != KeyEvent.VK_BACK_SPACE)
                 {
                     //f_expdate.setEditable(false);
                     try{
                            LocalDate dob = LocalDate.parse(f_expdate.getText());
                         }
                        catch(Exception ex){
                        //    System.out.print("invalid bdate format");
                         }
                 }
                
                     
             }
        }
        // TODO:  Validationfor date
        //(((0[13578]|10|12)([-./])(0[1-9]|[12][0-9]|3[01])([-./])(\d{4}))|((0[469]|11)([-./])([0][1-9]|[12][0-9]|30)([-./])(\d{4}))|((2)([-./])(0[1-9]|1[0-9]|2[0-8])([-./])(\d{4}))|((2)(\.|-|\/)(29)([-./])([02468][048]00))|((2)([-./])(29)([-./])([13579][26]00))|((2)([-./])(29)([-./])([0-9][0-9][0][48]))|((2)([-./])(29)([-./])([0-9][0-9][2468][048]))|((2)([-./])(29)([-./])([0-9][0-9][13579][26])))
        String expdate= f_expdate.getText();
                if(expdate.equals("")){
                                  exp_label.setIcon(null);
                                 }
                else{
        boolean exppattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(expdate).matches();
        if(!exppattern==true){
            exp_label.setIcon(cross);
            expd= false;
        }
        else{
            ///---add date check here
            if (expdate.compareTo(showDate()) >= 0) {
                        exp_label.setIcon(check);
                        expd=true;}
            else if (expdate.compareTo(showDate()) < 0) {
                        exp_label.setIcon(cross);
                        expd=false;}
            }
                }  
    }//GEN-LAST:event_f_expdateKeyReleased

    private void discountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountKeyReleased
        computeFinal();
    }//GEN-LAST:event_discountKeyReleased

    private void discountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountKeyTyped
        // TODO add your handling code here:
        char a =evt.getKeyChar();
        if(!(Character.isDigit(a))){
            evt.consume();
                                    }
    }//GEN-LAST:event_discountKeyTyped

@SuppressWarnings("empty-statement")
    private void f_packageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_packageKeyReleased
        // TODO: check database for duplicate then return a boolean check
        String pname = f_package.getText();
        if(pname.equals("")){
                                  pname_check.setIcon(null);
                                 }
        else{
        String col ="PACK_NAME";
        String query = "SELECT "+col+" FROM tbl_packages WHERE PACK_NAME like '"+pname+"'";
            DatabaseConnect.connect();
            String value=(DatabaseConnect.getValue(query,col));
            if("".equals(value)){
                            pname_check.setIcon(check);
                            pn=true;
                          }
            else{
                pname_check.setIcon(cross);
                pn=false;
                }
            }
    }//GEN-LAST:event_f_packageKeyReleased

    private void f_expdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_expdateKeyTyped
         char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
        if(f_expdate.getText().length()>=10){evt.consume();}
    }//GEN-LAST:event_f_expdateKeyTyped

    private void item_listKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_listKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_BACK_SPACE){
                removeItem();
        }
    }//GEN-LAST:event_item_listKeyReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
      listModel.clear();
//        item_list.removeAll();
          populateList();
    }//GEN-LAST:event_searchActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked

    }//GEN-LAST:event_searchMouseClicked

    private void amountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amountMouseClicked
        // TODO add your handling code here:
                computeFinal();
    }//GEN-LAST:event_amountMouseClicked

    private void percentageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_percentageMouseReleased
        // TODO add your handling code here:
                computeFinal();
    }//GEN-LAST:event_percentageMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel 
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
            java.util.logging.Logger.getLogger(Packages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Packages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Packages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Packages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Packages("EDIT").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JRadioButton amount;
    private javax.swing.JLabel amount1;
    private javax.swing.JButton b_remove;
    private javax.swing.JComboBox<String> c_items;
    private javax.swing.JLabel c_name;
    private javax.swing.JLabel c_name1;
    private javax.swing.JLabel comment;
    private javax.swing.JLabel date;
    private javax.swing.JLabel date_today;
    private javax.swing.ButtonGroup disc_type;
    private javax.swing.JTextField discount;
    private javax.swing.JLabel exp_date;
    private javax.swing.JLabel exp_label;
    private javax.swing.JTextField f_company;
    private javax.swing.JTextField f_expdate;
    private javax.swing.JTextField f_package;
    private javax.swing.JLabel final_amount;
    private javax.swing.JComboBox<String> item;
    private javax.swing.JList<String> item_list;
    private javax.swing.JLabel itemcount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label_amount2;
    private javax.swing.JLabel label_discount;
    private javax.swing.JLabel label_discount1;
    private javax.swing.JLabel orig_amount;
    private javax.swing.JLabel p_item;
    private javax.swing.JLabel p_name;
    private javax.swing.JLabel pack_desc;
    private javax.swing.JLabel package_label;
    private javax.swing.JRadioButton percentage;
    private javax.swing.JLabel pname_check;
    private javax.swing.JButton procceed1;
    private javax.swing.JComboBox<String> search;
    private javax.swing.JTextArea t_description;
    // End of variables declaration//GEN-END:variables
}
