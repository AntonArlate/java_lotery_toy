package Repo;

public class Toy {
    private int id;
    private String name;
    private int amount;
    private int chance;
    private String contestant;

    public Toy(int id, String name, int amount, int chance) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.chance = chance;
        this.contestant = "";
    }

    public Toy(int id, String name, String contestant) {
        this.id = id;
        this.name = name;
        this.amount = 0;
        this.chance = 0;
        this.contestant = contestant;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getChance() {
        return chance;
    }

    public String getContestant() {
        return contestant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setContestant(String contestant) {
        this.contestant = contestant;
    }

    public String[] getToyAsString(){
        return new String[] {
                Integer.toString(this.id),
                this.name,
                Integer.toString(this.amount),
                Integer.toString(this.chance)
        };
    }

    public String[] getPrizeAsString(){
        return new String[] {
                Integer.toString(this.id),
                this.name,
                this.contestant
        };
    }
}
