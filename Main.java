package com.company;
// Abbiram Ramanathan
// String Game
// ICS4U1
// Ms. Strelkovksa

import java.util.Scanner;
import java.io.*;

public class Main {

    // declare constants
    private static final String EMPTY_SPACE = " ";
    private static final String EMPTY_DASH = "_ ";


    // create input scanner
    private static Scanner input = new Scanner(System.in);

    // declare and initialize variables
    private static  int limitOfTries = 5;
    private static int numberOfSentences = 0;
    private static int decision = 0;
    private static int length = 0;
    private static int randomSentenceNum = 0;
    private static int score = 0;
    private static int guessCounter = 0;
    private static int difficultyNum = 0;
    private static int replay = 0;
    private static int counter = 0;
    private static int charGuessed = 0;

    private static String originalSentence = "";
    private static String fileName = "";
    private static String sentence = "";
    private static String blankSentence = "";
    private static String guess = "";
    private static String guessSentence = "";
    private static String adjustedSentence = "";
    private static String guessedLetters = "";
    private static String difficulty = "";

    private static boolean correctInput = true;
    private static boolean doneRound = false;

    // greet user
    private static void greeting() {
        System.out.println("Welcome to Java of Fortune!");
        System.out.println();
    } // end of method greeting()

    // begin game
    private static void start() throws FileNotFoundException {
        menu();
        processDecision();

    }// end of method start()

    // display menu and gather decision from user
    private static void menu() {
        System.out.println("Play [1]");
        System.out.println("Rules [2]");
        System.out.println("Display Score [3]");
        System.out.println("Reset Scoreboard [4]");
        System.out.println("End Game [5]");
        System.out.print("Please enter: ");
        decision = input.nextInt();
        input.nextLine();
        System.out.println();
    }// end of method menu()

    // determine action based on input decision
    private static void processDecision() throws FileNotFoundException {
        while (decision >= 1 && decision <= 5) {
            if (decision == 1) {
                printDifficulty();

                getFileName();
                outputSentence();
                while (guessCounter < limitOfTries) {
                    if (!doneRound) {
                        processGuess();
                    }

                }

            } else if (decision == 2) {
                printRules();
                menu();
            } else if (decision == 3) {
                displayScore();
                menu();
            } else if (decision == 4) {
                resetScore();
                menu();
            } else {
                endGame();
                break;
            }
        }

        // error in input
        while (!(decision >= 1 && decision <= 5)) {
            {
                inputError();
                start();
            }
        }

    }// end of method processDecision()

    // print rules
    private static void printRules() {
        System.out.println("The rules are as follows: ");
        System.out.println("1. A sentence is generated at random by the computer.");
        System.out.println("2. The player's job is to fill the entire sentence by guessing individual letters of the alphabet.");
        System.out.println("3. For every round a player completes, they are awarded points based on the selected difficulty.");
        System.out.println("4. Points awarded range from 1 == Easy, 5 == Medium, 10 == Hard, 25 == Impossible.");
        System.out.println("5. The number of attempts for each difficulty increases and starts at 8 for Easy.");
        System.out.println();
    }// end of method printRules()

    // print difficulty
    private static void printDifficulty() {
        System.out.println();
        System.out.println("Please select a level of difficulty: ");
        System.out.println("Easy [1]");
        System.out.println("Medium [2]");
        System.out.println("Hard [3]");
        System.out.println("Impossible [4]");
        System.out.print("Please enter: ");
        difficulty = input.nextLine();
        calculateNumberOfTries();
        difficultyNum = Integer.parseInt(difficulty);
        System.out.println();
    }// end of method printDifficulty()

    // calculate limit of attempts based on difficulty selected
    private static void calculateNumberOfTries() {
        final int EASY_TRY = 8;
        final int MEDIUM_TRY = 10;
        final int HARD_TRY = 12;
        final int IMPOSSIBLE_TRY = 15;
        while (!(difficulty.equals("1") || difficulty.equals("2") || difficulty.equals("3") || difficulty.equals("4"))) {
            inputError();
            printDifficulty();
        }
        if (difficulty.equals("1")){
            limitOfTries = EASY_TRY;
        }
        else if (difficulty.equals("2")){
            limitOfTries = MEDIUM_TRY;
        }
        else if (difficulty.equals("3")) {
            limitOfTries = HARD_TRY;
        }else {
            limitOfTries = IMPOSSIBLE_TRY;
        }

    }// end of calculateNumberOfTries()

    // determine file to choose based on difficulty selected
    private static String getFileName() {
        //difficulty = getDifficulty();
        fileName = "wheelOfFortune" + difficulty + ".txt";
        return fileName;

    }// end of method String getFileName()

    // reset score to zero
    private static void resetScore() {
        score = 0;
        System.out.println("Score has been reset");
        System.out.println();
    }// end of method resetScore()

    // print current score
    private static void displayScore() {
        System.out.println("Score: " + score);
        System.out.println();
    }// end of method displayScore()

    // determine number of sentences in specific file name
    private static int getNumberOfSentences() throws FileNotFoundException {
        fileName = getFileName();
        Scanner lineScan = new Scanner(new File(fileName));
        while (lineScan.hasNext()) {
            lineScan.nextLine();
            numberOfSentences += 1;
        }
        return numberOfSentences;
    }// end of method int getNumberOfSentences()

    // generate line and output blank spaces
    private static void outputSentence() throws FileNotFoundException {
        //System.out.println(fileName);
        Scanner fileScan = new Scanner(new File(fileName));
        numberOfSentences = getNumberOfSentences();
        //System.out.println(numberOfSentences);
        randomSentenceNum = (int) (Math.random() * numberOfSentences) + 1;
        sentence = "";
        adjustedSentence = "";
        for (int i = 1; i <= randomSentenceNum; i++) {
            sentence = fileScan.nextLine();
            length = sentence.length();

        }
        originalSentence = sentence;
        for (int j = 0; j < length; j++) {
            adjustedSentence += sentence.charAt(j) + " ";
        }
        System.out.println(sentence);
        sentence = adjustedSentence;
        length = sentence.length();

        for (int a = 0; a < length; a++) {
            String character = "";
            character = sentence.charAt(a) + "";
            if (!(character.equals(EMPTY_SPACE))) {
                blankSentence += ("_");

            } else {
                blankSentence += (EMPTY_SPACE);

            }
        }
        System.out.println(blankSentence);
        guessSentence = blankSentence;
        fileScan.close();
        System.out.println("You have " + limitOfTries + " tries to guess the sentence.");

    }// end of method outputSentence()

    // take in guess from user
    private static void getGuess() {
        System.out.print("Please enter one letter from the English alphabet or the entire sentence: ");
        guess = input.nextLine();
        //return guess;

    }// end of method getGuess()

    // determine correct action based on guess from user
    private static void processGuess() throws FileNotFoundException {
        String newGuessSentence = "";

        // determine if round is complete

        if (!(guessSentence.equals(adjustedSentence))) {

            boolean correctInput = true;
            do {               
                do {
                    guess = "";
                    getGuess();
                    if (guess.equalsIgnoreCase(originalSentence)){
                        doneRound = true;
                        correctInput = true;
                        guessCounter++;
                        completeMessage();
                        score = calculateScore();
                        playAgain();                       
                    }
                    // varify guess is single letter
                    if (!(guess.charAt(0) >= 65 && guess.charAt(0) <= 122)) {
                        correctInput = false;
                        inputError();
                    } else if (guess.length() > 1) {
                        correctInput = false;
                        inputError();
                    } else if (guess.length() == 1) {
                        // check if guess has been used already
                        for (int i = 0; i < charGuessed; i++) {

                            if ((guess.charAt(0) + "").equalsIgnoreCase(guessedLetters.charAt(i) + "")) {
                                System.out.println("You have already guessed that letter, please try again!");
                                correctInput = false;
                                break;
                            } else {
                                correctInput = true;
                            }

                        }
                    } else {
                        correctInput = true;
                    }

                } while (!correctInput);
                // convert respective cases of letters
                if (guess.charAt(0) < 97 || guess.charAt(0) > 122) {

                    if (guess.charAt(0) < 90 || guess.charAt(0) > 65) {
                        guess = guess.toLowerCase();
                        correctInput = true;
                        guessedLetters = guessedChar();
                    } else {
                        correctInput = false;
                        inputError();
                    }

                } else if (guess.charAt(0) >= 97 || guess.charAt(0) <= 122) {
                    guessedLetters = guessedChar();
                    correctInput = true;
                } else if (guess.length() > 1) {
                    inputError();
                    correctInput = false;
                }
            } while (!correctInput);
            guessCounter++;

            System.out.println("You have used the letters: " + guessedLetters);
            for (int i = 0; i < length; i++) {
                if (guess.equalsIgnoreCase(sentence.charAt(i) + "")) {
                    if (i == 0) {
                        newGuessSentence += guess.toUpperCase();
                    } else {
                        newGuessSentence += guess;
                    }
                } else if (sentence.equals(EMPTY_SPACE)) {
                    newGuessSentence += EMPTY_SPACE;
                } else if (sentence.equals(EMPTY_DASH)) {
                    newGuessSentence += EMPTY_DASH;
                } else {
                    newGuessSentence += guessSentence.charAt(i);
                }
            }

            if (guessSentence.compareTo(newGuessSentence) == 0) {
                guessError();
            }

            guessSentence = newGuessSentence;
            System.out.println(guessSentence);
        } else {
            doneRound = true;
            completeMessage();
            score = calculateScore();
            playAgain();
        }
        if (guessCounter == limitOfTries){
            gameOver();
            playAgain();
        }

    }// end of method processGuess()

    // calculate score based on round
    private static int calculateScore() {

        if (difficultyNum == 1) {
            score += 1;
        } else if (difficultyNum == 2) {
            score += 5;
        } else if (difficultyNum == 3) {
            score += 10;
        } else if (difficultyNum == 4) {
            score += 25;
        } else {
            score += 0;
        }
        return score;
    }// end of method int calculateScore()

    // display message after round win
    private static void completeMessage() {
        if (guessCounter == 1) {
            System.out.println("Congratulations, you completed this round in " + guessCounter + " guess!");
        }
        else{
            System.out.println("Congratulations, you completed this round in " + guessCounter + " guesses!");

        }
    }// end of method completeMessage()

    // display message after round loss
    private static void gameOver() {
        System.out.println("Oops! You ran out of attempts!");
        System.out.println("The sentence was " + sentence);
    }

    // prompt and decide to play again
    private static void playAgain() throws FileNotFoundException {

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Would you like to go back to the main menu or end the game? (Menu[0]/End Game[1]): ");
            replay = input.nextInt();
            if (replay == 0) {
                validInput = true;
                resetGame();
                start();
            } else if (replay == 1) {
                endGame();
                validInput = true;
            }
            else{
                inputError();
                System.out.println();
            }
        }
        validInput = false;

    }// end of method playAgain()

    // reset all variables to play game
    private static void resetGame() {
        fileName = "";
        sentence = "";
        blankSentence = "";
        guess = "";
        guessSentence = "";
        adjustedSentence = "";
        guessedLetters = "";
        doneRound = false;
        decision = 0;
        length = 0;
        randomSentenceNum = 0;
        limitOfTries = 10;
        guessCounter = 0;
        difficulty = "";
        replay = 0;
        counter = 0;
        numberOfSentences = 0;
        charGuessed = 0;
        difficultyNum = 0;
    }// end of method resetGame()

    // display error message
    private static void guessError() {
        System.out.println("Sorry, that letter is not in the sentence.");

    }// end of method guessError()

    // create list of guessed characters
    private static String guessedChar() {
        guessedLetters += guess + "|";
        charGuessed = guessedLetters.length();
        return guessedLetters;
    }// end of method String guessedChar()

    // display input error message
    private static void inputError() {
        System.out.println("Invalid input, please try again!");

    }// end of method inputError()

    // terminate program
    private static void endGame() {
        System.out.println("Your score was " + score);
        System.out.println("Thank you for playing Java of Fortune!");
        input.close();

        System.exit(0);

    }// end of method endGame()

    // main method
    public static void main(String[] args) throws FileNotFoundException {
        greeting();
        start();

    }// end of method main(String[] args)

}// end of class StringGame