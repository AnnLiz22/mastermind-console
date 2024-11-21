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
  private String userName = "Dear";

  public void setUserName(String userName) {
    this.userName = userName;
  }

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

    System.out.println(secretCode + " generated");
    System.out.println(" <<<<< secret code generated >>>>>\n");
  }

  public void startInteraction() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    System.out.printf("Nice to meet you, %s! Wanna play a game? ;-)\n [yes / no]", name);
    String userAnswer = scanner.nextLine();

    if (userAnswer.toLowerCase().contains("yes")) {
      System.out.println("Let's start then!");
      setUserName(name);
      startGame();

    } else if (userAnswer.toLowerCase().contains("no")) {
      System.out.println("If you change your mind, you know where tu find me!");
    }
    scanner.close();
  }

  private List<String> getUserGuess() {

    Scanner scanner = new Scanner(System.in);
    List<String> guess = new ArrayList<>();

    while (guess.size() < CODE_LENGTH) {
      String color = scanner.nextLine().trim().toUpperCase();
      if ((!guess.contains(color)) && (color.equals(COLORS[0]) || color.equals(COLORS[1]) || color.equals(COLORS[2])
          || color.equals(COLORS[3]) || color.equals(COLORS[4]) || color.equals(COLORS[5]))) {
        guess.add(color);

      } else {
        System.out.printf("%s, you need to choose the exact color from the list. Colors can't duplicate.", userName);
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

    if (correctColor == 2 && correctPosition == 0) {
      System.out.printf("That was not your best shoot, %s. Try again.\n", userName);
    } else if (correctColor == 4 && correctPosition == 2) {
      System.out.printf("Not bad, %s! Keep it up and you will crack the code!\n", userName);
    } else if (correctPosition == 3) {
      System.out.printf("You are close to crack the code, %s!\n", userName);
    }

    return "Correct color in the good position: " + correctPosition + ". Correct color (in the wrong position): " +
        correctColor + ". ";
  }

  public void startGame() {

    int attempts = 0;
    String setOfColors =
        ConsoleColors.RED + " Red, " + ConsoleColors.GREEN + "Green, "
            + ConsoleColors.YELLOW + "Yellow, " + ConsoleColors.BLUE + "Blue, "
            + ConsoleColors.ORANGE + "Orange, " + ConsoleColors.PURPLE + "Purple."
            + ConsoleColors.RESET;

    System.out.println(ConsoleColors.CYAN + userName + ", please choose four colours from: "
        + setOfColors
        + ConsoleColors.RESET);

    while (attempts < MAX_ATTEMPTS) {
      List<String> guess = getUserGuess();
      String feedback = checkForColorAndPosition(guess);
      System.out.println(feedback);

      if (guess.equals(new ArrayList<>(secretCode))) {
        System.out.printf(ConsoleColors.CYAN + "Congratulations, %s! You cracked the code!", userName);
        break;
      } else {
        attempts++;
        System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
        System.out.println("Choose from: " + setOfColors);
      }

      if (MAX_ATTEMPTS - attempts == 0) {
        System.out.printf("Game over, %s. ", userName);
      }
    }
    System.out.println("The correct code was: " + secretCode);
  }

}