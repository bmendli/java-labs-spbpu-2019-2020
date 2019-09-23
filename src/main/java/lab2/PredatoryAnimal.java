package lab2;

public class PredatoryAnimal extends Animal {

    public PredatoryAnimal(String name, int weight, int foodPerKg) {
        super(FoodType.FLESH, name, weight, foodPerKg);
    }

    public PredatoryAnimal(String name, int weight, int ID, int foodPerKg) {
        super(FoodType.FLESH, name, weight, ID, foodPerKg);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.FLESH;
    }
}
