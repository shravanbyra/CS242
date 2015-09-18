import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class KingTest extends TestCase
{
    @Test
    public void testKingOutOfBoard() throws Exception
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
        board[7][7].setPiece(new King(white));
        assertEquals(board[7][7].getPiece().validateMove(board, 7, 7, 8, 8), false);
    }

    @Test
    public void testValidateMoveKingValid1() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), true);
    }

    //down
    @Test
    public void testValidateMoveKingValid2() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 5), true);
    }

    //left
    @Test
    public void testValidateMoveKingValid3() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 4), true);
    }

    //right
    @Test
    public void testValidateMoveKingValid4() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 4), true);
    }

    //Up Left
    @Test
    public void testValidateMoveKingValid5() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 3), true);
    }

    //up right
    @Test
    public void testValidateMoveKingValid16() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 3), true);
    }

    //down left
    @Test
    public void testValidateMoveKingValid7() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 5), true);
    }

    //down right
    @Test
    public void testValidateMoveKingValid8() throws Exception
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
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 5), true);
    }

    @Test
    public void testKingKillOwnPiece() throws Exception
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
        board[2][2].setPiece(new King(white));
        board[1][1].setPiece(new Bishop(white));
        assertEquals(board[2][2].getPiece().validateMove(board, 2, 2, 1, 1), false);
    }
}