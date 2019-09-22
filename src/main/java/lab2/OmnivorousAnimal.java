package lab2;

public class OmnivorousAnimal extends Animal {

    public OmnivorousAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.ANYTHING, name, weight, foodPerKg);
    }

    public OmnivorousAnimal(FoodType foodType, String name, int weight, int ID, int foodPerKg) {
        super(foodType, name, weight, ID, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.ANYTHING;
    }
}
