package callOfDuty;

import java.util.Scanner;

public class CallOfDutyGame {
    static Weapon weapon;

    public CallOfDutyGame(Weapon weapon) {
        this.weapon = weapon;
    }

    private void ChangeWeapon(Weapon weaponRocket, Weapon weaponMissile) {
        if (CallOfDutyGame.weapon == weaponRocket) CallOfDutyGame.weapon = weaponMissile;
        else CallOfDutyGame.weapon = weaponRocket;
    }

    public static void main(String[] str) {

        Base base= new Base();
        Weapon weaponRocket = new RocketLauncher();
        Weapon weaponMissile = new Missile();
        CallOfDutyGame callOfDutyGame = new CallOfDutyGame(weaponRocket);
        base.placeAllTargetRandomly();
        boolean gameOver = false;

        while(! gameOver) {
            // System.out.println(base);
            base.print();
            String input = callOfDutyGame.askInput(weaponRocket, weaponMissile);

            while(input.length() == 1) {
                // change weapon
                input = callOfDutyGame.askInput(weaponRocket, weaponMissile);
            }
             while (CallOfDutyGame.weapon.getShotLeft() == 0) {
                 // when not enough ammunition
                 System.out.println("No ammunition");
                 input = callOfDutyGame.askInput(weaponRocket, weaponMissile);
            }

            // get the row, column to
            String[] strArray = input.split(",");
            int row = Integer.parseInt(strArray[0]);
            int column = Integer.parseInt(strArray[1]);
            CallOfDutyGame.weapon.shootAt(row, column, base);

            if (base.isGameOver(weaponRocket, weaponMissile)) gameOver = true;
        }
        if(base.win()) System.out.println("You win!");
        else System.out.println("Lose!");

    }

    private String askInput(Weapon weaponRocket, Weapon weaponMissile) {
        System.out.println("RPG: " + weaponRocket.getShotLeft() +
                " Missile: " + weaponMissile.getShotLeft());
        System.out.println("Your current weapon is: " + CallOfDutyGame.weapon.getWeaponType());
        System.out.print("Enter row, column, or q to switch a weapon ");

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        return s;
    }
}
