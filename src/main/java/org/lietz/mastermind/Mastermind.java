package org.lietz.mastermind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Mastermind {
  private static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "PURPLE"};
  private static final int CODE_LENGTH = 4;
  private static final int MAX_ATTEMPTS = 8;
  private Set<String> secretCode;

  public Mastermind() {
    generateSecretCode();
  }

  private void generateSecretCode() {
    Random random = new Random();
    secretCode = new HashSet<>();

    String color = COLORS[random.nextInt(COLORS.length)];
    secretCode.add(color);

    while (secretCode.size() < CODE_LENGTH) {

      color = COLORS[random.nextInt(COLORS.length)];

      secretCode.add(color);
    }

   // System.out.println(secretCode + " generated");
    System.out.println( " [secret code generated]");
  }

  private List<String> getUserGuess() {
    Scanner scanner = new Scanner(System.in);
    List<String> guess = new ArrayList<>();

    System.out.println(ConsoleColors.CYAN + "Please enter your guess! " + ConsoleColors.RESET);

    while (guess.size() < CODE_LENGTH) {
      String color = scanner.nextLine().trim().toUpperCase();
      if (color.equals(COLORS[0]) || color.equals(COLORS[1]) || color.equals(COLORS[2]) || color.equals(COLORS[3])
      ||color.equals(COLORS[4])||color.equals(COLORS[5])) {
        guess.add(color);
      } else {
        System.out.println("You need to choose the exact color from the list.");
      }

    }
    return guess;
  }

  private String checkForColorAndPosition(List<String> guess) {
    int correctPosition = 0;
    int correctColor = 0;

    for (int i = 0; i < CODE_LENGTH; i++) {
      if (guess.get(i).equals(secretCode.stream().toList().get(i))) {
        correctPosition++;
      }
    }

    for (String color : guess) {
      if (color != null && secretCode.contains(color)) {
        correctColor++;
      }
    }
    return "Correct color in the good position: " + correctPosition + ", Correct color (in the wrong position): " + correctColor;
  }

  public void startGame() {

    int attempts = 0;

    System.out.println(ConsoleColors.CYAN + "Let's start the game! Choose 4 colours from"
        + ConsoleColors.RED +" Red, " + ConsoleColors.GREEN + "Green, "
        + ConsoleColors.YELLOW + "Yellow, " + ConsoleColors.BLUE +  "Blue, "
        + ConsoleColors.ORANGE + "Orange, " + ConsoleColors.PURPLE + "Purple."
        + ConsoleColors.RESET);

    while (attempts < MAX_ATTEMPTS) {
      List<String> guess = getUserGuess();
      String feedback = checkForColorAndPosition(guess);
      System.out.println(feedback);

      if (guess.equals(new ArrayList<>(secretCode))) {
        System.out.println(ConsoleColors.CYAN + "Congratulations! You cracked the code!");
        break;
      } else {
        attempts++;
        System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));

      }
      if(MAX_ATTEMPTS - attempts ==0){
        System.out.println("Game over. ");
      }
    }
    System.out.println("The correct code was: " + secretCode);
  }

}