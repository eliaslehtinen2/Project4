package main;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void attack(Monster target) {
        System.out.println(name + " hyökkää " + target.getType() +" hirviöön!");
        if (target.takeDamage(10)) {
            System.out.println("Hirviöllä on " + target.getHealth() + " elämää jäljellä.");
        }else{
            System.out.println(target.getType() + " on kuollut!");
        }
    }

    public String getName() {
        return name;
    }
}