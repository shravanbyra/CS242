/**
 * Created by ShravanB on 9/11/2015.
 */
public class Player
{
    public static final int WHITE = 1;
    public static final int BLACK = 0;

    private int[][] pieceTracker;
    private int color;

    public Player(int color)
    {
        this.color = color;
        pieceTracker = new int[Game.SIZE*2][3];
    }

    public int getColor()
    {
        return color;
    }

    public void initPieceTracker()
    {

        //Pawns
        for(int i = 0; i < 8; i++)
        {
            pieceTracker[i][0] = Piece.PAWN;
            pieceTracker[i][1] = color*6 + Math.abs(color-1);        //6th row if white, 1st if black
            pieceTracker[i][2] = i;
        }
        //Rooks
        pieceTracker[8][0] = Piece.ROOK;
        pieceTracker[8][1] = 0;
        pieceTracker[8][2] = color*7;                                //7th row if white, 0th if black
        pieceTracker[9][0] = Piece.ROOK;
        pieceTracker[9][1] = 7;
        pieceTracker[9][2] = color*7;

        //Bishops
        pieceTracker[10][0] = Piece.BISHOP;
        pieceTracker[10][1] = 1;
        pieceTracker[10][2] = color*7;
        pieceTracker[11][0] = Piece.BISHOP;
        pieceTracker[11][1] = 6;
        pieceTracker[11][2] = color*7;

        //Knights
        pieceTracker[12][0] = Piece.KNIGHT;
        pieceTracker[12][1] = 2;
        pieceTracker[12][2] = color*7;
        pieceTracker[13][0] = Piece.KNIGHT;
        pieceTracker[13][1] = 5;
        pieceTracker[13][2] = color*7;

        //Queen
        pieceTracker[14][0] = Piece.QUEEN;
        pieceTracker[14][1] = 3 + color;                             //3rd column if white, 4th if black
        pieceTracker[14][2] = color*7;

        //King
        pieceTracker[15][0] = Piece.KING;
        pieceTracker[15][1] = 4 - color;                             //4th column if white, 3rd if black
        pieceTracker[15][2] = color*7;

    }

    public int[][] getPieceTracker()
    {
        return pieceTracker;
    }

    //update piece tracker with given move information
    public void setPiece(int currX, int currY, int toX, int toY)
    {
        for(int i = 0; i < pieceTracker.length; i++)
        {
            if(pieceTracker[i][1] == currX && pieceTracker[i][2] == currY)
            {
                pieceTracker[i][1] = toX;
                pieceTracker[i][2] = toY;
            }
        }
    }


    //update piece tracker with all -1's
    public void killPiece(int currX, int currY)
    {
        for(int i = 0; i < pieceTracker.length; i++)
        {
            if(pieceTracker[i][1] == currX && pieceTracker[i][2] == currY)
            {
                pieceTracker[i][0] = -1;
                pieceTracker[i][1] = -1;
                pieceTracker[i][2] = -1;
            }
        }
    }


    //return King xCoordinate
    public int getKingX()
    {
        return pieceTracker[15][1];
    }

    //return King yCoordinate
    public int getKingY()
    {
        return pieceTracker[15][2];
    }


}
