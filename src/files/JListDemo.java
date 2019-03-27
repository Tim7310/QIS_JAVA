package files;


import javax.swing.*;
 
// The primary class is a JFrame which can be shown when the program starts
public class JListDemo extends JFrame {
    
    /*  main is just housed inside of the primary class. It could be part of a driver
        class but this implementation is simpler. Here we just create a new 
        instance of the class to show the frame which contains the UI and all the 
        application logic. */
    public static void main(String[] args) {

        JListDemo demo = new JListDemo();
    }
    
    public JListDemo()
    {
        // create the list items using a DefaultListModel. This allows us to add/remove
        // items later
        DefaultListModel model = new DefaultListModel();
        model.addElement("piña");
        model.addElement("maracuya");
        model.addElement("guanábana");

        //JList list = new JList(model);
        //add(list);
        
        // finish typical frame setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);



        /*  in a separate operation, we can add new items to the list's model. 
            Think about how you would do the same thing in response to a button click.
            The interactions with the list and model are the same, you just need to 
            restructure things about to take place in the button's action listener. */

        // I know, we already have a reference to the model above, but I want to show
        // you how to get the model if you only have a reference to the list to start with.

        // We have to cast the return value of getModel to a DefaultListModel because
        // it returns a declared type of ListModel (an interface) which doesn't give
        // us the ability to add or remove items.
        //DefaultListModel listModel = (DefaultListModel)list.getModel(); 
        //listModel.addElement("aguacate");
        //listModel.addElement("papaya");
        //listModel.addElement("manga");

        pack(); // automatically resize the frame to fit the JList
    }
}