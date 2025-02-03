package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Syötä pelaajan nimi:");
        Player player = new Player(scanner.nextLine());
        Cave cave = new Cave(player);

        boolean exit = false;
        while (!exit) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Anna hirviön tyyppi:");
                    String type = scanner.nextLine();
                    System.out.println("Anna hirviön elämän määrä numerona:");
                    int health = Integer.parseInt(scanner.nextLine());
                    cave.addMonster(new Monster(type, health));
                    break;

                case 2:
                    System.out.println("Luolan hirviöt:");
                    cave.listMonsters();
                    break;

                case 3:
                    if (cave.getMonsterCount() == 0) {
                        break;
                    }
                    System.out.println("Valitse hirviö, johon hyökätä:");
                    cave.listMonsters();
                    int monsterChoice = Integer.parseInt(scanner.nextLine());
                    cave.attackMonster(monsterChoice);
                    break;

                case 4:
                    System.out.println("Anna tiedoston nimi, johon peli tallennetaan:");
                    Cave.saveGame(cave, scanner.nextLine());
                    System.out.println("Peli tallennettiin tiedostoon save.game.");
                    break;

                    case 5:
                    System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                    String loadFile = scanner.nextLine();
                    Cave loadedCave = Cave.loadGame(loadFile);
                    if (loadedCave != null) {
                        cave = loadedCave;
                        System.out.println("Peli ladattu tiedostosta " + loadFile + ". Tervetuloa takaisin, " + cave.getPlayerName() + ".");
                    }
                    break;

                case 0:
                    System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }
}