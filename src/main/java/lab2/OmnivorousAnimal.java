package lab2;

public class OmnivorousAnimal extends Animal {

    public OmnivorousAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.ANYTHING, name, weight, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.ANYTHING;
    }
}
