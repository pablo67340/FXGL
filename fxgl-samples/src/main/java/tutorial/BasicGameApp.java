/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package tutorial;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * This is a basic FXGL game application tutorial.
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class BasicGameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
        settings.setProfilingEnabled(false);  // turn off fps
        settings.setCloseConfirmation(false); // turn off exit dialog
        settings.setIntroEnabled(false);      // turn off intro
        settings.setMenuEnabled(false);       // turn off menus
    }

    @Override
    protected void initInput() {
        Input input = getInput(); // get input service

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.translateX(5); // move right 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translateX(-5); // move left 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-5); // move up 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translateY(5); // move down 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.S);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    private GameEntity player;

    @Override
    protected void initGame() {
        player = Entities.builder()
                .at(300, 300)
                .viewFromNode(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());

        getGameScene().addUINode(textPixels); // add to the scene graph
    }

    public static void main(String[] args) {
        launch(args);
    }
}
