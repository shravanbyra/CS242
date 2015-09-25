import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class JackTest extends TestCase
{
    @Test
    public void testJackOutOfBoard() throws Exception
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
        board[7][7].setPiece(new Jack(white));
        assertEquals(board[7][7].getPiece().validateMove(board, 7, 7, 8, 8), false);
    }

    @Test
    public void testValidateMoveJackInvalid() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 2, 2), false);
    }

    //up
    @Test
    public void testValidateMoveJackValid1() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), true);
    }

    //down
    @Test
    public void testValidateMoveJackValid2() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 5), true);
    }

    //left
    @Test
    public void testValidateMoveJackValid3() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 4), true);
    }

    //right
    @Test
    public void testValidateMoveJackValid4() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 4), true);
    }

    //Up Left
    @Test
    public void testValidateMoveJackValid5() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 3), true);
    }

    //up right
    @Test
    public void testValidateMoveJackValid16() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 3), true);
    }

    //down left
    @Test
    public void testValidateMoveJackValid7() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 5), true);
    }

    //down right
    @Test
    public void testValidateMoveJackValid8() throws Exception
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
        board[4][4].setPiece(new Jack(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 5, 5), true);
    }

    @Test
    public void testJackSwapOwnPiece() throws Exception
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
        board[2][2].setPiece(new Jack(white));
        board[1][1].setPiece(new Bishop(white));
        assertEquals(board[2][2].getPiece().validateMove(board, 2, 2, 1, 1), true);
    }

    @Test
    public void testJackSwapOppPiece() throws Exception
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
        board[2][2].setPiece(new Jack(white));
        board[1][1].setPiece(new Bishop(black));
        assertEquals(board[2][2].getPiece().validateMove(board, 2, 2, 4, 4), false);
    }

    @Test
    public void testJackKillOppPiece() throws Exception
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
        board[2][2].setPiece(new Jack(white));
        board[1][1].setPiece(new Bishop(black));
        assertEquals(board[2][2].getPiece().validateMove(board, 2, 2, 1, 1), true);
    }
}