package lab2;

public class HerbivorousAnimal extends Animal {

    public HerbivorousAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.PLANTS, name, weight, foodPerKg);
    }

    public HerbivorousAnimal(String name, int weight, int ID, int foodPerKg) {
        super(FoodType.PLANTS, name, weight, ID, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.PLANTS;
    }
}
