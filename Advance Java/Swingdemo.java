import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swingdemo implements ActionListener {
    JButton SubmitButton, ResetButton;
    JLabel nameLabel, addressLabel, emailLabel;
    JTextField nameField, addressField, emailField;

    JFrame f;
    JPanel p;

    public Swingdemo() {
        SubmitButton = new JButton("Submit");
        ResetButton = new JButton("Reset");

        SubmitButton.addActionListener(this);
        ResetButton.addActionListener(this);

        nameLabel = new JLabel("Name: ");
        addressLabel = new JLabel("Address: ");
        emailLabel = new JLabel("Email: ");

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);

        f = new JFrame();
        p = new JPanel();

        p.add(nameLabel);
        p.add(nameField);
        p.add(addressLabel);
        p.add(addressField);
        p.add(emailLabel);
        p.add(emailField);
        p.add(SubmitButton);
        p.add(ResetButton);

        f.add(p);
        f.setSize(800, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SubmitButton) {
            String name = nameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            System.out.println("Submitted Data:");
            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("Email: " + email);
        } else if (e.getSource() == ResetButton) {
            nameField.setText("");
            addressField.setText("");
            emailField.setText("");
        }
    }

    public static void main(String[] args) {
        new Swingdemo();
    }
}
