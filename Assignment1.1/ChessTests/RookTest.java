import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class RookTest extends TestCase
{
    @Test
    public void testRookOutOfBoard() throws Exception
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
        board[4][4].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 9), false);
    }

    @Test
    public void testValidateMoveRookInvalidMovement() throws Exception
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
        board[4][4].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 2, 2), false);

    }

    @Test
    public void testValidateMoveRookForwardValid() throws Exception
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
        board[4][4].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), true);

    }

    @Test
    public void testValidateMoveRookForwardInvalid() throws Exception
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
        board[4][4].setPiece(new Rook(white));
        board[4][3].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 2), false);

    }

    public void testRookKillOwnPiece() throws Exception
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
        board[2][1].setPiece(new Rook(white));
        board[1][1].setPiece(new Bishop(white));
        assertEquals(board[2][1].getPiece().validateMove(board, 2, 1, 1, 1), false);
    }
}