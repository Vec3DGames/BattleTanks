package xyz.vec3d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Daron on 11/5/2017.
 * Copyright vec3d.xyz 2016
 * All rights reserved
 */

class MenuScreen implements Screen {

    private Stage uiStage;

    /**
     * The background color constant for the menu screen. Ignore this for when
     * there is a proper background image to draw.
     */
    private static final float BACK_COLOR = 63f/255f;

    MenuScreen(final MainGame mainGame) {
        uiStage = new Stage(new StretchViewport(800, 480));
        Gdx.input.setInputProcessor(uiStage);
        Skin skin = MainGame.getAsset("uiskin.json");

        //Set up UI components here.
        Table uiTable = new Table(skin);
        Label label = new Label("Menu", skin, "default");
        //Play button
        TextButton playButton = new TextButton("Play", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new GameScreen(mainGame));
            }
        });
        //Stats button
        TextButton statsButton = new TextButton("Stats", skin);
        //Options button
        TextButton optionsButton = new TextButton("Options", skin);
        //Exit button
        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        uiTable.add(label).padBottom(40).row();
        uiTable.add(playButton).width(200).padBottom(40).row();
        uiTable.add(statsButton).width(200).padBottom(40).row();
        uiTable.add(optionsButton).width(200).padBottom(40).row();
        uiTable.add(exitButton).width(200);
        uiTable.setFillParent(true);

        //Add UI components to stage.
        uiStage.addActor(uiTable);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(BACK_COLOR, BACK_COLOR, BACK_COLOR, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.draw();
        uiStage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
