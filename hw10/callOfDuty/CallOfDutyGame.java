package src.callOfDuty;

import java.util.Scanner;

/**
 * control the process of the game
 */
public class CallOfDutyGame {
    /**
     * define the weapon
     */
    static Weapon weapon;

    /**
     * constructor
     * @param weapon
     */
    public CallOfDutyGame(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * game starts here
     * @param str
     */

    public static void main(String[] str) {
        boolean playAgain = true;

        while(playAgain) {
            // creat a new base
            Base base = new Base();
//            // print the original base name
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print(base.getTargetsArray()[i][j].getTargetName());
//                }
//                System.out.println();
//            }

            // creat new weapons
            Weapon weaponRocket = new RocketLauncher();
            Weapon weaponMissile = new Missile();
            CallOfDutyGame callOfDutyGame = new CallOfDutyGame(weaponRocket);

            // generate the target on the base
            base.placeAllTargetRandomly();

            // System.out.println("---------------------");

            // print the base after generate randomly
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print("   " + base.getTargetsArray()[i][j].getTargetName() + "   ");
//                }
//                System.out.println();
//            }

            boolean gameOver = false;

            while (!gameOver) {
                // System.out.println(base);
                base.print();
                // ask the user's input
                String input = callOfDutyGame.askInput(weaponRocket, weaponMissile);

                while (input.length() == 1) {
                    // change weapon
                    CallOfDutyGame.weapon = CallOfDutyGame.weapon.getWeaponType().toLowerCase().equals("missile") ? weaponRocket : weaponMissile;
                    input = callOfDutyGame.askInput(weaponRocket, weaponMissile);
                }

                while (CallOfDutyGame.weapon.getShotLeft() == 0) {
                    // when not enough ammunition
                    System.out.println("No ammunition");
                    input = callOfDutyGame.askInput(weaponRocket, weaponMissile);
                    while (input.length() == 1) {
                        // change weapon
                        CallOfDutyGame.weapon = CallOfDutyGame.weapon.getWeaponType().toLowerCase().equals("missile") ? weaponRocket : weaponMissile;
                        input = callOfDutyGame.askInput(weaponRocket, weaponMissile);
                    }
                }

                // get the row, column number
                String[] strArray = input.split(",");
                int row = Integer.parseInt(strArray[0]);
                int column = Integer.parseInt(strArray[1]);
                CallOfDutyGame.weapon.shootAt(row, column, base);

                if (base.isGameOver(weaponRocket, weaponMissile)) gameOver = true;
            }
            playAgain = callOfDutyGame.goodbyeMessage(base);
        }
    }

    /**
     * change the weapon to use
     * @param weaponRocket
     * @param weaponMissile
     */
    private void ChangeWeapon(Weapon weaponRocket, Weapon weaponMissile) {
        if (CallOfDutyGame.weapon == weaponRocket) CallOfDutyGame.weapon = weaponMissile;
        else CallOfDutyGame.weapon = weaponRocket;
    }

    /**
     * ask user to select a shot place or whether they want to change the weapon
     * @return the answer
     */
    private String askInput(Weapon weaponRocket, Weapon weaponMissile) {
        System.out.println("RPG: " + weaponRocket.getShotLeft() +
                " Missile: " + weaponMissile.getShotLeft());
        System.out.println("Your current weapon is: " + CallOfDutyGame.weapon.getWeaponType());
        System.out.print("Enter row, column, or q to switch a weapon ");

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        s = s.trim().toLowerCase();
        String[] s_split = s.split(",");
        // check if the input is legal
        while((s.length() != 1 && s.length() != 3) || (s.length() == 1 && !s.equals("q"))
                || (s.length() == 3 && s_split.length != 2)
                || (s.length() == 3 && (Integer.parseInt(s_split[0]) <0 || Integer.parseInt(s_split[0])>9))
                || (s.length() == 3 && (Integer.parseInt(s_split[1]) <0 || Integer.parseInt(s_split[1])>9))) {
            //System.out.println("!!!!!!");
            System.out.println("RPG: " + weaponRocket.getShotLeft() +
                    " Missile: " + weaponMissile.getShotLeft());
            System.out.println("Your current weapon is: " + CallOfDutyGame.weapon.getWeaponType());
            System.out.print("Enter row, column, or q to switch a weapon ");
            s = scan.next();
            s = s.trim().toLowerCase();
            s_split = s.split(",");
        }
        return s;
    }

    /**
     * print the game result and ask if the user want to paly again
     * @return true if the user want to play again
     */
    private boolean goodbyeMessage(Base base) {
        // Game is Over
        System.out.println("Game is over");

        // Does the user win?
        if (base.win()) System.out.println("You win!");
        else System.out.println("Lose!");

        // How many shots you've fired.
        System.out.println("You have fired " + base.getShotsCount() + " shots.");

        // Do you want to play again?
        System.out.println("Do you want to play again?('y' or 'n')");
        Scanner scann = new Scanner(System.in);
        String answer = scann.next();
        if (answer.length() == 0 || (answer.charAt(0) != 'y' && answer.charAt(0) != 'n')) {
            System.out.println("Do you want to play again?('y' or 'n')");
            answer = scann.next();
            answer = answer.trim().toLowerCase();
        }
        return answer.charAt(0) == 'y' ? true : false;
    }
}
