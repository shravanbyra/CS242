/**
 * Created by ShravanB on 9/11/2015.
 */
public class Pawn extends Piece
{


    public Pawn(Player p)
    {
        super(p, Piece.PAWN);
    }

    @Override
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        if(super.validateMove(board,  currX,  toX,  currY,  toY) == false)
        {
            return false;
        }

        if(getPlayer().getColor() == Player.WHITE)
        {
            //Moving forward
            if(currY - toY == 1)
            {
                //Moving straight
                if(currX == toX)
                {
                    return board[toX][toY].getPiece() == null;
                }
            }
            return true;
        }
        else if(getPlayer().getColor() == Player.BLACK)
        {
            //Moving forward
            if(currY - toY == -1)
            {
                //Moving straight
                if(currX == toX)
                {
                    return board[toX][toY].getPiece() == null;
                }

            }
            return true;
        }

        return false;
    }

}
