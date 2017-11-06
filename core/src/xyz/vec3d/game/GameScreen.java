package xyz.vec3d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import xyz.vec3d.game.model.World;
import xyz.vec3d.game.utils.Utils;

/**
 * Created by Daron on 11/5/2017.
 * Copyright vec3d.xyz 2016
 * All rights reserved
 */

class GameScreen implements Screen {

    /**
     * The {@link Stage} instance for UI.
     */
    private Stage uiStage;

    /**
     * The {@link TiledMapRenderer} responsible for drawing the world's map.
     */
    private TiledMapRenderer tiledMapRenderer;

    /**
     * The {@link OrthographicCamera} responsible for looking into the world map.
     */
    private OrthographicCamera worldCamera;

    /**
     * Width of the map in world units.
     */
    private int mapWidth;

    /**
     * Height of the map in world units.
     */
    private int mapHeight;

    private SpriteBatch batch;
    private BitmapFont font;

    private World world;

    GameScreen(MainGame mainGame) {
        batch = new SpriteBatch();
        font = new BitmapFont();

        TiledMap map = MainGame.getAsset("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1/32f);
        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(false, 25, 14);
        worldCamera.update();

        //Set up map and camera viewport properties.
        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Utils.centerCamera(worldCamera, null, mapWidth, mapHeight);
        worldCamera.update();

        tiledMapRenderer.setView(worldCamera);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(worldCamera.combined);
        batch.begin();

        world.renderAndUpdate(delta);

        batch.end();

        uiStage.act(delta);
        uiStage.getBatch().setColor(Color.WHITE);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
