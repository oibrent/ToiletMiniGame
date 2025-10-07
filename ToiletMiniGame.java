import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ToiletMiniGame extends JFrame {
    private String[] itemsArray = {
        // ✅ Flushable
        "Toilet Paper", "Poop", "Pee",
        // ❌ Not Flushable (fun kid items)
        "Toy Car", "Teddy Bear", "Crayon", "Sticker", "Puzzle Piece", "Candy", "Paper Airplane"
    };
    private List<String> items;  // shuffled list
    private int currentIndex = 0;
    private int score = 0;
    private final int MAX_ROUNDS = 10;

    private JLabel itemLabel;
    private JLabel scoreLabel;
    private JButton flushButton;
    private JButton dontFlushButton;

    public ToiletMiniGame() {
        setTitle("Toilet Mini-Game: Only Flush the Right Items");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Shuffle items for this game
        items = new ArrayList<>(Arrays.asList(itemsArray));
        Collections.shuffle(items);

        // Score label
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        add(scoreLabel, BorderLayout.NORTH);

        // First item
        itemLabel = new JLabel(items.get(currentIndex), SwingConstants.CENTER);
        itemLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        add(itemLabel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        flushButton = new JButton("🚽 Flush");
        flushButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        flushButton.addActionListener(e -> handleChoice(true));
        buttonPanel.add(flushButton);

        dontFlushButton = new JButton("✋ Don't Flush");
        dontFlushButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        dontFlushButton.addActionListener(e -> handleChoice(false));
        buttonPanel.add(dontFlushButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleChoice(boolean flushChoice) {
        String currentItem = items.get(currentIndex);
        boolean shouldFlush = currentItem.equals("Toilet Paper") ||
                              currentItem.equals("Poop") ||
                              currentItem.equals("Pee");

        if ((flushChoice && shouldFlush) || (!flushChoice && !shouldFlush)) {
            score++;
            JOptionPane.showMessageDialog(this,
                    "✅ Good choice!",
                    "Correct",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            score--;
            JOptionPane.showMessageDialog(this,
                    "❌ Oops! Only toilet paper, pee, and poop go in the toilet. This item goes in the trash!",
                    "Incorrect",
                    JOptionPane.WARNING_MESSAGE);
        }

        scoreLabel.setText("Score: " + score);
        currentIndex++;

        if (currentIndex >= MAX_ROUNDS) {
            JOptionPane.showMessageDialog(this,
                    "🎉 Game Over! Your final score: " + score,
                    "Game Finished",
                    JOptionPane.INFORMATION_MESSAGE);
            flushButton.setEnabled(false);
            dontFlushButton.setEnabled(false);
            itemLabel.setText("Play Again Soon!");
        } else {
            itemLabel.setText(items.get(currentIndex));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToiletMiniGame().setVisible(true));
    }
}
