import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class KnightTest extends TestCase
{
    @Test
    public void testKnightOutOfBoard() throws Exception
    {
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        for(int i = 0; i < 8; i++)
        {
            for( int j = 0; j < 8; j++)
            {
                board[i][j] = new Square();
            }
        }
        board[7][7].setPiece(new Knight(white));
        assertEquals(board[7][7].getPiece().validateMove(board, 7, 7, 8, 9), false);
    }

    @Test
    public void testValidateMoveKnightValid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        for(int i = 0; i < 8; i++)
        {
            for( int j = 0; j < 8; j++)
            {
                board[i][j] = new Square();
            }
        }
        board[4][4].setPiece(new Knight(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 2), true);
    }

    @Test
    public void testValidateMoveKnightInvalid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        for(int i = 0; i < 8; i++)
        {
            for( int j = 0; j < 8; j++)
            {
                board[i][j] = new Square();
            }
        }
        board[4][4].setPiece(new Knight(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 1), false);
    }

    public void testKnightKillOwnPiece() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        for(int i = 0; i < 8; i++)
        {
            for( int j = 0; j < 8; j++)
            {
                board[i][j] = new Square();
            }
        }
        board[2][3].setPiece(new Knight(white));
        board[1][1].setPiece(new Bishop(white));
        assertEquals(board[2][3].getPiece().validateMove(board, 2, 3, 1, 1), false);
    }
}