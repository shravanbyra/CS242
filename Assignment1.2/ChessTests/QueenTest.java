import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class QueenTest extends TestCase
{
    @Test
    public void testQueenOutOfBoard() throws Exception
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
        board[7][7].setPiece(new Queen(white));
        assertEquals(board[7][7].getPiece().validateMove(board, 7, 7, 8, 8), false);
    }

    @Test
    public void testValidateMoveQueenDiagValid() throws Exception
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
        board[4][4].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 1), true);
    }

    @Test
    public void testValidateMoveQueenHorizValid() throws Exception
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
        board[4][4].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 1), true);
    }

    @Test
    public void testValidateMoveQueenInvalid() throws Exception
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
        board[4][4].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 2), false);
    }

    @Test
    public void testValidateMoveQueenPieceInbetweenInvalid() throws Exception
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
        board[4][4].setPiece(new Queen(white));
        board[2][2].setPiece(new Queen(black));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 1), false);
    }

    @Test
    public void testQueenKillOwnPiece() throws Exception
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
        board[4][4].setPiece(new Queen(white));
        board[1][1].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 1), false);
    }
}