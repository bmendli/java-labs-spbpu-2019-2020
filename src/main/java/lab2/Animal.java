package lab2;

public abstract class Animal implements Comparable<Animal> {

    private static int countAnimals = 0;
    private FoodType foodType;
    private String name;
    private int weight;
    private int ID;
    private int foodPerKg;

    public Animal(FoodType foodType, String name, int weight, int foodPerKg) {
        this.foodType = foodType;
        this.name = name;
        this.weight = weight;
        this.ID = 1000 + countAnimals++;
        this.foodPerKg = foodPerKg;
    }

    @Override
    public int compareTo(Animal o) {
        if (getFoodAmount() != o.getFoodAmount()) {
            return o.getFoodAmount() - getFoodAmount();
        } else return Integer.compare(0, name.compareTo(o.getName()));
    }

    @Override
    public String toString() {
        return "Animal{"
            + "food type=" + foodType
            + ", name='" + name + '\''
            + ", ID=" + ID
            + ", food amount" + getFoodAmount() + '}';
    }

    public abstract FoodType getFoodType();

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getWeight() {
        return weight;
    }

    public int getFoodPerKg() {
        return foodPerKg;
    }

    public int getFoodAmount() {
        return foodPerKg * weight;
    }
}
