/**
 * Created by ShravanB on 9/11/2015.
 */
public class Knight extends Piece
{
    public Knight(Player p)
    {
        super(p, Piece.KNIGHT);
    }

    @Override
    public boolean validateMove(Square[][] board, int currX, int currY, int toX, int toY)
    {
        if(super.validateMove(board,  currX, currY, toX, toY) == false)
        {
            return false;
        }

        //Can't kill own piece
        if(board[toX][toY].getPiece() != null && board[toX][toY].getPiece().getPlayer().getColor() == super.getPlayer().getColor())
        {
            return false;
        }

        return Math.abs(currX - toX) * Math.abs(currY - toY) == 2; //x movement * y movement should equal 2
    }

}

