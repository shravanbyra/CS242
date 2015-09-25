/**
 * Created by ShravanB on 9/9/2015.
 */
public abstract class Piece
{
    public static final int PAWN = 0;
    public static final int ROOK = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;
    public static final int EXECUTIONER = 5;
    public static final int JACK = 6;

    private int type;
    private Player player;
    public Piece(Player p, int type)
    {
        this.type = type;
        this.player = p;
    }
    public boolean validateMove(Square[][] board, int currX, int currY, int toX, int toY)
    {
        //no piece = no move
        if(board[currX][currY] == null)
        {
            return false;
        }


        //Piece stays in board  and actually moves (cannot move to same spot)
        if((toX< 0 || toX > board.length - 1) || (toY < 0 || toY > board.length - 1) || (toX == currX && toY == currY))
        {
            return false;
        }

        return true;
    }
    public int getType()
    {
        return type;
    }
    public Player getPlayer()
    {
        return player;
    }





}
