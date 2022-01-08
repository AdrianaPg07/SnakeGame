package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Food {
    private int x;
    private int y;
    private boolean alive = false;
    Snake snake;

    Food(Snake snake) {
        this.snake = snake;
    }

    public void updatePosition () {
        boolean covered;   //tapado
        if (!alive) {   //vida
            do {
                covered = false;
                x = MathUtils.random((Gdx.graphics.getWidth() / snake.getSIZE()) - 1) * snake.getSIZE(); //calibracion de oobjeto
                y = MathUtils.random((Gdx.graphics.getHeight() / snake.getSIZE()) - 1) * snake.getSIZE();
                if (x == snake.getX() || y == snake.getY()){
                    covered = true;
                }
                alive = true;
            } while (covered);
        }
    }

    public void checkFoodCollision() {
        if (alive && x == snake.getX() && y == snake.getY()){
            snake.createBodyPart(snake.getX(), snake.getY());
            alive = false;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, snake.getSIZE(), snake.getSIZE());
        shapeRenderer.end();
    }
}
