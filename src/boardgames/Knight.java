package boardgames;

import javafx.scene.image.Image;

public class Knight extends Piece {

    String imageFileName = "knight.png";
    Image knightImage = new Image(imageFileName);

    @Override
    public String toString(){
        return "Kn";
    }
}
