/**
 * Created by ShravanB on 9/11/2015.
 */
public class King extends Piece
{


    public King(Player p)
    {
        super(p, Piece.KING);
    }

    @Override
    public boolean validateMove(Square[][] board, int currX, int currY, int toX, int toY)
    {
        if(super.validateMove(board, currX, currY, toX, toY) == false)
        {
            return false;
        }

        //Can't kill own piece
        if(board[toX][toY].getPiece() != null && board[toX][toY].getPiece().getPlayer().getColor() == super.getPlayer().getColor())
        {
            return false;
        }

        //left
        if(currX - toX == 1)
        {
            //up
            if(currY - toY == 1)
            {
                return true;
            }

            //down
            if(currY - toY == -1)
            {
                return true;
            }

            //straight
            if(currY == toY)
            {
                return true;
            }
        }
        //right
        if(currX - toX == -1)
        {
            //up
            if(currY - toY == 1)
            {
                return true;
            }

            //down
            if(currY - toY == -1)
            {
                return true;
            }

            //straight
            if(currY == toY)
            {
                return true;
            }
        }
        if(currX == toX && Math.abs(currY - toY) == 1)
        {
            return true;
        }
        return false;

    }

}
