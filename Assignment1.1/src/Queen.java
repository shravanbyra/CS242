/**
 * Created by ShravanB on 9/11/2015.
 */
public class Queen extends Piece
{


    public Queen(Player p)
    {
        super(p, Piece.QUEEN);
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
