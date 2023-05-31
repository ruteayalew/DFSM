/*
* @author: Rute Ayalew
* @date: February 18th 2023
* @description: This program implements a deterministic finite state machine to accept or reject given words
 by the established set of rules below:
            1. An optional 2 at the beginning of the word
            2. After the optional 2 if it is present, or at the beginning of the word if the optional 2 is not present
                    a. One or more instances of 10, followed by
                    b. One 2 symbol, followed by
                    c. One or more instance of 01
            6. An optional 2 at the end of the word
*/
import java.util.Scanner;

public class DFSM {

    public static Scanner scnr = new Scanner (System.in);

    public static void main(String[] args) {

        System.out.println("Hello! Welcome to Rute's DFSM \nPlease type a number using digits {0,1,2} \n...or not :) ");

        String word = scnr.nextLine();
        Machine machine = new Machine();

        machine.check0(word);
    }
}
