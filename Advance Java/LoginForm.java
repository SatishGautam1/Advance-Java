import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class LoginForm {

    private static Properties properties = new Properties();
    private static final String PROPERTIES_FILE = "login.properties";
    
    //Method to load properties from file
    private static void loadProperties() {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Methos to save properties to file
    private static void saveProperties() {
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        loadProperties();

        //Login Form

        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); 

        //Username

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(50, 50, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        frame.add(usernameField);

        //Password

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 100, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        frame.add(passwordField);

        //Remember me

        JCheckBox rememberme = new JCheckBox("Remember me");
        rememberme.setBounds(150, 150, 200, 30);
        frame.add(rememberme);

        //Pre-fill username an password if saved

        String storedUsername = properties.getProperty("username");
        String storedPassword = properties.getProperty("password");

        if (storedUsername != null) {
            usernameField.setText(storedUsername);
            if (storedPassword != null) {
                passwordField.setText(storedPassword);
                rememberme.setSelected(true);
            }
        }
        
        //Log in

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(100, 200, 100, 30);
        frame.add(loginButton);

        //Reset Button

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(220, 200, 100, 30);
        frame.add(resetButton);

        //Action for Login Button

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                System.out.println("Username: "+username);
                System.out.println("Password: "+new String(password));

                if (rememberme.isSelected()) {
                    properties.setProperty("username", username);
                    properties.setProperty("password", new String(password));
                    saveProperties();
                }
                else {
                    properties.remove("username");
                    properties.remove("password");
                    saveProperties();
                }
            }
        });

        //Action for Reset

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
                rememberme.setSelected(false);
            }
        });

        frame.setVisible(true);
    }
}