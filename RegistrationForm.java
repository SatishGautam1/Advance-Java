import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class RegistrationForm {
        public static void main (String args[]) {
            
            //Registratin Form

            JFrame frame = new JFrame("Registration Form");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 700);
            frame.setLayout(null);     
            
            // Name Field

            JLabel nameLabel = new JLabel("Name: ");
            nameLabel.setBounds(50, 50, 100, 30);
            frame.add(nameLabel);

            JTextField nameField = new JTextField();
            nameField.setBounds(150, 50, 200, 30);
            frame.add(nameField);
            
            //Address Field

            JLabel addressLabel = new JLabel("Address: ");
            addressLabel.setBounds(50, 100, 100, 30);
            frame.add(addressLabel);

            JTextField addressField = new JTextField();
            addressField.setBounds(150, 100, 200, 30);
            frame.add(addressField);
            
            //Email field

            JLabel emailLabel = new JLabel("Email: ");
            emailLabel.setBounds(50, 150, 100, 30);
            frame.add(emailLabel);

            JTextField emailField = new JTextField();
            emailField.setBounds(150, 150, 200, 30);
            frame.add(emailField);

            //Gender field

            JLabel genderLabel = new JLabel("Gender: ");
            genderLabel.setBounds(50, 200, 200, 30);
            frame.add(genderLabel);

            JRadioButton maleRadioButton = new JRadioButton("Male");
            maleRadioButton.setBounds(150, 200, 80, 30);
            frame.add(maleRadioButton);

            JRadioButton femaleRadioButton = new JRadioButton("Female");
            femaleRadioButton.setBounds(240, 200, 100, 30);
            frame.add(femaleRadioButton);

            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);

            //Faculty field

            JLabel facultyLabel = new JLabel("Faculty: ");
            facultyLabel.setBounds(50, 250, 100, 30);
            frame.add(facultyLabel);
            

            String[] faculties = {"Choose Faculty","Science", "Management", "Humanities and Social Science", "Law", "Arts"};
            JComboBox<String> facultyComboBox = new JComboBox<>(faculties);
            facultyComboBox.setBounds(150, 250, 200, 30);
            frame.add(facultyComboBox);

            //Program field

            JLabel programLabel = new JLabel("Program: ");
            programLabel.setBounds(50, 300, 100, 30);
            frame.add(programLabel);

            String[] programs = {"BCA", "BICT", "BBS"};
            JList<String> programList = new JList<>(programs);
            programList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane programScrollPane = new JScrollPane(programList);
            programScrollPane.setBounds(150, 300, 200, 80);
            frame.add(programScrollPane);

            //Hobbies field

            JLabel hobbiesLabel = new JLabel("Hobbies: ");
            hobbiesLabel.setBounds(50, 400, 100, 30);
            frame.add(hobbiesLabel);

            JCheckBox hobby1 = new JCheckBox("Reading");
            hobby1.setBounds(150, 400, 100, 30);
            frame.add(hobby1);

            JCheckBox hobby2 = new JCheckBox("Travelling");
            hobby2.setBounds(150, 430, 100, 30);
            frame.add(hobby2);

            //Submit Button

            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(150, 550, 100, 30);
            frame.add(submitButton);

            //Reset Button

            JButton resetButton = new JButton("Reset");
            resetButton.setBounds(260, 550, 100, 30);
            frame.add(resetButton);
            
            //Action after submit

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String email = emailField.getText();
                    String gender = maleRadioButton.isSelected() ? "Male" : "Female";
                    String faculty = (String) facultyComboBox.getSelectedItem();
                    String selectedProgram = programList.getSelectedValue();
                    
                    StringBuilder hobbies = new StringBuilder();
                    if (hobby1.isSelected()) hobbies.append("Reading ");
                    if (hobby2.isSelected()) hobbies.append("Travelling ");

                    //Email validation
                    String emailPattern = "^[A-Za-z0-9+_,-]+@(.+)$";
                    Pattern pattern = Pattern.compile(emailPattern);
                    Matcher matcher = pattern.matcher(email);

                    if (!matcher.matches()) {
                        JOptionPane.showMessageDialog(frame, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Gender: " + gender);
                    System.out.println("Faculty: " + faculty);
                    System.out.println("Program: " + selectedProgram);
                    System.out.println("Hobbies: " + hobbies.toString());

                }
            });

            //Action after reset 
            
            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    nameField.setText("");
                    addressField.setText("");
                    emailField.setText("");
                    genderGroup.clearSelection();
                    facultyComboBox.setSelectedIndex(0);
                    programList.clearSelection();
                    hobby1.setSelected(false);
                    hobby2.setSelected(false);
                }
            });

            frame.setVisible(true);
        }
    }