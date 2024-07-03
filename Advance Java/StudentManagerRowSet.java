import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.table.DefaultTableModel;

public class StudentManagerRowSet {

    //Database connection details
    
    final String DB_URL = "jdbc:mariadb://localhost/College";
    final String USER = "root";  
    final String PASS = "root";  

    JdbcRowSet jdbcRowSet;
    
    //Construction to initializr the GUI and databse connection

    public StudentManagerRowSet() {

        // Establish database connection

        try {

            //Create Jdbc Rowset instance and set connection details

            jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRowSet.setUrl(DB_URL);
            jdbcRowSet.setUsername(USER);
            jdbcRowSet.setPassword(PASS);
        
            // Creating and setting up the main frame

            JFrame frame = new JFrame("StudentManager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setLayout(null); 

            //Roll No label and text field

            JLabel labelRollNo = new JLabel("RollNo: ");
            labelRollNo.setBounds(150, 50, 100, 30);
            frame.add(labelRollNo);

            JTextField txtRollNo = new JTextField();
            txtRollNo.setBounds(250, 50, 200, 30);
            frame.add(txtRollNo);

            //Name label and text field

            JLabel labelName = new JLabel("Name: ");
            labelName.setBounds(150, 100, 100, 30);
            frame.add(labelName);

            JTextField txtName = new JTextField();
            txtName.setBounds(250, 100, 200, 30);
            frame.add(txtName);

            //Age label and text field

            JLabel labelAge = new JLabel("Age: ");
            labelAge.setBounds(150, 150, 100, 30);
            frame.add(labelAge);

            JTextField txtAge = new JTextField();
            txtAge.setBounds(250, 150, 200, 30);
            frame.add(txtAge);

            //Clear button

            JButton btnClear = new JButton("Clear");
            btnClear.setBounds(150, 200, 100, 30);
            frame.add(btnClear);

            //Save button

            JButton btnSave = new JButton("Save");
            btnSave.setBounds(260, 200, 100, 30);
            frame.add(btnSave);

            //Edit button

            JButton btnEdit = new JButton("Edit");
            btnEdit.setBounds(370, 200, 100, 30);
            frame.add(btnEdit);

            //Delete button

            JButton btnDelete = new JButton("Delete");
            btnDelete.setBounds(150, 250, 100, 30);
            frame.add(btnDelete);

            //Find button

            JButton btnFind = new JButton("Find");
            btnFind.setBounds(260, 250, 100, 30);
            frame.add(btnFind);

            //Display button

            JButton btnDisplay = new JButton("Display");
            btnDisplay.setBounds(370, 250, 100, 30);
            frame.add(btnDisplay);

            //Table to display data

            String[] columnsNames = {"Roll No", "Name", "Age"};
            DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 300, 500, 150);
            frame.add(scrollPane);

            // Make the frame visible
            frame.setVisible(true);

            //Action Listerner for Clear button

            btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    //Clear text field
                    txtRollNo.setText("");
                    txtName.setText("");
                    txtAge.setText("");
                }
            });

            //Action Listerner for Save button

            btnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //Insert new student into datbase
                        jdbcRowSet.setCommand("INSERT INTO Students (rollno, name, age) VALUES (?, ?, ?)");
                        jdbcRowSet.setInt(1,Integer.parseInt(txtRollNo.getText()));
                        jdbcRowSet.setString(2, txtName.getText());
                        jdbcRowSet.setInt(3,Integer.parseInt(txtAge.getText()));
                        jdbcRowSet.execute();
                        JOptionPane.showMessageDialog(btnSave, "Student saved sucessfully");
                        //Clear the text field
                        txtRollNo.setText("");
                        txtName.setText("");
                        txtAge.setText("");
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }           
            });

            //Action Listerner for Edit button

            btnEdit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //Update students details in the datbase
                        jdbcRowSet.setCommand("UPDATE Students SET name = ?, age = ? WHERE rollno = ?");
                        jdbcRowSet.setString(1, txtName.getText());
                        jdbcRowSet.setInt(2, Integer.parseInt(txtAge.getText()));
                        jdbcRowSet.setInt(3, Integer.parseInt(txtRollNo.getText()));
                        jdbcRowSet.execute();
                        JOptionPane.showMessageDialog(btnEdit, "Student updated successfully!");
                        //Clear the text field
                        txtRollNo.setText("");
                        txtName.setText("");
                        txtAge.setText("");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            //Action Listerner for Delete button

            btnDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //Delete student from the database
                        jdbcRowSet.setCommand("DELETE FROM Students WHERE rollno = ?");
                        jdbcRowSet.setInt(1, Integer.parseInt(txtRollNo.getText()));
                        jdbcRowSet.execute();
                        JOptionPane.showMessageDialog(btnDelete, "Student deleted sucessfully");
                        //Clear the text field
                        txtRollNo.setText("");
                        txtName.setText("");
                        txtAge.setText("");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            //Action Listerner for Find button

            btnFind.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //Find the student in databse
                        jdbcRowSet.setCommand("SELECT * FROM Students WHERE rollno = ?");
                        jdbcRowSet.setInt(1, Integer.parseInt(txtRollNo.getText()));
                        jdbcRowSet.execute();
                        if (jdbcRowSet.next()) {
                            //Show text field with student details
                            txtName.setText(jdbcRowSet.getString("name"));
                            txtAge.setText(jdbcRowSet.getString("age"));
                        } else {
                            //Message if no student found
                            JOptionPane.showMessageDialog(btnFind, "Student not found");
                            //Clear the text fields
                            txtRollNo.setText("");
                            txtName.setText("");
                            txtAge.setText("");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            btnDisplay.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //Clear table before displaying records
                        tableModel.setRowCount(0);
                        //Retrive all students from database
                        jdbcRowSet.setCommand("SELECT * FROM Students");
                        jdbcRowSet.execute();
                        //Add students to the table
                        while (jdbcRowSet.next()){
                            tableModel.addRow(new Object[] {jdbcRowSet.getInt("rollno"), jdbcRowSet.getString("name"), jdbcRowSet.getInt("age")});
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Main method
    public static void main(String[] args) {
        new StudentManagerRowSet();
    }

}
