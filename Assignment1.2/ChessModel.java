/**
 * Created by ShravanB on 9/25/2015.
 */
public class ChessModel
{
    String white;
    String black;
    int whiteScore;
    int blackScore;
    Game chessGame;

    public ChessModel(String p1, String p2)
    {
        white = p1;
        black = p2;
        whiteScore = 0;
        blackScore = 0;
        chessGame = new Game();
    }

    public void undo()
    {

    }

    public Square[][] sendBoard()
    {
        return chessGame.getBoard();
    }

    public void move(int currX, int currY, int toX, int toY )
    {
        if(chessGame.getPlayer() == chessGame.getBoard()[currX][currY].getPiece().getPlayer())
        {
            chessGame.movePiece(chessGame.getPlayer(), currX, currY, toX, toY);
        }
    }
}
