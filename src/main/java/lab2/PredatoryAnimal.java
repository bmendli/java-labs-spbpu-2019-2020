package lab2;

public class PredatoryAnimal extends Animal {

    public PredatoryAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.FLESH, name, weight, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.FLESH;
    }
}
