import javafx.application.Application;
import boardgames.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class App extends Application{

    private Board board = new Board(6, Color.DARKGREEN, Color.BEIGE);
    private PuzzleSolver solver = new KnightsTourSolver(board, new Coordinate(0, 0));
    BoardView boardView;
    private ListView<Move> moveListView;
    private int nextStep = 0;
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage aStage) throws Exception {
        solver.solve();
        board.clearBoard();
        Scene aScene = new Scene(buildClientArea(), 550, 400);
        aStage.setScene(aScene);
        aStage.setTitle("Chess Puzzles");
        aStage.show();
    }

    private VBox buildClientArea(){
        VBox root = new VBox();
        // add a menu bar
        root.getChildren().add(buildBoardAndMoveList());
        // add control button
        return root;
    }

    private HBox buildBoardAndMoveList(){
        HBox row = new HBox();
        moveListView = new ListView<Move>(solver.getMoves());
        row.getChildren().add(buildBoardAndButtons());
        row.getChildren().add(moveListView);
        return row;
    }

    private VBox buildBoardAndButtons(){
        VBox column = new VBox();
        boardView = new BoardView(solver.getBoard(), 320, 320);
        column.getChildren().add(boardView);
        column.getChildren().add(buildButtonRow());
        return column;
    }

    private HBox buildButtonRow(){
        HBox buttons = new HBox();
        Button resetButton = new Button("reset");
        Button stepButton = new Button("step");
        resetButton.setOnAction( (event) -> resetGame() );
        stepButton.setOnAction( (event) -> handleStep() );
        buttons.getChildren().add(resetButton);
        buttons.getChildren().add(stepButton);
        return buttons;
    }

    private void resetGame() {
        board.clearBoard();
        nextStep = 0;
    }

    private void handleStep() {
        if(nextStep == solver.getMoves().size()){
            return;
        }
        Move aMove = solver.getMoves().get(nextStep++);
        aMove.doOn(board);
        
        boardView.drawSquareAt(board, aMove.getStart());
        boardView.drawSquareAt(board, aMove.getEnd());
    }
}
