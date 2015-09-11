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

    private int type;
    private Player player;
    public Piece(Player p, int type)
    {
        this.type = type;
        this.player = p;
    }
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        //Piece stays in board  and actually moves (cannot move to same spot)
        if((toX< 0 || toX > board.length) || (toY < 0 || toY > board.length) || (toX == currX && toY == currY))
        {
            return false;
        }

        //Can't kill own piece
        if(board[toX][toY].getPiece().getPlayer().getColor() == player.getColor())
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
