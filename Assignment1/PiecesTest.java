/**
 * Created by ShravanB on 9/11/2015.
 */

import junit.framework.TestCase;
import org.junit.Test;


public class PiecesTest extends TestCase
{

    @Test
    public void testValidateMovePawnForwardInvalid()
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Pawn(white));
        board[4][3].setPiece(new Pawn(white));
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
                board[i][j] = new Square(null);
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
        board[4][4].setPiece(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 3), true);
    }

    @Test
    public void testValidateMovePawnInvalidMovement() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Pawn(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 2, 2), false);
    }

    @Test
    public void testValidateMoveRookInvalidMovement() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 2, 2), false);

    }

    @Test
    public void testValidateMoveRookForwardValid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 3), true);

    }

    @Test
    public void testValidateMoveRookForwardInvalid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Rook(white));
        board[4][3].setPiece(new Rook(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 4, 2), false);

    }

    @Test
    public void testValidateMoveKnightValid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Knight(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 2), true);
    }

    @Test
    public void testValidateMoveKnightInvalid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Knight(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 1), false);
    }

    @Test
    public void testValidateMoveQueenValid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 1), true);
    }

    @Test
    public void testValidateMoveQueenInvalid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Queen(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 2), false);
    }

    @Test
    public void testValidateMoveQueenPieceInbetweenInvalid() throws Exception
    {
        Player black = new Player(Player.BLACK);
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new Queen(white));
        board[2][2].setPiece(new Queen(black));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 1, 1), false);
    }


    @Test
    public void testValidateMoveKingValid() throws Exception
    {
        Player white = new Player(Player.WHITE);
        Square[][] board = new Square[8][8];
        board[4][4].setPiece(new King(white));
        assertEquals(board[4][4].getPiece().validateMove(board, 4, 4, 3, 3), true);
    }
}