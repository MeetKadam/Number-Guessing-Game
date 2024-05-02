import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;
    private int secretNumber;
    private int attempts;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel guessLabel = new JLabel("Enter your guess(1-100):");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessStr = guessField.getText();
            try {
                int guess = Integer.parseInt(guessStr);
                attempts++;
                if (guess == secretNumber) {
                    resultArea.append("Woww...Congratulations! You guessed the correct number in " + attempts + " attempts.\n");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                } else {
                    resultArea.append((guess < secretNumber ? "Too low! " : "Too high! ") + "Try again.\n");
                }
            } catch (NumberFormatException ex) {
                resultArea.append("Invalid input. Please enter a valid number.\n");
            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}
