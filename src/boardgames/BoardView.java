package boardgames;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BoardView extends Canvas {

    double squareSize;

    public BoardView(Board aBoard, double width, double height){
        super(width, height);
        aBoard.addListener( (board) -> redraw(board) );
        redraw(aBoard);
    }

    public void redraw(Object aBoard){
        Board board = (Board)aBoard;
        squareSize = getWidth() / board.size();
        for(int rankIndex = 0; rankIndex < board.size(); ++rankIndex){
            for(int fileIndex = 0; fileIndex < board.size(); ++fileIndex){
                Coordinate aCoord = new Coordinate(fileIndex, rankIndex);
                drawSquareAt(board, aCoord);
            }
        }
    }

    public void drawSquareAt(Board board, Coordinate aCoord){
        if(aCoord == null)
        {
            return;
        }
        Square aSquare = board.getSquare(aCoord);
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(aSquare.getColor());
        double x = aCoord.fileIndex * squareSize;
        double y = (board.size() - aCoord.rankIndex - 1) * squareSize;
        gc.fillRect(x, y, squareSize, squareSize);
        if(!aSquare.isEmpty()) {
            Image image = aSquare.getPiece().getImage();
            gc.drawImage(image, x, y, squareSize, squareSize);
        }
    } 
    
}
