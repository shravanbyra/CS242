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
                else if(Math.abs(currX - toX) == 1 )
                {
                    if(board[toX][toY].getPiece() != null)
                    {
                        return board[toX][toY].getPiece().getPlayer().getColor() == Player.BLACK;
                    }
                    return false;
                }
            }
            return false;
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
                else if(Math.abs(currX - toX) == 1 )
                {
                    if(board[toX][toY].getPiece() != null)
                    {
                        return board[toX][toY].getPiece().getPlayer().getColor() == Player.WHITE;
                    }
                }


            }
            return false;
        }

        return false;
    }

}
