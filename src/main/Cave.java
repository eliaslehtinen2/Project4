package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class Cave implements Serializable {
    private Player player;
    private ArrayList<Monster> monsters;

    public Cave(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void listMonsters() {
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).printInfo(i + 1);
        }
    }

    public void attackMonster(int choice) {
        if (choice < 1) {
            return;
        }
        if (choice > monsters.size()) {
            return;
        }

        Monster target = monsters.get(choice - 1);
        player.attack(target);

        if (target.getHealth() <= 0) {
            monsters.remove(choice - 1);
        }
    }
    public int getMonsterCount() {
        return monsters.size();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public static void saveGame(Cave cave, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(cave);
            out.close();
        } catch (IOException e) {
        }
    }

    public static Cave loadGame(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Cave loadedCave = (Cave) in.readObject();
            in.close();
            return loadedCave;
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return null;
    }
}

