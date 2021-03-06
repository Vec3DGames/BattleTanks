package xyz.vec3d.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

import xyz.vec3d.game.model.Entity;

/**
 * Created by darakelian on 6/30/2016.
 * Copyright vec3d.xyz 2016
 * All rights reserved
 *
 * Utils class provides static methods for various repeat functionality such as
 * math functions.
 */
public class Utils {

    /**
     * The singleton random generator to use for all random number generations.
     */
    private static final Random random = new Random();

    /**
     * Gets the x coordinate to draw an object at when being centered in a container
     * in which point 0,0 is the lower left corner of the object.
     *
     * @param objectWidth The width of the object being centered.
     * @param containerWidth The width of the container that object is centered in.
     *
     * @return The x coordinate to draw the lower left corner of the object.
     *
     * @see Utils#getPosCenterX(float, float, float)
     */
    public static float getPosCenterX(float objectWidth, float containerWidth) {
        return getPosCenterX(objectWidth, containerWidth, 0);
    }

    /**
     * Gets the x coordinate to draw an object at based on a given object width
     * and container width such that it is drawn in the center of the container.
     *
     * @param objectWidth The width of the object being centered.
     * @param containerWidth The width of the container that object is centered in.
     * @param containerPos The position of the container (0 if container is the screen).
     *
     * @return The x coordinate to draw the lower left corner of the object.
     */
    private static float getPosCenterX(float objectWidth, float containerWidth, float containerPos) {
        return getPosCenter(objectWidth, containerWidth, containerPos);
    }

    /**
     * Gets the y coordinate to draw an object at when being centered in a container
     * in which point 0,0 is the lower left corner of the object.
     *
     * @param objectHeight The height of the object being centered.
     * @param containerHeight The wight of the container that object is centered in.
     *
     * @return The y coordinate to draw the lower left corner of the object.
     *
     * @see Utils#getPosCenterY(float, float, float)
     */
    private static float getPosCenterY(float objectHeight, float containerHeight) {
        return getPosCenterY(objectHeight, containerHeight, 0);
    }

    /**
     * Gets the y coordinate to draw an object at based on a given object height
     * and container height such that it is drawn in the center of the container.
     *
     * @param objectHeight The height of the object being centered.
     * @param containerHeight The height of the container that object is centered in.
     * @param containerPos The position of the container (0 if the container is the screen).
     *
     * @return The y coordinate to draw the lower left corner of the object.
     */
    private static float getPosCenterY(float objectHeight, float containerHeight, float containerPos) {
        return getPosCenter(objectHeight, containerHeight, containerPos);
    }

    /**
     * Performs the actual calculation to determine the coordinate that will draw
     * the object centered in the container. Private because this method should
     * not be called be anyone other than the wrapper methods in the Utils class.
     *
     * @param objectDimension Dimension of the object being centered (either width/height).
     * @param containerDimension Dimension of the container (either width/height).
     * @param containerPos Starting x or y coordinate of the container.
     *
     * @return The coordinate to draw the lower left corner of the object.
     */
    private static float getPosCenter(float objectDimension, float containerDimension,
                                      float containerPos) {
        return containerPos + ((containerDimension - objectDimension) / 2);
    }

    public static void centerCamera(OrthographicCamera worldCamera, Entity entity, float mapWidth,
                                    float mapHeight) {
        float camViewportHalfX = worldCamera.viewportWidth / 2;
        float camViewportHalfY = worldCamera.viewportHeight / 2;
        worldCamera.position.x = entity.getX();
        worldCamera.position.y = entity.getY();
        //Clamp camera first on x, then on y.
        worldCamera.position.x = MathUtils.clamp(worldCamera.position.x,
                camViewportHalfX, mapWidth - camViewportHalfX);
        worldCamera.position.y = MathUtils.clamp(worldCamera.position.y,
                camViewportHalfY, mapHeight - camViewportHalfY);
    }
}
