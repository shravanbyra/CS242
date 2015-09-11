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
    public boolean validateMove(Square[][] board, int currX, int toX, int currY, int toY)
    {
        if(super.validateMove(board,  currX,  toX,  currY,  toY) == false)
        {
            return false;
        }

        return Math.abs(currX - toX) * Math.abs(currY - toY) == 2; //x movement * y movement should equal 2
    }

}

