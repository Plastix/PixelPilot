package com.mygdx.pixelpilot.game;

import com.artemis.EntityFactory;
import com.artemis.annotations.Bind;
import com.artemis.annotations.UseSetter;
import com.mygdx.pixelpilot.game.component.*;

@Bind({Sprite2D.class, Cullable.class, Position.class, Rotation.class,
        Velocity.class, Size.class, Health.class, PlaneDefinition.class, Renderable.class})
public interface Plane extends EntityFactory<Plane> {
    Plane position(float x, float y);

    @Bind(Sprite2D.class)
    @UseSetter
    Plane setPath(String path);

    Plane health(float health);
    Plane size(float width, float height);

    @Bind(Velocity.class)
    @UseSetter("setSpeed")
    Plane speed(float speed);
}
