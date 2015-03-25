package com.mygdx.pixelpilot.util.pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.plane.SteerableActor;

public class ArrayPool<T> extends Pool<Array<T>> {
    public static ArrayPool<SteerableActor> steerableActorArrayPool = new ArrayPool<SteerableActor>();
    @Override
    protected Array<T> newObject() {
        return new Array<T>();
    }

    @Override
    public Array<T> obtain() {
        Array<T> t = super.obtain();
        t.clear();
        return t;
    }
}
