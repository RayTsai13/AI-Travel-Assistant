import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the "first" screen you see when you run the program.
 * You are given a brief description of the program, and then given three choices
 * on how to proceed (by entering a location, an activity, or by writing your own
 * prompt).
 *
 * @author Raymond Tsai
 * @version 4/28/2024
 */
public class SelectionFrame extends JFrame implements ActionListener {
    private JButton locationButton;
    private JButton activityButton;
    private JButton freeButton;

    public SelectionFrame() {
        //frame setup
        this.setTitle("AI Travel Assistant");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1300, 700);
        this.setLayout(new BorderLayout());
        ImageIcon plane = new ImageIcon("pakne.png");
        this.setIconImage(plane.getImage());
        this.setVisible(true);

        //adding a panel to the top
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(100, 100));

        //adding a panel to the bottom (for buttons)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(100, 150));
        bottomPanel.setLayout(new BorderLayout());

        //add panels to the bottom panel
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setPreferredSize(new Dimension(400, 150));
        JPanel bottomCenterPanel = new JPanel();
        bottomCenterPanel.setPreferredSize(new Dimension(400, 150));
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setPreferredSize(new Dimension(400, 150));

        //add panels to the center panel
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setPreferredSize(new Dimension(400, 150));
        JPanel centerMidPanel = new JPanel();
        centerMidPanel.setPreferredSize(new Dimension(400, 150));
        JPanel centerBotPanel = new JPanel();
        centerBotPanel.setPreferredSize(new Dimension(400, 150));

        //adding a panel to the center of the screen
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());

        //adding a title
        JLabel title = new JLabel("AI Travel Assistant");
        title.setFont(new Font("Open Sans", Font.BOLD, 50));

        //adding a description for the program
        JLabel description1 = new JLabel("This is a program (powered by the OpenAI API) designed to help you plan for vacations.");
        description1.setFont(new Font("Open Sans", Font.BOLD, 28));
        JLabel description2 = new JLabel("You can choose to enter a location you want to go to,");
        description2.setFont(new Font("Open Sans", Font.BOLD, 40));
        JLabel description3 = new JLabel("an activity you're interested in, or just supply your own prompt!");
        description3.setFont(new Font("Open Sans", Font.BOLD, 40));

        //adding buttons to the bottom
        locationButton = new JButton();
        locationButton.setFocusable(false);
        locationButton.setText("Choose using location");
        locationButton.addActionListener(this);
        locationButton.setPreferredSize(new Dimension(400, 150));
        locationButton.setBackground(Color.YELLOW);
        locationButton.setFont(new Font("Open Sans", Font.BOLD, 30));
        activityButton = new JButton();
        activityButton.setFocusable(false);
        activityButton.setText("Choose using activity");
        activityButton.addActionListener(this);
        activityButton.setPreferredSize(new Dimension(400, 150));
        activityButton.setBackground(Color.YELLOW);
        activityButton.setFont(new Font("Open Sans", Font.BOLD, 30));
        freeButton = new JButton();
        freeButton.setFocusable(false);
        freeButton.setText("Enter your own prompt");
        freeButton.addActionListener(this);
        freeButton.setPreferredSize(new Dimension(400, 150));
        freeButton.setBackground(Color.YELLOW);
        freeButton.setFont(new Font("Open Sans", Font.BOLD, 30));

        //adding components to the frame
        topPanel.add(title);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);
        bottomPanel.add(bottomCenterPanel, BorderLayout.CENTER);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);
        bottomLeftPanel.add(locationButton);
        bottomCenterPanel.add(activityButton);
        bottomRightPanel.add(freeButton);
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerMidPanel, BorderLayout.CENTER);
        centerPanel.add(centerBotPanel, BorderLayout.SOUTH);
        centerTopPanel.add(description1);
        centerMidPanel.add(description2);
        centerBotPanel.add(description3);
    }

    /**
     * Different actions to take depending on the button press.
     * For this class specifically it just creates a new SearchFrame object
     * and passes in a String that shows what choice was made.
     *
     * @author Raymond Tsai
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == locationButton) {
            this.dispose();
            new SearchFrame("location");
        }
        else if (e.getSource() == activityButton) {
            this.dispose();
            new SearchFrame("activity");
        }
        else if (e.getSource() == freeButton) {
            this.dispose();
            new SearchFrame("free");
        }
    }
}
