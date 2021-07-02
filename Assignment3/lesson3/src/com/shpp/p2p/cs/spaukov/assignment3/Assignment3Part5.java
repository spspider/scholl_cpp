package com.shpp.p2p.cs.spaukov.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;


/**
 * TODO:Douglas Hofstadter's book "Gödel, Escher, Bach" (Pulitzer Prize-winning) mentions
 * various interesting tasks that can, to some extent, be viewed through the lens of programming.
 * Here is one of them:
 */

public class Assignment3Part5 extends TextProgram {

    private static final int HOW_MANY_MONEY_TO_WIN = 20;

    /**
     * end of the game - someone win 20$
     * unlucky place 1$
     * lucky start the game by random bool
     * if
     * eagle
     * unlucky +add same money
     * if no eagle
     * all n table go to lucky
     * if lucky have less then 20$
     * then repeat the game
     */

    public void run() {
        startGame();

    }

    private void startGame() {
        //int howManyGames = 1;
        int unluckyMoney = 1;
        int luckyMoney = 0;
        int howManyGames = doSomeWhileCalculations(unluckyMoney, luckyMoney);
        println("It took " + howManyGames + " game"+(howManyGames<2?"":"s")+" to earn $" + HOW_MANY_MONEY_TO_WIN);

    }

    /**
     * lets think what we need to solve this game
     * if lucky wins, all money moved to him
     * if unlucky he must add money to table in count which is table have
     * <p>
     * total games it is all money ++
     * if you win one game,that is mean
     * <p>
     * this game - 1$ - i think it was false (one game where
     * boolean was false and all money transferred to lucky)
     * if (false){lucky money = allmoney}
     * if (true){do again until it will be false so continue}
     *
     * @param unluckyMoney - unlucky money with start 1
     * @param luckyMoney   - lucky money, to check if < 20
     */

    private int doSomeWhileCalculations(int unluckyMoney, int luckyMoney) {
        int howManyGames = 0;

        //   ↓↓↓↓↓  for testing purposes  ↓↓↓↓↓
        boolean money[] = new boolean[]{true, false, false, false, false, false};
        //      ↑↑↑↑↑ for testing ↑↑↑↑↑↑↑↑

        int totalMoney = 0;
        int moneyOnTable = 0;
        moneyOnTable = unluckyMoney;
        while (totalMoney < HOW_MANY_MONEY_TO_WIN) {
            boolean eagle = getRandomBolean();

            if (!eagle) {//all money goes to lucky
                luckyMoney = moneyOnTable;
            } else {//add money on table in quantity how much money
                moneyOnTable += moneyOnTable;
                continue;//i use continue to identify it is need to continue my while, because condition not applied
            }

            totalMoney += moneyOnTable;//for know how money add some
            println("This game, you earned $" +luckyMoney);
            println("Your total is $" + totalMoney);
            howManyGames++;
        }
        return howManyGames;
    }

    private boolean getRandomBolean() {
        return RandomGenerator.getInstance().nextBoolean();
    }


}
