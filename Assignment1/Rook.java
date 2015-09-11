/**
 * Created by ShravanB on 9/11/2015.
 */
public class Rook extends Piece
{


    public Rook(Player p)
    {
        super(p, Piece.ROOK);
    }

    @Override
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        if(super.validateMove(board,  currX,  toX,  currY,  toY) == false)
        {
            return false;
        }

        //vertical movement
        if(toX == currX)
        {
            //Check if pieces are in between
            for(int i = 1; i < Math.abs(toY - currY); i++)
            {
                //up
                if(toY < currY)
                {
                    if(board[currX][currY - i].getPiece() != null)
                    {
                        return false;
                    }
                }

                //down
                if(toY > currY)
                {
                    if(board[currX][currY + i].getPiece() != null)
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        //horizontal movement
        if(toY == currY)
        {
            //Check if pieces are in between
            for(int i = 1; i < Math.abs(toX - toY); i++)
            {
                //left
                if(toX < currX)
                {
                    if(board[currX - 1][currY].getPiece() != null)
                    {
                        return false;
                    }
                }

                //right
                if(toX > currX)
                {
                    if(board[currX + 1][currY].getPiece() != null)
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
