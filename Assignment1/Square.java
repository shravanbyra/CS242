/**
 * Created by ShravanB on 9/11/2015.
 */
public class Square
{
    private Piece piece;

    public Square(Piece p)
    {
        piece = p; //null if no piece
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void setPiece(Piece newPiece)
    {

        /*
        ****USE IN GAME****
        if(piece != null)
        {
            Player.killPiece();
        }
        */

        piece = newPiece;
    }

}
