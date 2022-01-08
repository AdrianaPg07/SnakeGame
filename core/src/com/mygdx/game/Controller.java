package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
    public DIRECTION queryInput() {
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        if (rightPressed)
            return  DIRECTION.RIGHT;
        if (leftPressed)
            return  DIRECTION.LEFT;
        if (upPressed)
            return  DIRECTION.UP;
        if (downPressed)
            return  DIRECTION.DOWN;
        return DIRECTION.NONE;
    }
}
