import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class ChessGUI extends JFrame
{

    private JPanel GameBoard;
    private JPanel GameRecord;
    private JPanel MainPanel;
    private JButton squares[][];

    public ChessGUI()
    {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        GameBoard = new JPanel();
        GameRecord = new JPanel();
        squares = new JButton[8][8];
        initializeGameBoard();


    }

    private void initializeGameBoard()
    {
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j++)
            {
                squares[i][j] = new JButton();
                if(i+j%2 == 1)
                {
                    squares[i][j].setBackground(Color.WHITE);
                }
                else
                {
                    squares[i][j].setBackground(Color.RED);
                }
                GameBoard.add(squares[i][j]);
            }
        }
    }
}
