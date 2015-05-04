package com.mygdx.pixelpilot.planeref;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.plane.PlaneDefinition;
import com.mygdx.pixelpilot.plane.SteerableActor;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.InstalledWeaponDefinition;
import com.mygdx.pixelpilot.plane.armaments.weapon.weapons.Weapon;
import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.screen.game.World;
import com.mygdx.pixelpilot.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Plane extends SteerableActor {
    private PlaneDefinition def;
    private ParticleEffect smoke;
    private ParticleEffect fire;
    private List<Weapon> weapons;
    private Controller controller;
    private boolean isOnFire;
    private Sprite sprite;
    private Sprite shadow;
    private World world;
    private int health;

    public Plane(PlaneDefinition def,
                 List<InstalledWeaponDefinition> weaponDefs,
                 Controller controller,
                 World world) {
        this.positionVector = new Vector2(0, 0);
        this.linearVelocity = new Vector2(1, 1);
        this.controller = controller;
        this.health = 100;
        this.world = world;
        this.def = def;
        controller.setPlane(this);
        initWeapons(weaponDefs);
        initParticles();
        initGraphics();

    }

    private void initWeapons(List<InstalledWeaponDefinition> weaponDefs) {
        // Create the weapons from the definitions
        weapons = new ArrayList<Weapon>();
        for (InstalledWeaponDefinition weaponDef : weaponDefs) {
            weapons.add(weaponDef.create(this));
        }
    }

    /**
     * No need to call effect.start() because these effects are continuous
     */
    private void initParticles() {
        //Create the plane trail particle smoke
        smoke = new ParticleEffect();
        smoke.load(Gdx.files.internal("data/particle/trail"), Gdx.files.internal("image"));

        //Create the fire particles
        fire = new ParticleEffect();
        fire.load(Gdx.files.internal("data/particle/fire"), Gdx.files.internal("image"));
        isOnFire = false;
    }

    private void initGraphics() {
        // set the main sprite
        sprite = new Sprite(new Texture(def.spritePath));
        sprite.setScale(3.5f, 3.5f);
        sprite.setRotation(-90);

        // set the shadow sprite
        shadow = new Sprite(sprite);
        shadow.setColor(new Color(0, 0, 0, 0.1f));
        shadow.setScale(1.5f, 1.5f);

        // set the size of the actor
        setSize(sprite.getWidth(), sprite.getHeight());
        setScale(sprite.getScaleX(), sprite.getScaleY());
        setOrigin(Align.center);
        setRotation(-90);
        sizeChanged();
    }

    private void updateParticleTrail() {
        ParticleEmitter emitter = smoke.findEmitter("trail");
        if (emitter != null) {
            //Darker smoke nearer to death
            float chan = Utils.map(health, 0, 100, 0.2f, 1);
            float[] colors = {chan, chan, chan};
            emitter.getTint().setColors(colors);

            //Makes the trails "puffier" nearer to death
            emitter.getScale().setHigh(Utils.map(health, 0, 100, 5, 20), Utils.map(health, 0, 100, 35, 20));
        }
        if (health < 15) {
            //Render the fire particle effect in draw
            isOnFire = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // keep the position vector in sync with the actor's position
        // (in case someone calls actor.setPosition)
        positionVector.set(getX(), getY());

        float prevAngle = linearVelocity.angleRad();
        controller.control();
        angularVel = linearVelocity.angleRad() - prevAngle;

        positionVector.add(linearVelocity);

        sprite.setPosition(positionVector.x - sprite.getWidth() / 2,
                positionVector.y - sprite.getHeight() / 2);

        shadow.setPosition(positionVector.x - shadow.getWidth() / 2,
                positionVector.y - 20 - shadow.getHeight() / 2);

        sprite.setRotation(getRotation());
        shadow.setRotation(getRotation());

        //Update the particle emitter
        smoke.setPosition(positionVector.x, positionVector.y);
        smoke.update(delta);

        if (isOnFire) {
            fire.setPosition(positionVector.x, positionVector.y);
            fire.update(delta);
        }

        // keep the actor's position in sync with the position vector
        setPosition(positionVector.x, positionVector.y);
    }

    /**
     * Scale the plane sprite and shadow when scaling the actor
     * Needed for scene2d actions!
     */
    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        sprite.setScale(scaleX, scaleY);

        //This is a really hacky fix so scaleTo actions set the right size of the shadow
        shadow.setScale(scaleX - 1.5f, scaleY - 1.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shadow.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);

        //Draw the emitter and its particles
        //Effects draw is overloaded but expects a delta time not a parelAlpha!
        //Do not pass parentAlpha into smoke's draw!
        smoke.draw(batch);
        if (isOnFire) {
            fire.draw(batch);
        }
    }

    /**
     * Causes the plane to change its heading
     *
     * @param turnAmount amount to turn this frame, expects to be in range [-1, 1]
     */
    public void turn(float turnAmount) {
        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * linearVelocity.len() / def.minTurnRadius;

        float turnAng = Utils.map(turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        linearVelocity.rotate(turnAng);
        setRotation(linearVelocity.angle() - 90);
    }

    public void hit(int damage) {
        //TODO simulating taking damage
        health -= damage;
        if (!isDead()) {
            updateParticleTrail(); // why does this need to be called each time?
        }
    }

    private void checkForProjectileCollisions() {
//        world.getStorage().get(myRect)
    }

//    public void shootIfInRange(Steerable<Vector2> target) {
//        for (Weapon weapon : weapons) {
//            if(this.getPosition().dst(target.getPosition()) < weapon.getRange())
//                weapon.fire(target);
//        }
//    }
//
//    public void shoot(Steerable<Vector2> target) {
//        for (Weapon weapon : weapons) {
//            weapon.fire(target);
//        }
//    }
//
//    public void shoot() {
//        for (Weapon weapon : weapons) {
//
//        }
//    }

    public boolean isDead() {
        return health < 0;
    }

    public Color getMarkerColor() {
        return def.markerColor;
    }

    public Controller getController() {
        return controller;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public float getBoundingRadius() {
        return (sprite.getHeight() + sprite.getWidth()) / 4f;
    }

    @Override
    public float getMaxLinearSpeed() {
        return def.speed;
    }

    @Override
    public String toString() {
        return "Plane with " + def + " body and " + controller;
    }

}
