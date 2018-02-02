package gui.model;

import gui.FrontendController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public abstract class BottomOptionView {

    protected Button customButton;
    protected FrontendController frontendController;
    private BorderPane root;

    public BottomOptionView(Button customButton, FrontendController frontendController, BorderPane root) {
        this.customButton = customButton;
        this.frontendController = frontendController;
        this.root = root;
    }

    protected void setCustomButtonStyle() {
        customButton.setPadding(new Insets(10, 10, 10, 10));
    }

    protected abstract void setAction();

    protected void composeButtonPane() {
        HBox buttonPane = new HBox(1, customButton);
        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        buttonPane.setFillHeight(false);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(buttonPane);
    }

    public void composeOptionView() {
        composeButtonPane();
        setCustomButtonStyle();
        setAction();
    }
}
