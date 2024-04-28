import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the screen you use to enter information to the
 * OpenAI API. It takes in a query, passes it to a new ChatGPTPrompt object,
 * and then outputs the result to the screen.
 *
 * @author Raymond Tsai
 * @version 4/28/2024
 */
public class SearchFrame extends JFrame implements ActionListener {
    private JButton backButton;
    private JTextField searchBar;
    private JButton enterButton;
    private String choice;
    private JTextArea textArea;
    private JLabel instructions;

    public SearchFrame(String path) {
        this.choice = path;

        //setting up the frame
        this.setTitle("AI Travel Assistant");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1300, 700);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        //creating a panel for the top
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setLayout(new BorderLayout());

        //creaing a panel for the center
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(1300, 600));
        centerPanel.setLayout(new BorderLayout());

        //adding a search bar and enter button
        searchBar = new JTextField();
        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        enterButton.setFocusable(false);
        enterButton.setPreferredSize(new Dimension(200, 50));
        searchBar.setPreferredSize(new Dimension(200, 50));
        enterButton.setFont(new Font("Open Sans", Font.BOLD, 20));
        enterButton.setBackground(Color.YELLOW);
        searchBar.setFont(new Font("Open Sans", Font.PLAIN, 30));

        //adding a text area for the output
        textArea = new JTextArea(10, 20);
        JScrollPane outputArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Open Sans", Font.PLAIN, 25));

        //adding instructions
        instructions = new JLabel();
        switch (path) {
            case "location":
                instructions.setText("Please enter a location you'd like to travel to:");
                break;
            case "activity":
                instructions.setText("Please enter some activities you'd like to do:");
                break;
            case "free":
                instructions.setText("Enter your own request:");
                break;
        }
        instructions.setFont(new Font("Open Sans", Font.PLAIN, 20));

        //back button
        backButton = new JButton();
        backButton.setFocusable(false);
        backButton.setText("Back");
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.setFont(new Font("Open Sans", Font.BOLD, 20));
        backButton.setBackground(Color.YELLOW);

        //adding components to panels and panels to the frame
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(searchBar, BorderLayout.CENTER);
        topPanel.add(enterButton, BorderLayout.EAST);
        topPanel.add(instructions, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(outputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            new SelectionFrame();
        }
        else if (e.getSource() == enterButton) {
            String orignialInstructions = instructions.getText();
            instructions.setText(orignialInstructions + " loading...");
            ChatGPTPrompt query;
            String outputString;
            switch (choice) {
                case "location":
                    query = new ChatGPTPrompt("Please create a vacation plan for me for " + searchBar.getText());
                    outputString = "";
                    for (String temp: query.getAnswer()) {
                        outputString = outputString.concat(temp + "\n");
                    }
                    searchBar.setText("");
                    textArea.setText(outputString);
                    instructions.setText(orignialInstructions);
                    break;
                case "activity":
                    query = new ChatGPTPrompt("Please create a vacation plan for me if I want to " + searchBar.getText());
                    outputString = "";
                    for (String temp: query.getAnswer()) {
                        outputString = outputString.concat(temp + "\n");
                    }
                    searchBar.setText("");
                    textArea.setText(outputString);
                    instructions.setText(orignialInstructions);
                    break;
                case "free":
                    query = new ChatGPTPrompt(searchBar.getText());
                    outputString = "";
                    for (String temp: query.getAnswer()) {
                        outputString = outputString.concat(temp + "\n");
                    }
                    searchBar.setText("");
                    textArea.setText(outputString);
                    instructions.setText(orignialInstructions);
                    break;
            }
        }
    }
}
