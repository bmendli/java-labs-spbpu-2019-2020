package lab2;

public class PredatoryAnimal extends Animal {

    public PredatoryAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.FLESH, name, weight, foodPerKg);
    }

    public PredatoryAnimal(FoodType foodType, String name, int weight, int ID, int foodPerKg) {
        super(foodType, name, weight, ID, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.FLESH;
    }
}
