package xyz.vec3d.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daron on 11/5/2017.
 * Copyright vec3d.xyz 2017
 * All rights reserved
 *
 * Collection point for all of the entities. Handles updating stuff here.
 */

public class World {

    private List<Entity> entities;

    public World() {
        entities = new ArrayList<>();
    }

    public void renderAndUpdate(float delta) {

    }
}
