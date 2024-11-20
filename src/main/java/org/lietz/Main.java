package org.lietz;

import org.lietz.mastermind.ConsoleColors;
import org.lietz.mastermind.Mastermind;

public class Main {
  public static void main(String[] args) {
    System.out.print(ConsoleColors.CYAN + "Welcome in the world of mastermind!");

    Mastermind mastermind = new Mastermind();
    mastermind.startGame();
  }
}