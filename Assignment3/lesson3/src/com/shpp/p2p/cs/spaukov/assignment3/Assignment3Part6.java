package com.shpp.p2p.cs.spaukov.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;


/**
 * TODO:We may not have reviewed the animation yet, but we will review it soon. Well, for now, your task: to write a program that displays as much as 5 seconds of any animation (not taken from somewhere, but created by hand). It can be any animation of your choice with any script.
 * five seconds, no more and no less. At the end of our course, we will combine all your creations into one cool video.
 * the animation must contain at least 50 frames. If you have 24 frames per second, that's fine.
 * Be creative!) Is all we ask.
 */

public class Assignment3Part6 extends WindowProgram implements Runnable {

    private static final double OFFSET_STAR = 50;
    private static final double PAUSE_TIME = 1000 / 30.0;
    private static final double ALL_SECONDS = 5;
    private static final double MOON_SIZE = 100;
    private static final int STARS_NUMBER = 100;
    private static final double FIRST_ANIMATION_SECONDS = 2;
    private static final double SECOND_ANIMATION_SECONDS = 3;
    private long startMillis;
    private GLabel textSeconds;

    public void run() {

        System.out.println("Math:" + Math.cos(0.91));

        startMillis = System.currentTimeMillis();
        setBackground(Color.BLACK);
        //System.out.println((System.currentTimeMillis() / 1000));
        //System.out.println(TimeUnit.MILLISECONDS.toSeconds(timeMillis););
        //System.out.println((System.currentTimeMillis()));
        calculateMillisec();
        //**!!!!!!!!!!!!ATTENTION!!!ATTENTION!!!ATTENTION!!!**//
        //this can produce NullPointerException!!!!!!!!!!!!!!!!//
        ////////////////this is known problem///////////////////
        ///////////caused by dividing to 0, in some cases///////
        ///////////when computer @things@ that is near to 0/////
        //////////because sometime Double are not ideal/////////
        //createParticles();//<-------------THIS
        /////SPECIALLY NOT TAKEN INTO TRY CATCH BRACKETS!!!!!////
        ////////////////////////////////////////////////////////

        createStars();
        drawMoonVoid();

    }

    private void createStars() {
        /*
        we will create stars by random
        random position shall be in vertical and Horizontal position
         */

        ArrayList<GObject> stars = new ArrayList<>();
        //it will be not less then 100 stars so create for
        for (int i = 0; i < STARS_NUMBER; i++) {
            //inside this for we shall put random stars
            double randomPositionX = RandomGenerator.getInstance().nextDouble(OFFSET_STAR, getWidth() - OFFSET_STAR);
            double randomPositionY = RandomGenerator.getInstance().nextDouble(OFFSET_STAR, getHeight() - OFFSET_STAR);
            //now code stars
            //and dont forget to code size of stars
            int randomSize = RandomGenerator.getInstance().nextInt(1, 11);
            GOval star = new GOval(randomPositionX, randomPositionY, randomSize, randomSize);
            star.setColor(Color.BLACK);
            star.setFilled(true);
            stars.add(star);
            add(star);
        }
        //now stars created, we need to make them glow
        //within 3 seconds
        //second for this animation
        double secondsAnimation = (FIRST_ANIMATION_SECONDS) * 1000;
        double secondsAnimationUpdate = (ALL_SECONDS * 1000) - secondsAnimation;
        System.out.println(secondsAnimationUpdate);
        int secondsCounter = 0;
        while (updateCalculateSeconds() > secondsAnimationUpdate) { //5000>(5000-1000)

            //wow
            // how do it?
            // i need create array of stars


            for (int i = 0; i < STARS_NUMBER; i++) {
                //calculate clour count
                //secondsAnimation - 1000
                //we need not more than 255
                //1000/255=3
                double colorCount = secondsCounter * (secondsAnimation / 255 / i * 10);//10 - brightness
                int colorCountCastInt = Math.min((int) colorCount, 255);
                stars.get(i).setColor(new Color(colorCountCastInt, colorCountCastInt, colorCountCastInt));//new Color(196, 30, 58)
                //stars.get(i).setLocation(stars.get(i).getX(),stars.get(i).getY()*Math.cos((secondsAnimation-secondsCounter)/10000)+getHeight()/(Math.cos((secondsAnimation-secondsCounter)/10000)*getHeight()));
                //double backCounterToZero=secondsAnimation-secondsCounter;
                //double starY =stars.get(i).getY()*Math.cos((getHeight()-secondsCounter*(secondsAnimation/getHeight()))/1000);
                //double cosMath = Math.cos((getHeight()-secondsCounter*(secondsAnimation/getHeight()))/1000);
                // double starY =cosMath*generatorY.get(i);
                //stars.get(i).setLocation(stars.get(i).getX(),starY);

            }
            secondsCounter++;
            pause(PAUSE_TIME);
            //pause(0);


        }
        System.out.println(secondsCounter);
    }


    private long updateCalculateSeconds() {
        long seconds = System.currentTimeMillis() - startMillis;
        long finishMillis = (long) (ALL_SECONDS * 1000 - seconds);
        if (textSeconds == null) {
            textSeconds = new GLabel("start", 10, 50);
            textSeconds.setColor(Color.WHITE);
            textSeconds.setFont("Verdana-50");
            add(textSeconds);
        }
        if (seconds < (ALL_SECONDS * 1000) + 1) {
            textSeconds.setLabel(String.valueOf((int) ALL_SECONDS * 1000 - seconds));
        }
        return finishMillis;
    }

    /**
     * how to calculate millisec?
     * in 1 sec 1000 ms
     * we have only 5 seconds
     * try to get in first animations 3 sec in second 2 seconds
     * 5 / 3 = 1.66666
     * and pause will be 1000/fps
     * speed of animation:
     * in 3 sec some animation will be done
     * 3 sec * 30 frames = 90 frames
     * particles moved from senter of screen to edges
     * so it moves with speed half screen/90
     */
    double firstAnimationMillisec = 0;
    double speedOfAnimation = 0;

    /**
     * SO WE HAVE for example 2000 ms
     * and we need to move object which moved a half of screen
     * in 2000ms
     * speed be 2000/half screen..
     * lets think again
     * speed that is km/per hour
     * we need to find speed, where km - half of screen
     * hour - ms
     */
    private double calculateMillisec() {
        // first animation will be 1.66 part of all seconds
        //firstAnimationMillisec = PAUSE_TIME;
       return getWidth() / 2.0 / (4 * PAUSE_TIME);//4 - speed of the moon(but really it's seconds, where moon moves)
    }

    private void drawMoonVoid() {

        GOval moon = drawMoon();
        add(moon);
        GOval shadowMoon = drawShadowOfMoon(moon);
        add(shadowMoon);
        while (updateCalculateSeconds() >= 0) {
            moon.move(-calculateMillisec(), 0);
            shadowMoon.move(-calculateMillisec(), 0);
            pause(PAUSE_TIME);
        }
    }


    private GOval drawMoon() {
        GOval moon = new GOval(getWidth() + MOON_SIZE, getHeightByRandom(), MOON_SIZE, MOON_SIZE);
        //GOval moon = new GOval(getWidth() / 2.0, getHeight() / 2, 100, 100);
        moon.setColor(Color.WHITE);
        moon.setFilled(true);
        moon.setFillColor(Color.WHITE);
        return moon;
    }

    private double getHeightByRandom() {
        return RandomGenerator.getInstance().nextDouble(0, getHeight() - MOON_SIZE);
    }

    private GOval drawShadowOfMoon(GOval moon) {
        GOval shadowMoon = new GOval(moon.getX() - moon.getWidth() / 2.5, moon.getY(), moon.getWidth(), moon.getHeight());
        shadowMoon.setFilled(true);
        shadowMoon.setFillColor(Color.BLACK);
        return shadowMoon;
    }

    private void createParticles() {
        double positionX = getWidth() / 2.0;
        double positionY = getHeight() / 2.0;


        ArrayList<GObject> stars = new ArrayList<>();
        ArrayList<Integer> generatorX = new ArrayList<>();
        ArrayList<Integer> generatorY = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int generator = RandomGenerator.getInstance().nextInt(1, 10);//10 - fast stars
            int generator2 = RandomGenerator.getInstance().nextInt(1, 1000);//1000 - slow stars
            int size = 11 - generator;//11 - maximum size of stars (because 11- 10 > 1)

            GObject star = (createParticleOval(positionX, positionY, size));
            add(star);
            stars.add(star);

            generatorX.add(generator);
            generatorY.add(generator2);
        }


        int starsNumber = stars.size();
        boolean nextAnimation = true;
        while (nextAnimation) {
            for (int i = 0; i < stars.size(); i++) {
                //star.movePolar(generator, generator2);
                if (stars.get(i) != null) {
                    stars.get(i).movePolar(generatorX.get(i), generatorY.get(i));
                    /*
                    very difficult code, but it should be there,
                    in my tablet this stars moved very slowly, in computer faster
                    and i create pseudo acceleration
                     */
                    //double pause = updateCalculateSeconds()-2000;//that is ms to finish 5000-4500 (500msec)
                    double pause = 2000;//that is ms to finish 5000-4500 (500msec)
                    // pause = pause < 0 ? 0.000001 : pause;
                    /*
                    [---------]
                    [         ]
                    [         ]
                    [_________]
                    this is the window, and to calculate from center to corner we a imagine that is a triangle
                          . ]
                         .  ]
                        .   ]
                       .    ]
                     .......]
                     */

                    double halfSize = Math.sqrt(getHeight() * getHeight() + getWidth() * getWidth()) / 2;//
                    double movedParticle = Math.sqrt(stars.get(i).getX() * stars.get(i).getX() + stars.get(i).getY() * stars.get(i).getY());
                    double distanceLeft = halfSize - movedParticle;
                    /*
                    now we have another problem
                    1 - wtf? 1ms - no, 1 pixel for second? - no.
                    try to guess
                    if this value less, the object move quicker
                    try to write 1000 whats happened?
                    so it very similar with second
                    for example if it 1 - it is move to 1-10 pixels in a 1 ms
                     */
                    double speed;
                    speed = distanceLeft / pause;
                    speed = speed < 0 ? 0 : speed;//<---THIS CAN PRODUCE EXCEPTION SOMETIMES try catch brackets needed
                    stars.get(i).pause(speed);

                    //System.out.println(System.currentTimeMillis()/1000-startMillis);

                    //need to calculate it
                    //for calculation pause
                    nextAnimation = breakWhenStarsOut(stars.get(i), stars);
                }
            }
            //star.pause(10);
        }

        //////////////////////

    }


    private boolean breakWhenStarsOut(GObject star, ArrayList<GObject> stars) {
        /**
         * if start out of screen remove it
         * screen position can be < 0
         * or screen width  > position
         */
        if ((star.getX() + OFFSET_STAR < 0) || (star.getX() > getWidth() - OFFSET_STAR)) {
            return false;
        }
        if ((star.getY() + OFFSET_STAR < 0) || (star.getY() > getHeight() - OFFSET_STAR)) {
            return false;
        }
        if (updateCalculateSeconds() < 3000) {
            return false;
        }
        return true;
    }

    private GObject createParticleOval(double positionX, double positionY, int size) {
        GOval particle = new GOval(positionX, positionY, size, size);
        particle.setFilled(true);
        particle.setFillColor(Color.WHITE);
        particle.setColor(Color.WHITE);
        return particle;
    }


}
