package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.InstalledWeaponDefinition;
import com.mygdx.pixelpilot.plane.shooty.weapon.weapons.Weapon;
import com.mygdx.pixelpilot.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Plane extends SteerableActor {

    private PlaneDefinition def;
    private List<Weapon> weapons;
    private Controller controller;
    private Sprite sprite;
    private Sprite shadow;
    private ParticleEffect smoke;
    private ParticleEffect fire;

    private int health;
    private boolean isOnFire;

    public Plane(final PlaneDefinition def, List<InstalledWeaponDefinition> weaponDefs, Controller controller) {

        this.controller = controller;
        this.def = def;
//        this.weapon = new MultishotWeapon(this);

        setMaxLinearAcceleration(200);
        setMaxAngularSpeed(5);
        setMaxAngularAcceleration(10);

        this.weapons = new ArrayList<Weapon>();
        for (InstalledWeaponDefinition weaponDef : weaponDefs) {
            this.weapons.add(weaponDef.create(this));
        }

        this.positionVector = new Vector2(0,0);

        this.linearVelocity = new Vector2(1, 1);
        linearVelocity.setLength(def.speed);
        linearVelocity.setAngle(0);

        this.sprite = new Sprite(new Texture(def.spritePath));
        sprite.setScale(3.5f, 3.5f);
        sprite.setRotation(-90);

        this.shadow = new Sprite(sprite);
        shadow.setColor(new Color(0, 0, 0, 0.1f));
        shadow.setScale(1.5f, 1.5f);

        setSize(sprite.getWidth(), sprite.getHeight());
        this.setScale(sprite.getScaleX(), sprite.getScaleY());
        sizeChanged();

        setOrigin(Align.center);
        setRotation(-90);

        health = 100;

        initializeParticles();
    }

    @Override
    public void act (float delta) {
        super.act(delta);

        // keep the position vector in sync with the actor's position
        // (in case someone calls actor.setPosition)
        this.positionVector.set(getX(), getY());

        this.controller.control(this);
        this.positionVector.add(linearVelocity);
        this.sprite.setPosition(positionVector.x - sprite.getWidth() / 2, positionVector.y - sprite.getHeight() / 2);
        this.shadow.setPosition(positionVector.x - shadow.getWidth() / 2, positionVector.y - 20 - shadow.getHeight() / 2);
        this.sprite.setRotation(this.getRotation());
        this.shadow.setRotation(this.getRotation());

        //Update the particle emitter
        this.smoke.setPosition(positionVector.x, positionVector.y);
        this.smoke.update(delta);

        if(isOnFire) {
            this.fire.setPosition(positionVector.x, positionVector.y);
            this.fire.update(delta);
        }

        // keep the actor's position in sync with the position vector
        this.setPosition(positionVector.x, positionVector.y);
    }

    /**
     * Scale the plane sprite and shadow when scaling the actor
     * Needed for scene2d actions!
     */
    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        this.sprite.setScale(scaleX, scaleY);
        //This is a really hacky fix so scaleTo actions set the right size of the shadow
        this.shadow.setScale(scaleX - 1.5f, scaleY - 1.5f);
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
        if(isOnFire) {
            fire.draw(batch);
        }
    }

    /**
     * Causes the plane to change its heading
     * @param turnAmount amount to turn this frame, expects to be in range [-1, 1]
     */
    public void turn(float turnAmount) {
        float prevAngle = linearVelocity.angleRad();
        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * linearVelocity.len() / def.minTurnRadius;

        float turnAng = Utils.map(turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        linearVelocity.rotate(turnAng);
        setRotation(linearVelocity.angle() - 90);
        angularVel = linearVelocity.angleRad() - prevAngle;
    }

    /**
     * No need to call effect.start() because these effects are continuous
     */
    private void initializeParticles(){
        //Create the plane trail particle smoke
        this.smoke = new ParticleEffect();
        this.smoke.load(Gdx.files.internal("data/particle/trail"), Gdx.files.internal("image"));

        //Create the fire particles
        this.fire = new ParticleEffect();
        this.fire.load(Gdx.files.internal("data/particle/fire"), Gdx.files.internal("image"));
        this.isOnFire = false;
    }

    private void updateParticleTrail() {
        ParticleEmitter emitter = this.smoke.findEmitter("trail");
        if(emitter != null) {

            //Darker smoke nearer to death
            float chan = Utils.map(health, 0, 100, 0.2f, 1);
            float[] colors = {chan, chan, chan};
            emitter.getTint().setColors(colors);

            //Makes the trails "puffier" nearer to death
            emitter.getScale().setHigh(Utils.map(health, 0, 100, 5, 20), Utils.map(health, 0, 100, 35, 20));
        }
        if(health < 15){
            //Render the fire particle effect in draw
            isOnFire = true;
        }
    }

    public void hit(){
        //TODO simulating taking damage
        this.health-=1;
        if(health > 0) {
            updateParticleTrail();
        }
    }

    public boolean isDead(){
        return health < 0;
    }

    public void shoot(Steerable<Vector2> target) {
        for (Weapon weapon : weapons) {
            if(this.getPosition().dst(target.getPosition()) < weapon.getRange())
                weapon.fire(target);
        }
    }

    public void shoot() {
        for (Weapon weapon : weapons) {
            weapon.fire();
        }
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }

    public Controller getController() {
        return controller;
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

    public Weapon getWeapon() {
        return null;
    }
}
