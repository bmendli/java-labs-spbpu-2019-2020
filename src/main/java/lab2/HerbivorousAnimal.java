package lab2;

public class HerbivorousAnimal extends Animal {

    public HerbivorousAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.PLANTS, name, weight, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.PLANTS;
    }
}
