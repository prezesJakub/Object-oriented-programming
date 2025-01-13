package agh.ics.oop.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class WorldElementBox extends VBox {
    private final WorldElement element;
    private final ImageView imageView;

    public WorldElementBox(WorldElement element) {
        this.element=element;

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/" + element.getResourceName())));

        this.imageView = new ImageView(image);

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Label positionLabel = new Label(element.getPosition().toString());

        this.getChildren().addAll(imageView, positionLabel);
        this.setAlignment(Pos.CENTER);
    }

    public WorldElement getElement() {
        return this.element;
    }

}
