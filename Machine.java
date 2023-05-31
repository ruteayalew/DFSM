import java.util.Scanner;

public class Machine {
    public Scanner scnr = new Scanner (System.in);
    public int currentState;
    public char currentChar;
    public char secondChar;//1st digit
    public char thirdChar;//2nd digit
    public char genChar; //general digit for scanning whole word
    public Machine(){

    }

    public int check0(String word){
        //check0 is the initial checking point using recursive calls to see if the word is the minimum word length of 5
        //and if the characters are in the alphabet
        if(word.length()<5){
            System.out.println("Your word is too short. Please try again with a new input:");
            word=scnr.nextLine();
            check0(word);}
        //if any of the digits are not in the alphabet, reject and recursive call
        for(int i = 0; i < word.length(); i++) {
            genChar = (word.charAt(i));
            if (!(genChar == '0' || genChar == '1' || genChar == '2')) {
                System.out.println("Your word has an unacceptable character. Please try again with a new input:");
                word = scnr.nextLine();
                check0(word);}
        }
        currentState=0;
        System.out.println("Your word has passed the initial check and is currently in state 0");
        currentChar=getChar(0,word);
        nextState(word, currentChar, currentState);
        return currentState;
    }

    public int nextState(String word, char currentChar, int currentState){
        // this method contains the full DFSM algorithm

        if(currentState==0){
            //STATE 0 CHECK: Evaluates the first 3 characters
            //Grabbing the 2nd and 3rd characters
            secondChar = word.charAt(1);
            thirdChar = word.charAt(2);
            System.out.println("Your first three digits are: " + currentChar + secondChar + thirdChar);
            // If word does NOT start with either 10 or 2, reject
            if(currentChar=='1' && secondChar=='0') {
                currentState=2;
                System.out.println("Your word starts with 10 so it is moving onto State: " + currentState);
            }
            else if(currentChar=='2'){
                currentState=1;
                System.out.println("Your word starts with 2 so it is moving onto State: " + currentState);
            }
            else{
                System.out.println("Your word does not start with either 10 or 2. \nPlease try again with a new input:");
                word=scnr.nextLine();
                check0(word);
            }
            //END OF STATE 0 CHECK
            System.out.println("Your word is now in state: " + currentState);
        }

        //STATE 1 CHECK
        if(currentState==1) {
            //if the first digit is 2, if the following are NOT 10, reject
            if (currentChar == '2') {
                System.out.println("Now since your word starts with 2...");
                if (!(secondChar == '1' && thirdChar == '0')) {
                    System.out.println("Your word is rejected because 2 is not followed by 10. \nPlease try again with a new input:");
                    word = scnr.nextLine();
                    check0(word);
                } else {
                    currentState = 2;
                    System.out.println("And 2 is followed by 10, your word is moving onto State: " + currentState);}
                } else { //if the first digit is not a 2, then auto move to State 2
                currentState = 2;
                System.out.println("Since your word starts with 10, it is automatically passed onto State: " + currentState);}
            //END OF STATE 1 EVALUATION
        }
        //STATE 2 CHECK
        int twoCounter=0; //track occurance of 2 in order to move to state 3
        int indexTrack=0; //find index of 2 if found
        if(currentState==2){
            for(int i=1;i<word.length();i++){ //loop to find repitions of 10, start at index 2 to avoid initial 2 if exists
                if(word.charAt(i)=='1'&&word.charAt(i+1)=='0'){//if there is a 1, it must be followed by a 0
                    System.out.println("I see repition of 10");}
                else if (word.charAt(i)=='1'&&word.charAt(i+1)!='0'){//if a 1 is not followed by a 0, reject
                    System.out.println("Your word is rejected because a 0 is not preceeded by 1. \nPlease try again with a new input:");
                    word = scnr.nextLine();
                    check0(word);}
                if(word.charAt(i)=='2'){ //move onto state 3 once a 2 is found
                    System.out.println("I see a 2! You are now moving onto State 3...");
                    twoCounter++;
                    indexTrack=i;
                    currentState=3; break;}
            }
            if(twoCounter==0){ //if there was no 2 after the iteration, reject
                System.out.println("There is no 2 in the middle of your word so it is rejected.\nPlease try again with a new input: ");
                word = scnr.nextLine();
                check0(word);}
        }
        //STATE 3 CHECK
        if(currentState==3){
            //use (index of previously found 2)+1 as starting point of iteration to find "01" to move into State 4
            for(int i=indexTrack+1;i<word.length();i++){
                if(word.charAt(i)=='0'&&word.charAt(i+1)=='1'){//if there is a 1 that is followed by 0, move forward
                    System.out.println("I see an occurance of 01, so your word is moving onto State 4");
                    currentState=4;
                    indexTrack=i+1;
                    break;}
                else {//reject all else
                    System.out.println("Your word is rejected because there is no occurance of 01. \nPlease try again with a new input:");
                    word = scnr.nextLine();
                    check0(word);}
            }
        }
        //STATE 4 CHECK
        if(currentState==4){
            System.out.println("Congradulations your word has reached the acceptance State 4");
            for(int i=indexTrack;i<word.length();i++){ //loop to find repitions of 10, start at index 2 to avoid initial 2 if exists
                if(word.charAt(i)=='0'&&word.charAt(i+1)=='1'){//if there is a 1, it must be followed by a 0
                    System.out.println("I see repition of 01");}
                if(word.charAt(i)=='2'){
                    System.out.println("Discovered a 2! Your word has progressed into acceptance State 5");
                }
            }
        }

        return currentState;
    }


    public char getChar(int index, String word){
        currentChar = (word.charAt(index));
        return currentChar;
    }

}
