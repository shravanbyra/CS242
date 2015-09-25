/**
 * Created by ShravanB on 9/18/2015.
 */
public class Jack extends Piece
{

    //Can swap with any of same color pieces in 2 square radius of itself
    //Same moves as king otherwise
    public Jack(Player p)
    {
        super(p, Piece.JACK);
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
            return (Math.abs(currX - toX) <= 2 && Math.abs(currY - toY) <=2);
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
        //up & down
        if(currX == toX && Math.abs(currY - toY) == 1)
        {
            return true;
        }
        return false;

    }
}
