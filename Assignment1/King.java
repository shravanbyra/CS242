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
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        if(super.validateMove(board, currX, toX, currY, toY) == false)
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
        return false;

    }

}
