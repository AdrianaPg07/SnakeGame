package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends ScreenAdapter {
    private GlyphLayout layout;
    private BitmapFont bitmapFont;
    private Batch batch;
    ShapeRenderer shapeRenderer;

    Snake snake;
    Food food;  //
    Controller controller;

    private static final int SNAKE_SIZE = 32;
    private static final int SNAKE_STEP = SNAKE_SIZE;
    private static final float SNAKE_SPEED = 1F;

    @Override
    public void show() {
        batch = new SpriteBatch();
        layout = new GlyphLayout();
        bitmapFont = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        snake = new Snake(2, 6);  //4
        food = new Food(snake);  //
        controller = new Controller();

    }

    @Override
    public void render(float delta) {
        //queryInput();
        snake.updateDirection(controller.queryInput());  //
        snake.update(delta);
        food.updatePosition(); //
        food.checkFoodCollision();  //llamar a clase food
        clearScreen();
        batch.begin();
        //snake.drawHead(batch, shapeRenderer);
        snake.draw(shapeRenderer);
        snake.drawBodyParts(shapeRenderer);  // llama clase...
        food.draw(shapeRenderer);
        batch.end();
    }

    private  void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
/*
    private void queryInput() {
        DIRECTION direction = controller.queryInput();
        if (direction != DIRECTION.NONE){
            snake.updateDirection(direction);
        }
    }

 */
}
