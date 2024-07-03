import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class StudentManagerCachedRowSet {

    // Database connection details
    final String DB_URL = "jdbc:mariadb://localhost/College";
    final String USER = "root";  
    final String PASS = "root";  

    CachedRowSet cachedRowSet; //Class scope
    
    // Constructor to initialize the GUI and database connection
    public StudentManagerCachedRowSet() {

        // Establish database connection
        try {
            // Create CachedRowSet instance and set connection details
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.setUrl(DB_URL);
            cachedRowSet.setUsername(USER);
            cachedRowSet.setPassword(PASS);
            cachedRowSet.setCommand("SELECT * FROM Students");
            cachedRowSet.execute();
        
            // Creating and setting up the main frame
            JFrame frame = new JFrame("StudentManager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 400);
            frame.setLayout(null); 

            // Roll No label and text field
            JLabel labelRollNo = new JLabel("RollNo: ");
            labelRollNo.setBounds(150, 50, 100, 30);
            frame.add(labelRollNo);

            JTextField txtRollNo = new JTextField();
            txtRollNo.setBounds(250, 50, 200, 30);
            frame.add(txtRollNo);

            // Name label and text field
            JLabel labelName = new JLabel("Name: ");
            labelName.setBounds(150, 100, 100, 30);
            frame.add(labelName);

            JTextField txtName = new JTextField();
            txtName.setBounds(250, 100, 200, 30);
            frame.add(txtName);

            // Age label and text field
            JLabel labelAge = new JLabel("Age: ");
            labelAge.setBounds(150, 150, 100, 30);
            frame.add(labelAge);

            JTextField txtAge = new JTextField();
            txtAge.setBounds(250, 150, 200, 30);
            frame.add(txtAge);

            // ||<< button
            JButton btnFirst = new JButton("||<<");
            btnFirst.setBounds(150, 200, 100, 30);
            frame.add(btnFirst);

            // << button
            JButton btnPrevious = new JButton("<<");
            btnPrevious.setBounds(260, 200, 100, 30);
            frame.add(btnPrevious);

            // >> button
            JButton btnNext = new JButton(">>");
            btnNext.setBounds(370, 200, 100, 30);
            frame.add(btnNext);

            // >>|| button
            JButton btnLast = new JButton(">>||");
            btnLast.setBounds(480, 200, 100, 30);
            frame.add(btnLast);

            // Make the frame visible
            frame.setVisible(true);

            // Action Listener for ||<< button
            btnFirst.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        cachedRowSet.first();
                        updateTextFields(txtRollNo, txtName, txtAge);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            // //Action Listener for << button 
            // btnPrevious.addActionListener(ae -> {
            //     try {
            //         if (!cachedRowSet.isFirst()) {
            //             cachedRowSet.previous();
            //             updateTextFields(txtRollNo, txtName, txtAge);
            //         }
            //     } catch (SQLException e) {
            //         e.printStackTrace();
            //     }
            // });

            // Action Listener for << button
            btnPrevious.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (!cachedRowSet.isFirst()) {
                            cachedRowSet.previous();
                            updateTextFields(txtRollNo, txtName, txtAge);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }           
            });

            // Action Listener for >> button
            btnNext.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (!cachedRowSet.isLast()) {
                            cachedRowSet.next();
                            updateTextFields(txtRollNo, txtName, txtAge);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Action Listener for >>|| button
            btnLast.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        cachedRowSet.last();
                        updateTextFields(txtRollNo, txtName, txtAge);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTextFields(JTextField txtRollNo, JTextField txtName, JTextField txtAge) {
        try {
            txtRollNo.setText(String.valueOf(cachedRowSet.getInt("rollno")));
            txtName.setText(cachedRowSet.getString("name"));
            txtAge.setText(String.valueOf(cachedRowSet.getInt("age")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        new StudentManagerCachedRowSet();
    }
}
