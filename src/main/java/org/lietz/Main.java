package org.lietz;

import org.lietz.mastermind.ConsoleColors;
import org.lietz.mastermind.Mastermind;

public class Main {
  public static void main(String[] args) {
    System.out.print(ConsoleColors.CYAN + "\n Welcome to the world of Mastermind!\n The computer generates a code for you. " +
        "Your task is to crack it! You need to choose four colours and put them in the correct sequence.\n" +
        " You have 8 attempts. Please confirm with [ENTER] after each color.\n" +
        " Good luck!!!\n\n");

    Mastermind mastermind = new Mastermind();
    mastermind.startInteraction();
   // mastermind.startGame();
  }
}