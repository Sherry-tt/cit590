import java.util.Scanner;
public class HelloWorld {
    /**
     *1. Say Hello
     * a. Ask the user to enter their full name.
     * b. The user should type in their first name and last name, separated by a space.
     * i. Print “Hello, <full name>!” where <full name> gets replaced by the full name of the user.
     */
    public static String sayHello () {
        System.out.println("Please enter your full name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Hello,"+ name + "!");
        return name;
    }


    /**
     *2. Add Five Numbers
     * a. Ask the user to enter a total of 5 numbers (ints or doubles), and hit enter after each. Assume each number is an int or a double.
     * b. Print the sum (as a double) of all the numbers each time.
     */
    public static void askNumber () {
        System.out.println("Please enter a total of 5 numbers");
        Scanner scan = new Scanner(System.in);
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int num = scan.nextInt();
            sum += num;
            System.out.println("Sum: "+ (double)sum);
        }
    }


    /**
     * 3. Even or Odd
     * a. Ask the user to enter an integer.
     * b. Check if the number is even or odd. Assume this will be a positive integer.
     *       i. If it is even, print “<number> is even”, where <number> gets replaced by the number.
     *       ii. If it is odd, print “<number> is odd”, where <number> gets replaced by the number.
     */
    public static void checkNumber() {
        System.out.println("Please enter an integer");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        if (num % 2 == 0) {
            System.out.println(num + " is even");
        } else {
            System.out.println(num + " is odd");
        }
    }

    /**
     *4. Prime or Composite
     * a. Ask the user to enter a positive integer. Assume this will be a positive integer.
     * b. Check if the number is prime or composite.
     *      i. If it is prime, print “<number> is prime”, where <number> gets replaced by the number.
     *      ii. If it is composite, print “<number> is composite”, where <number> gets replaced by the number.
     *      iii. If the number is 1, print 1.
     */
    public static void check_Prime_or_Composite() {
        System.out.println("Please enter an positive integer");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        if (num <= 3) {
            if (num == 1) System.out.println(num);
            else System.out.println(num + " is prime");
        } else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    System.out.println(num + " is composite");
                    return;
                }
            }
            System.out.println(num + " is prime");
        }
    }

    /**
     * 5. Convert Seconds to Time
     * a. Ask the user to enter some number of seconds (as an int) and convert it to hours:minutes:seconds.
     *  i. For example, if input seconds is 1432, print output in the format: 0:23:52
     *  ii. If input seconds is 0, print output in the format: 0:0:0
     *  iii. If input seconds is negative, print output in the format: -1:-1:-1
     */
    public static void convertTime() {
        System.out.println("Please enter some number of seconds (as an int)");
        Scanner in = new Scanner(System.in);
        int seconds = in.nextInt();

        if(seconds <= 0) {
            if(seconds == 0) System.out.println("0:0:0");
            else System.out.println("-1:-1:-1");
            return;
        }
        //seconds
        int t1 = seconds % 60;
        int t2 = seconds / 60;
        //hours
        int t3 = t2 / 60;
        //minutes
        t2 = t2 % 60;
        System.out.println(t3+":" + t2 + ":" + t1);
    }

    /**
     * 6. Say Goodbye
     * a. Print “Goodbye, <full name>!” where <full name> gets replaced by the full name of the user.
     */
    public static void goodBye(String name) {
        System.out.println("Goodbye, "+ name + "!");
    }



    public static void main(String[] args) {
        // String name = sayHello();
        /*
         *1. Say Hello
         * a. Ask the user to enter their full name.
         * b. The user should type in their first name and last name, separated by a space.
         * i. Print “Hello, <full name>!” where <full name> gets replaced by the full name of the user.
         */
        System.out.println("Please enter your full name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Hello,"+ name + "!");

        System.out.println();
        System.out.println("----------");
        System.out.println();


        //askNumber ();
        /*
         *2. Add Five Numbers
         * a. Ask the user to enter a total of 5 numbers (ints or doubles), and hit enter after each. Assume each number is an int or a double.
         * b. Print the sum (as a double) of all the numbers each time.
         */
        System.out.println("Please enter a total of 5 numbers");
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int num = scan.nextInt();
            sum += num;
            System.out.println("Sum: "+ (double)sum);
        }

        System.out.println();
        System.out.println("----------");
        System.out.println();

        //checkNumber();
        /*
         * 3. Even or Odd
         * a. Ask the user to enter an integer.
         * b. Check if the number is even or odd. Assume this will be a positive integer.
         *       i. If it is even, print “<number> is even”, where <number> gets replaced by the number.
         *       ii. If it is odd, print “<number> is odd”, where <number> gets replaced by the number.
         */
        System.out.println("Please enter an integer");
        int num = scan.nextInt();
        if (num % 2 == 0) {
            System.out.println(num + " is even");
        } else {
            System.out.println(num + " is odd");
        }

        System.out.println();
        System.out.println("----------");
        System.out.println();


        //check_Prime_or_Composite();
        /*
         *4. Prime or Composite
         * a. Ask the user to enter a positive integer. Assume this will be a positive integer.
         * b. Check if the number is prime or composite.
         *      i. If it is prime, print “<number> is prime”, where <number> gets replaced by the number.
         *      ii. If it is composite, print “<number> is composite”, where <number> gets replaced by the number.
         *      iii. If the number is 1, print 1.
         */
        System.out.println("Please enter an positive integer");
        num = scan.nextInt();
        if (num <= 3) {
            if (num == 1) System.out.println(num);
            else System.out.println(num + " is prime");
        } else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    System.out.println(num + " is composite");
                    break;
                } else if( i == num - 1) {
                    System.out.println(num + " is prime");
                }
            }
        }

        System.out.println();
        System.out.println("----------");
        System.out.println();

        //convertTime();
        /*
         * 5. Convert Seconds to Time
         * a. Ask the user to enter some number of seconds (as an int) and convert it to hours:minutes:seconds.
         *  i. For example, if input seconds is 1432, print output in the format: 0:23:52
         *  ii. If input seconds is 0, print output in the format: 0:0:0
         *  iii. If input seconds is negative, print output in the format: -1:-1:-1
         */
        System.out.println("Please enter some number of seconds (as an int)");
        Scanner in = new Scanner(System.in);
        int seconds = in.nextInt();

        if(seconds <= 0) {
            if(seconds == 0) System.out.println("0:0:0");
            else System.out.println("-1:-1:-1");
            return;
        }
        //seconds
        int t1 = seconds % 60;
        int t2 = seconds / 60;
        //hours
        int t3 = t2 / 60;
        //minutes
        t2 = t2 % 60;
        System.out.println(t3+":" + t2 + ":" + t1);


        System.out.println();
        System.out.println("----------");
        System.out.println();

        //goodBye(name);
        /*
         * 6. Say Goodbye
         * a. Print “Goodbye, <full name>!” where <full name> gets replaced by the full name of the user.
         */
        System.out.println("Goodbye, "+ name + "!");
        scan.close();
    }
}
