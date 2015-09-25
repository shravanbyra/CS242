/**
 * Created by ShravanB on 9/11/2015.
 */
public class Game
{
    public static final int SIZE = 8;
    public static final int NUM_PIECES = 16;
    private Player black;
    private Player white;
    private Square[][] board;
    private boolean whiteTurn;

    public Game()
    {
        board = new Square[SIZE][SIZE];

        black = new Player(Player.BLACK);
        black.initPieceTracker();

        white = new Player(Player.WHITE);
        white.initPieceTracker();
        whiteTurn = true;
        initializePieces();


    }

    public void initializePieces()
    {
        //use pieceTracker to initialize board

        //white pieces
        int[][] tempPieceTracker = white.getPieceTracker();
        for(int i = 0; i < 16; i++)
        {
            if(tempPieceTracker[i][0] == Piece.PAWN)
            {
                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Pawn(white));
            }
            else if(tempPieceTracker[i][0] == Piece.KNIGHT)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Knight(white));
            }
            else if(tempPieceTracker[i][0] == Piece.BISHOP)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Bishop(white));
            }
            else if(tempPieceTracker[i][0] == Piece.ROOK)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Rook(white));
            }
            else if(tempPieceTracker[i][0] == Piece.QUEEN)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Queen(white));
            }
            else if(tempPieceTracker[i][0] == Piece.KING)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new King(white));
            }
        }

        //black pieces
        tempPieceTracker = black.getPieceTracker();
        for(int i = 0; i < 16; i++)
        {
            if(tempPieceTracker[i][0] == Piece.PAWN)
            {
                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Pawn(black));
            }
            else if(tempPieceTracker[i][0] == Piece.KNIGHT)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Knight(black));
            }
            else if(tempPieceTracker[i][0] == Piece.BISHOP)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Bishop(black));
            }
            else if(tempPieceTracker[i][0] == Piece.ROOK)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Rook(black));
            }
            else if(tempPieceTracker[i][0] == Piece.QUEEN)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new Queen(black));
            }
            else if(tempPieceTracker[i][0] == Piece.KING)
            {

                board[tempPieceTracker[i][1]][tempPieceTracker[i][2]] = new Square(new King(black));
            }
        }
    }

    public boolean movePiece(Player p, int currX, int currY, int toX, int toY)
    {
        Piece piece = board[currX][currY].getPiece();

        //piece you can move
        if(!piece.getPlayer().equals(p) || piece == null)
        {
            return false;
        }

        //viable move
        if(!piece.validateMove(board, currX, toX, currY, toY)) //
        {
            return false;
        }

        //is destination empty or kill piece
        if(board[toX][toY].getPiece() == null)
        {
            //update location on board and pieceTracker
            Piece tempPiece = board[currX][currY].getPiece();


            //pieceTracker
            if(p.getColor() == Player.WHITE)
            {
                white.setPiece(currX, currY, toX, toY);
            }
            else
            {
                black.setPiece(currX, currY, toX, toY);
            }
            board[toX][toY].setPiece(tempPiece);

            //make sure old location is empty
            board[currX][currY].setPiece(null);

            return true;
        }

        //kill piece
        if(board[toX][toY].getPiece() != null)
        {
            //kill piece
            Piece tempPieceTarget = board[toX][toY].getPiece();
            if(tempPieceTarget.getPlayer().getColor() == Player.WHITE)  //pieceTracker
            {
                white.killPiece(toX, toY);
            }
            else
            {
                black.killPiece(toX,toY);
            }

            board[toX][toY].setPiece(board[currX][currY].getPiece());   //kill piece on board
            board[currX][currY].setPiece(null);                         //remove piece from current location

            return true;
        }

        return false;

    }

    public boolean checkCheck(Player p)
    {
        int kingX = p.getKingX();
        int kingY = p.getKingY();

        //check all piece that aren't p's and see if they can move to p's King location
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(board[i][j].getPiece() != null && board[i][j].getPiece().getPlayer() != p)
                {
                    if(board[i][j].getPiece().validateMove(board, i, kingX, j, kingY))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //player p can't make a valid move
    public boolean checkStalemate(Player p)
    {
        int kingX = p.getKingX();
        int kingY = p.getKingY();

        //first check where king can move (regardless of enemy pieces)
        boolean[] kingPossibleMoves = new boolean[8];

        kingPossibleMoves[0] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX - 1, kingY, kingY -1 ); //top left
        kingPossibleMoves[1] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX - 1, kingY, kingY);     //left
        kingPossibleMoves[2] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX - 1, kingY, kingY + 1); //bottom left
        kingPossibleMoves[3] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX, kingY, kingY - 1);    //up
        kingPossibleMoves[4] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX, kingY, kingY + 1);    //down
        kingPossibleMoves[5] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX + 1, kingY, kingY - 1); //top right
        kingPossibleMoves[6] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX + 1, kingY, kingY);     //right
        kingPossibleMoves[7] = board[kingX][kingY].getPiece().validateMove(board, kingX, kingX + 1, kingY, kingY + 1); //bottom right


        //check all piece that aren't p's and see if they can move to any position the king can move to
        //update kingPossibleMoves accordingly
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(board[i][j].getPiece() != null && board[i][j].getPiece().getPlayer() != p)
                {
                    kingPossibleMoves[0] = (kingPossibleMoves[0] && board[i][j].getPiece().validateMove(board, i, kingX - 1 , j, kingY - 1)); //top left
                    kingPossibleMoves[1] = (kingPossibleMoves[1] && board[i][j].getPiece().validateMove(board, i, kingX - 1 , j, kingY));     //left
                    kingPossibleMoves[2] = (kingPossibleMoves[2] && board[i][j].getPiece().validateMove(board, i, kingX - 1 , j, kingY + 1)); //bottom left
                    kingPossibleMoves[3] = (kingPossibleMoves[3] && board[i][j].getPiece().validateMove(board, i, kingX, j, kingY - 1));      //up
                    kingPossibleMoves[4] = (kingPossibleMoves[4] && board[i][j].getPiece().validateMove(board, i, kingX, j, kingY - 1));      //down
                    kingPossibleMoves[5] = (kingPossibleMoves[5] && board[i][j].getPiece().validateMove(board, i, kingX + 1 , j, kingY - 1)); //top right
                    kingPossibleMoves[6] = (kingPossibleMoves[6] && board[i][j].getPiece().validateMove(board, i, kingX + 1 , j, kingY));     //right
                    kingPossibleMoves[7] = (kingPossibleMoves[7] && board[i][j].getPiece().validateMove(board, i, kingX + 1 , j, kingY + 1)); //bottom right
                }
            }
        }

        //if at least one of kingPossibleMoves is true return false, else return true
        for(int i = 0; i < kingPossibleMoves.length; i++)
        {
            if(kingPossibleMoves[i])
            {
                return false;
            }
        }

        return true;
    }

    //if the king is in stalemate and check it is checkmate
    public boolean checkCheckmate(Player p)
    {
        return checkCheck(p) && checkStalemate(p);
    }

    public Player getPlayer()
    {
        if(whiteTurn == true)
        {
            return white;
        }
        else
        {
            return black;
        }
    }

    public Square[][] getBoard()
    {
        return board;
    }

}
