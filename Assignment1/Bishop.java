/**
 * Created by ShravanB on 9/11/2015.
 */
public class Bishop extends Piece
{


    public Bishop(Player p)
    {
        super(p, Piece.BISHOP);
    }

    @Override
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        if(super.validateMove(board, currX, toX, currY, toY) == false)
        {
            return false;
        }

        //diagonal movement
        if(Math.abs(currX - toX) == Math.abs(currY - toY))
        {
            //Check if pieces are in between
            for(int i = 1; i < Math.abs(toX - currX); i++)
            {
                //up and left
                if(toY < currY && toX < currX)
                {
                    if(board[currX - i][currY - i].getPiece() != null)
                    {
                        return false;
                    }
                }

                //down and left
                if(toY > currY && toX < currX)
                {
                    if(board[currX - i][currY + i].getPiece() != null)
                    {
                        return false;
                    }
                }

                //up and right
                if(toY < currY && toX > currX)
                {
                    if(board[currX + i][currY - i].getPiece() != null)
                    {
                        return false;
                    }
                }

                //down and right
                if(toY > currY && toX > currX)
                {
                    if(board[currX + i][currY + i].getPiece() != null)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}
