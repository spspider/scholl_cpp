package com.shpp.p2p.cs.spaukov.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BreakoutExt extends WindowProgram {
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
    double vx, vy;
    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 100;
    private static final int BALL_DIAMETER = 2 * BALL_RADIUS;

    double prevMouseX = 0;
    double dxMouse = 0;


    /**
    in this game i add Delta corners,
     so if you want to use some physics, play this game!
     */
    public void run() {
        /* You fill this in, along with any subsidiary methods */
            /*lets start.
            firstly i need to create my rocket)

             */


        addMouseListeners();
        runGame();

    }

    private void runGame() {
        createFirstStep();

        GObject click = showMessage("PLEASE CLICK TO START, TURNS:" +nTurns);
        waitForClick();//wait for click
        remove(click);//remove this label
        createSecondStep();//create ball
        drawBlocks();//draw blocks
        addBallPhysics();//add while
    }

    /**
     *
     * try to draw blocks right in a Assignemnt 2
     */
    private void drawBlocks() {
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                GRect box = createBox(i, j); //where i - rows, j - columns
                add(box);
            }
        }
    }

    /**
     * make another method because roman sad it's needed
     *
     * @param i -  rows
     * @param j - columns
     * @return Grect box
     */
    private GRect createBox(int i, int j) {
        GRect box = new GRect(xFirstPosition(i), yFirstPosition(j), BRICK_WIDTH, BRICK_HEIGHT);
        box.setFilled(true);
        box.setFillColor(determineColorByRow(j));
        return box;
    }

    private Color determineColorByRow(int j) {
        return switch (j) {
            case 0, 1 -> Color.RED;
            case 2, 3 -> Color.ORANGE;
            case 4, 5 -> Color.YELLOW;
            case 6, 7 -> Color.GREEN;
            case 8, 9 -> Color.CYAN;
            default -> Color.BLACK;
        };
    }

    private double yFirstPosition(int j) {
        /**
         * to calculate center of all boxes i use screen and take half of it
         * then multiply int from cycle and add box size with spacing
         */
        double yFirstPosition_offset = BRICK_Y_OFFSET;
        return yFirstPosition_offset + j * (BRICK_HEIGHT + BRICK_SEP);
    }

    private double xFirstPosition(int i) {
        double xFirstPosition_offset = determineCenterOfScreenX();
        return xFirstPosition_offset + i * (BRICK_WIDTH + BRICK_SEP);
    }

    private double determineCenterOfScreenX() {
        /**
         * same as did before but in vertical dimension
         */
        double CenterScreen = getWidth() / 2.0;
        double CenterOfBoxes = ((BRICK_WIDTH + BRICK_SEP) * NBRICKS_PER_ROW - BRICK_SEP) / 2.0;
        return CenterScreen - CenterOfBoxes;
    }

    private GLabel text;


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseEvent.getButton();
        if (!programRun) {
            programRun = true;

        }

    }



    boolean programRun = true;
    int bricksCount = NBRICK_ROWS * NBRICKS_PER_ROW;

    private void addBallPhysics() {
        /*
        now add some physics to the ball
         */
        vx = randomGenerator();
        vy = 3.0;

        while (programRun) {
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
            if (collidingObject != null) {
                if (collidingObject != paddle) {
                    remove(collidingObject);
                    bricksCount--;
                    if (bricksCount <= 0) {
                        programRun = false;
                        removeAll();
                        showMessage("YOU WIN THE GAME");
                    }
                }
            }
            createglabelfordebug();
        }
    }


    @Override
    public void waitForClick() {
        super.waitForClick();
    }

    private GObject getCollidingObject(GOval ball) {
        //take object where ball is:
        boolean collided = false;
        GObject collidingLeftUp = getElementAt(ball.getX(), ball.getY());
        GObject collidingRightUp = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY());
        GObject collidingLeftDown = getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER);
        GObject collidingRightDown = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER);

        GObject colliding = null;
        //colliding = collidingLeftDown;

        if (collidingLeftDown != null || collidingRightDown != null || collidingLeftUp != null || collidingRightUp != null) {
            if ((collidingLeftDown != null) && (collidingRightDown != null)) {//collided down
                //ignore vx
                vy *= -1;
            } else if ((collidingLeftUp != null) && (collidingRightUp != null)) {//collided up
                //ignore vx
                vy *= -1;
            } else if ((collidingLeftDown != null) && (collidingLeftUp != null)) {//collided left
                //ignore vy
                vx *= -1;
            } else if ((collidingRightDown != null) && (collidingRightUp != null)) {//collided right
                //ignore vy
                vx *= -1;

                //now we add logic for corners
            } else if ((collidingLeftUp != null) && (collidingRightDown == null)) {//colliding leftUp corner
                leftUpLogic(collidingLeftUp);
            } else if ((collidingRightDown != null) && (collidingLeftUp == null)) {//colliding leftDown corner
                rigtDownLogic(collidingRightDown);
            } else if ((collidingRightUp != null) && (collidingLeftDown == null)) {//colliding leftUp corner
                rightUpLogic(collidingRightUp);
            } else if ((collidingLeftDown != null) && (collidingRightUp == null)) {//colliding leftDown corner
                leftDownLogic(collidingLeftDown);
            }
            //vx*=-dxMouse/10;
            //vx *= -2.5;
        }
        colliding = collidingLeftDown != null ? collidingLeftDown : colliding;
        colliding = collidingRightDown != null ? collidingRightDown : colliding;
        colliding = collidingLeftUp != null ? collidingLeftUp : colliding;
        colliding = collidingRightUp != null ? collidingRightUp : colliding;

        ArrayList<GObject> CollidedArray = new ArrayList<>();
        if (collidingLeftDown != null) {
            CollidedArray.add(collidingLeftDown);
        }
        if (collidingRightDown != null) {
            CollidedArray.add(collidingRightDown);
        }
        if (collidingLeftUp != null) {
            CollidedArray.add(collidingLeftUp);
        }
        if (collidingRightUp != null) {
            CollidedArray.add(collidingRightUp);
        }
        if (CollidedArray.size() > 2) {
            ball.setLocation(getWidth() / 2.0, getHeight() / 2.0);
            //vy=getHeight()/2.0;
        }

        return colliding;
    }

    private void leftUpLogic(GObject collidingLeftUp) {
        if ((collidingLeftUp != null)) {//Left Up ->

            if (vx < 0) {//if vx > 0 - fly right, if vx < 0 fly left
                //vx *= -1 * getRandom();
               calculateDelta(collidingLeftUp,-1,-1,3);
            }
            if (vy < 0) {//if vy < 0 - fly up, if vy > 0 fly down
                calculateDelta(collidingLeftUp,-1,-1,3);
            }
        }
    }

    private void rightUpLogic(GObject collidingRightUp) {
        if ((collidingRightUp != null)) {
            if (vx > 0) {//if vx > 0 - fly right, if vx < 0 fly left
                 calculateDelta(collidingRightUp,-1,-1,2);
            }
            if (vy < 0) {//if vy < 0 - fly up, if vy > 0 fly down
                calculateDelta(collidingRightUp,-1,-1,2);
            }
        }
    }

    private void rigtDownLogic(GObject collidingRightDown) {
        if ((collidingRightDown != null)) {
            //it flying to down
            //so vy > 0
            if (vx > 0) {//if vx > 0 - fly right, if vx < 0 fly left
               calculateDelta(collidingRightDown,-1,-1,1);
            }
            if (vy > 0) {//if vy < 0 - fly up, if vy > 0 fly down
                calculateDelta(collidingRightDown,-1,-1,1);
            }

        }
    }

    private void leftDownLogic(GObject collidingLeftDown) {
        if ((collidingLeftDown != null)) {
            if (vx < 0) {//if vx > 0 - fly right, if vx < 0 fly left
                calculateDelta(collidingLeftDown,-1,-1,0);
                //vx *= -dxMouse;
            }
            if (vy > 0) {//if vy < 0 - fly up, if vy > 0 fly down
                calculateDelta(collidingLeftDown,-1,-1,0);
            }
        }
    }


    private void calculateDelta(GObject collidingObject, int whereXGo, int whereYGo, int i) {
        double deltaX = 0;
        double deltaY = 0;
        switch (i) {
            case 0 -> {//leftDown
                deltaX = collidingObject.getX() + collidingObject.getWidth() - ball.getX();
                deltaY = collidingObject.getY() - ball.getY() - BALL_DIAMETER;
            }
            case 1 -> {//rightDown
                deltaX = collidingObject.getX() - ball.getX() - BALL_DIAMETER;
                deltaY = collidingObject.getY() - ball.getY() - BALL_DIAMETER;
            }
            case 2 -> {//leftUp
                deltaX = collidingObject.getX() + collidingObject.getWidth() / 2.0 - ball.getX() + BALL_RADIUS + BALL_DIAMETER;
                deltaY = ball.getY() - BALL_DIAMETER - paddle.getY() + paddle.getHeight();
            }
            case 3 -> {//right up
                deltaX = collidingObject.getX() - ball.getX() - BALL_DIAMETER;
                deltaY = ball.getY() - BALL_DIAMETER - paddle.getY() + paddle.getHeight();
            }
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }

        if ((deltaX > 10) || (deltaX <= 0)) {
            deltaX = 1;
            double percentage = deltaX * 100 / 10.0;//50%
            double increasedByX = (Math.abs(vx) + Math.abs(vy)) * percentage / 100;
            double increaseByY = (Math.abs(vx) + Math.abs(vy)) - increasedByX;

            vx = increasedByX;
            vy = -increaseByY;
        } else if ((deltaY >= 0) || (deltaY <= -10)) {
            deltaY = 1;
            double percentage = deltaY * 100 / 10.0;//50%
            double increasedByY = (Math.abs(vx) + Math.abs(vy)) * percentage / 100;
            double increaseByX = (Math.abs(vx) + Math.abs(vy)) - increasedByY;


            vx = increaseByX * whereXGo;
            vy = increasedByY * whereYGo;
        }
    }

    int nTurns = NTURNS;

    private double addTopBottomWalls(double vy) {
        if (ball.getY() < 0) {
            //if ((ball.getY() < 0) || (ball.getY() > getHeight() - BALL_DIAMETER)) {//uncomment this to make ball jump from bottom.
            vy *= -1;
        }
        if (ball.getY() > getHeight() - BALL_DIAMETER) {
            //it's stops the game and we should return to step where we start game again
            programRun = false;
            removeAll();

            if (nTurns > 0) {

                remove(showMessage("TRY AGAIN"));
                nTurns--;
                runGame();
                //stop the game, it's fail
            } else {
                showMessage("SORRY YOU CAN TRY ANOTHER GAME");
            }

        }
        if (ball.getY() < 0) {
            ball.setLocation(ball.getX(), 0);
        }
        double rightBallPosition = getHeight() - BALL_DIAMETER;
        if (ball.getY() > rightBallPosition) {
            ball.setLocation(ball.getX(), rightBallPosition);
        }
        return vy;
    }


    private GObject showMessage(String s) {
        GLabel messageGame = new GLabel(s, 0, 0);
        messageGame.setFont("serif 25");
        messageGame.setLocation(getWidth() / 2.0 - messageGame.getWidth() / 2.0, getHeight() + 50);
        boolean stop = false;
        add(messageGame);
        while (!stop) {
            messageGame.move(0, -10);
            pause(PAUSE_TIME);
            if (messageGame.getY() <= getHeight() / 2.0) {
                stop = true;
            }
        }

        return messageGame;
    }

    private double addLeftRightWalls(double vx) {
        if ((ball.getX() < 0) || (ball.getX() > getWidth() - BALL_DIAMETER)) {//left or right wall
            //need to change vx
            vx *= -1;
        }
        if (ball.getX() > getWidth() - BALL_DIAMETER) {
            ball.setLocation(getWidth() - BALL_DIAMETER, ball.getY());
        }
        if (ball.getX() < 0) {
            ball.setLocation(0, ball.getY());
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
        ball = new GOval(getWidth() / 2.0 - BALL_DIAMETER / 2.0, getHeight() / 2.0 - BALL_DIAMETER / 2.0, BALL_DIAMETER, BALL_DIAMETER);
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
    public void mouseExited(MouseEvent mouseEvent) {
        dxMouse = 0;
    }

    private void createglabelfordebug() {
        if (text == null) {
            text = new GLabel("text", 10, 10);
            //text.setLabel("vx:" + vx + " vy" + vy);
            add(text);
        }

        //from 10 to 0
        //where 0 it's 1
        //if the ball is bouncing from wall more than 10, this vx or vy don't needed
        //cos x from 0 to 1.57 (PI/2)
        //calculate traektory
        //for example we have ball where placed x = 5
        //and y = 0,
        //we expect that ball will move to
        //when delta = 10 then
        // if deltaX and deltaY = 1, then vx*=-1 and vy*=-1

        /*
        LOGIC:
        vx = 1.5
        vy = 3
        vx + vy = 4.5
        10 part of it 10*4.5/10 = 4.5
        example
        we have 1.5 and 3.0
        if increase 1.5 by 20% it will be 1.7
        if all summ 1.5 + 3.0 = 4.5
        another will be 4.5 - 1.7
        then
        20% in example for me 10 that is 100% and 4.5 that is 100% too
        4.5 - 100%
        10  - 100%  -> 100/10 = 10
        1.5 that is 33% of 4.5
        and 5 that is (5 * 100/10) = 50%
        we need to inrease by 50% = 4.5 - 100%
                                     x  - 50%
           x= 4.5*50/100=2.25

         */
        double deltaX, deltaY;
        GObject collidingObject = paddle;

        //left down - > ok
        deltaX = collidingObject.getX() + collidingObject.getWidth() - ball.getX();
        deltaY = collidingObject.getY() - ball.getY() - BALL_DIAMETER;

        //left up
        //deltaX = collidingObject.getX() + collidingObject.getWidth() / 2.0 - ball.getX() + BALL_RADIUS + BALL_DIAMETER;
        //deltaY = ball.getY()-BALL_DIAMETER-paddle.getY()+paddle.getHeight();
//        //rght up
//        deltaX = collidingObject.getX() - ball.getX()-BALL_DIAMETER;
//        deltaY = ball.getY()-BALL_DIAMETER-paddle.getY()+paddle.getHeight();
//        //right down - > ok
        //deltaX = collidingObject.getX() - ball.getX()-BALL_DIAMETER;
        //deltaY = collidingObject.getY()-ball.getY()-BALL_DIAMETER;

        double percentage = deltaX * 100 / 10.0;//50%
        double increasedByX = (Math.abs(vx) + Math.abs(vy)) * percentage / 100;
        double increaseByY = (Math.abs(vx) + Math.abs(vy)) - increasedByX;
        String first = String.format("%.2f", deltaX);
        String second = String.format("%.2f", deltaY);
        String v3 = String.format("%.2f", increasedByX);
        String v4 = String.format("%.2f", increaseByY);
        text.setLabel("deltaX:" + first + " deltaY" + second + " increasedByX" + increasedByX + " increaseByY" + increaseByY);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //it will not work if i just crete override method
        //i need this object to be an a global
        //we need to move it in the middle so - half part of it
        //paddle.setLocation(mouseEvent.getX()-PADDLE_WIDTH/2.0,paddle.getY());
        //paddle goes outside of screen
        //to prevent it try to write something like exeption
        dxMouse = prevMouseX - mouseEvent.getX();
        //System.out.println(dxMouse);
        double paddleLocation = mouseEvent.getX() - PADDLE_WIDTH / 2.0;

        paddleLocation = paddleLocation < 0 ? 0 : paddleLocation;//left side block
        paddleLocation = paddleLocation > getWidth() - PADDLE_WIDTH ? getWidth() - PADDLE_WIDTH : paddleLocation;//right side block
        paddle.setLocation(paddleLocation, paddle.getY());

        //double deltaX = paddle.getX() + paddle.getWidth() - ball.getX() - BALL_DIAMETER;
        //ball.setLocation(paddleLocation + PADDLE_WIDTH, paddle.getY() + paddle.getHeight()); //left up
        //ball.setLocation(paddleLocation -BALL_DIAMETER, paddle.getY()+paddle.getHeight());//right up
        //ball.setLocation(paddleLocation -BALL_DIAMETER, paddle.getY()+paddle.getHeight()-(paddle.getHeight()+BALL_DIAMETER));//right down
        //ball.setLocation(paddleLocation+paddle.getWidth(), paddle.getY()+paddle.getHeight()-(paddle.getHeight()+BALL_DIAMETER));//left down
        prevMouseX = mouseEvent.getX();
        //super.mouseMoved(mouseEvent);
    }

    /**
     * lets draw paddle in the center of screen
     * where is offset?
     * oh here is offset - PADDLE_Y_OFFSET
     *
     * @param paddleWidth  -  wtih of the paddle
     * @param paddleHeight - heighth of paddle
     * @return paddle
     */
    private GRect drawRaket(int paddleWidth, int paddleHeight) {
        GRect paddle = new GRect(getWidth() / 2.0 - paddleWidth / 2.0, getHeight() - PADDLE_Y_OFFSET, paddleWidth, paddleHeight);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        return paddle;
    }
}
