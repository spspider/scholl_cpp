package com.shpp.p2p.cs.spaukov.assignment4;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     *
     */
    GRect paddle;
    GOval ball;
    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 48;
    private static final int BALL_DIAMETER = 2 * BALL_RADIUS;

    public void run() {
        /* You fill this in, along with any subsidiary methods */
            /*lets start.
            firstly i need to create my rocket)

             */
        addMouseListeners();

        createFirstStep();
        createSecondStep();
        addBallPhysics();
    }

    private void addBallPhysics() {
        /*
        now add some physics to the ball
         */
        double vx = randomGenerator(), vy = 3.0;

        while (true) {
            //initial speed
            //what is direction?
            //speed is: private double vx, vy;

            ball.move(vx, vy);
            pause(PAUSE_TIME);
            //ok. it's works, let's try to add walls
            vx = addLeftRightWalls(vx);
            vy = addTopBottomWalls(vy);
            //now i need to write a paddle physics:
            //getElementX

            GObject collidingObject = getCollidingObject(ball);
        }
    }

    private GObject getCollidingObject(GOval ball) {
        //take object where ball is:

        GObject collidingLeftUp = getElementAt(ball.getX(), ball.getY());
        GObject collidingRightUp = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY());
        GObject collidingLeftDown = getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER);
        GObject collidingRightDown = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_RADIUS);

        GObject colliding=null;
        if (collidingLeftUp!=null){
            colliding=collidingLeftUp;
        }
        if (collidingRightUp!=null){
            colliding=collidingRightUp;
        }
        if (collidingLeftDown!=null){
            colliding=collidingLeftDown;
        }
        if (collidingRightDown!=null){
            colliding=collidingRightDown;
        }

        return colliding;
    }

    private double addTopBottomWalls(double vy) {
        if ((ball.getY() < 0) || (ball.getY() > getHeight())) {
            vy *= -1;
        }
        return vy;
    }

    private double addLeftRightWalls(double vx) {
        if ((ball.getX() < 0) || (ball.getX() > getWidth() - BALL_DIAMETER)) {//left or right wall
            //need to change vx
            vx *= -1;
        }
        return vx;
    }

    /**
     * this is taken from course
     */
    private double randomGenerator() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        double vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        return vx;
    }

    private void createSecondStep() {
        //create ball
        ball = new GOval(getWidth() / 2.0 - BALL_DIAMETER / 2.0, getHeight() / 2.0 - BALL_DIAMETER / 2.0, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    private void createFirstStep() {
        paddle = drawRaket(PADDLE_WIDTH, PADDLE_HEIGHT);
        add(paddle);
        //now we need to make them movable, how to do it?
        //i think i need to create on MouseMove() event
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //super.mouseClicked(mouseEvent);
        System.out.println(mouseEvent.getX());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //it will not work if i just crete override method
        //i need this object to be an a global
        //we need to move it in the middle so - half part of it
        //paddle.setLocation(mouseEvent.getX()-PADDLE_WIDTH/2.0,paddle.getY());
        //paddle goes outside of screen
        //to prevent it try to write something like exeption
        double paddleLocation = mouseEvent.getX() - PADDLE_WIDTH / 2.0;

        paddleLocation = paddleLocation < 0 ? 0 : paddleLocation;//left side block
        paddleLocation = paddleLocation > getWidth() - PADDLE_WIDTH ? getWidth() - PADDLE_WIDTH : paddleLocation;//right side block
        paddle.setLocation(paddleLocation, paddle.getY());
        //super.mouseMoved(mouseEvent);
    }

    /**
     * lets draw paddle in the center of screen
     * where is offset?
     * oh here is offset - PADDLE_Y_OFFSET
     *
     * @param paddleWidth  -  wtih of the paddle
     * @param paddleHeight - heighth of paddle
     * @return
     */
    private GRect drawRaket(int paddleWidth, int paddleHeight) {
        GRect paddle = new GRect(getWidth() / 2.0 - paddleWidth / 2.0, getHeight() - PADDLE_Y_OFFSET, paddleWidth, paddleHeight);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        return paddle;
    }
}
