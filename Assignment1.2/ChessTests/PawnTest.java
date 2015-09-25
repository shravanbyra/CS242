import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ShravanB on 9/18/2015.
 */
public class PawnTest extends TestCase
{
    public void testValidateMovePawnForwardInvalid()
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4] = new Square(new Pawn(white));
        board[4][3] = new Square(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), false);
    }

    //simultaneously checks whether you can capture enemy piece
    @Test
    public void testValidateMovePawnForwardValid() throws Exception
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
        board[4][4].setPiece(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), true);
    }

    //simultaneously checks whether you can capture own piece
    @Test
    public void testValidateMovePawnDiagInvalid() throws Exception
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
        board[4][4].setPiece(new Pawn(white));
        board[4][3].setPiece(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), false);
    }

    @Test
    public void testValidateMovePawnDiagValid() throws Exception
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
        board[4][4].setPiece(new Pawn(white));

        board[3][3].setPiece(new Pawn(black));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 3), true);
    }

    @Test
    public void testValidateMovePawnInvalidMovement() throws Exception
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
        board[4][4].setPiece(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 2, 2), false);
    }
}