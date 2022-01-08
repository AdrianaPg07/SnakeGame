package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Snake {
    //private int size;
    //private int speed;
    //private int step;
    private DIRECTION direction = DIRECTION.RIGHT;
    private int x;
    private int y;
    //private  int size;  //
    //private  int bodySize = 0;
    private  int xBeforeMove;
    private  int yBeforeMove;
    private float timer;
    private final float MOVE_TIME;
    private final int SIZE;
    private final int STEP;
    private Array<BodyPart> bodyParts = new Array<>();

    Snake(int size, int speed) {
        //this.size = size;
        //this.step = step;
        //this.speed = speed;
        MOVE_TIME = 1f / speed;
        SIZE = 16 * size;   //or 32
        STEP = SIZE;
        this.timer = MOVE_TIME;
    }

    private void move(DIRECTION direction){
        this.xBeforeMove = this.x;
        this.yBeforeMove = this.y;
        switch (direction){
            case RIGHT: {
                this.x += STEP;
                return;
            }
            case LEFT: {
                this.x -= STEP;
                return;
            }
            case UP: {
                this.y += STEP;
                return;
            }
            case DOWN: {
                this.y -= STEP;
                return;
            }
        }
    }

    public void update(float delta){
        timer -= delta;
        if (timer <= 0) {
            move(direction);
            checkOutOfBounds();
            updateBodyParts();  //actualizar cuerpo tamaño  MIN 4:15  //a
            timer = MOVE_TIME;
        }
    }

    public void updateDirection(DIRECTION newDirection){
        switch (newDirection) {
            case RIGHT: {
                //this.direction = direction;
                updateIfNotOpposite(newDirection, DIRECTION.LEFT);
            }
            break;
            case LEFT: {
                //this.direction = direction;
                updateIfNotOpposite(newDirection, DIRECTION.RIGHT);
            }
            break;
            case UP: {
                //this.direction = direction;
                updateIfNotOpposite(newDirection, DIRECTION.DOWN);
            }
            break;
            case DOWN: {
                //this.direction = direction;
                updateIfNotOpposite(newDirection, DIRECTION.UP);
            }
            break;
        }
    }

    private void checkOutOfBounds() { //Limites
        if (x > Gdx.graphics.getWidth()) {
            x = 0; //SIZE / 2;
        }
        if (x < 0) {
            x = Gdx.graphics.getWidth(); // - SIZE / 2;
        }
        if (y > Gdx.graphics.getHeight()) {
            y = 0;
        }
        if (y < 0) {
            y = Gdx.graphics.getHeight();
        }
    }
    //direccion Opuesto if
    private void updateIfNotOpposite(DIRECTION newDirection, DIRECTION oppositeDirection) {
        if (this.direction != oppositeDirection || bodyParts.size == 0) {  //size
            this.direction = newDirection;
        }
    }

    public void draw(ShapeRenderer shapeRenderer){
        drawHead(shapeRenderer);
    }

    private void drawHead(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(x, y, SIZE, SIZE);
        shapeRenderer.end();
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public int getSIZE(){
        return this.SIZE;
    }

    public int getSTEP(){
        return this.STEP;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    //
    public void createBodyPart(int x, int y) {
        BodyPart bodyPart = new BodyPart();
        bodyPart.updateBodyPart(x, y);
        bodyParts.insert(0, bodyPart);
    }

    public void drawBodyParts(ShapeRenderer shapeRenderer) {
        for (BodyPart bodyPart : bodyParts) {
            bodyPart.draw(shapeRenderer);  // dibuja objeto
        }
    }

    public void updateBodyParts() {
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPart(xBeforeMove, yBeforeMove);
            bodyParts.add(bodyPart);
        }
    }
    //class
    private class BodyPart {
        int x;
        int y;

        BodyPart() {

        }

        public void updateBodyPart(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(ShapeRenderer shapeRenderer) {
            if (!(this.x == Snake.this.x && this.y == Snake.this.y)) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.GOLD);
                shapeRenderer.rect(this.x, this.y, Snake.this.SIZE, Snake.this.SIZE);  //MIN 4
                shapeRenderer.end();
            }
        }
    }
}
