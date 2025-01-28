package com.teamfourtyone;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Tileable {
    private Node tileImage;

    public Tileable() { }

    public Tileable(String imageID) {
        ImageView imageView = new ImageView(new Image(App.class.getResourceAsStream(imageID)));
        imageView.setFitHeight(App.TILESIZE);
        imageView.setFitWidth(App.TILESIZE);
        tileImage = imageView;
    }

    public Node getTileImage() {
        return tileImage;
    }

    public void setTileImage(Node tileImage) {
        this.tileImage = tileImage;
    }
}
