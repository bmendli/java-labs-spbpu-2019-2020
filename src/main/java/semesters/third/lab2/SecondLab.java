package semesters.third.lab2;

import org.apache.commons.io.LineIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecondLab {

    private List<Animal> animals;

    public SecondLab() {
        animals = new ArrayList<>();
    }

    public void read(String file) throws FileNotFoundException {
        List<Animal> list = new ArrayList<>();

        LineIterator li = new LineIterator(new InputStreamReader(
            new FileInputStream(file),
            StandardCharsets.UTF_8)
        );

        int ID;
        String name;
        String foodTypeString;
        int foodAmount;
        int weight;
        int foodPerKg;
        FoodType foodType;

        while (li.hasNext()) {
            ID = Integer.parseInt(li.nextLine());
            name = li.nextLine();
            foodTypeString = li.nextLine();
            foodAmount = Integer.parseInt(li.nextLine());
            weight = Integer.parseInt(li.nextLine());
            foodPerKg = Integer.parseInt(li.nextLine());

            if (li.hasNext()) {
                li.nextLine();
            }

            if ((name == null)
                || (foodTypeString == null)
                || (weight < 0)
                || (foodPerKg < 0)
                || (foodAmount != foodPerKg * weight)
                || (ID < 100000)
                || (ID > 999999)) {
                throw new IllegalArgumentException("Incorrect data");
            }

            switch (foodTypeString.toUpperCase()) {
                case "ANYTHING": {
                    foodType = FoodType.ANYTHING;
                    break;
                }
                case "FLESH": {
                    foodType = FoodType.FLESH;
                    break;
                }
                case "PLANTS": {
                    foodType = FoodType.PLANTS;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Incorrect data about food type");
                }
            }

            switch (foodType) {
                case ANYTHING: {
                    list.add(
                        new OmnivorousAnimal(
                            name,
                            weight,
                            ID,
                            foodPerKg
                        )
                    );
                    break;
                }
                case FLESH: {
                    list.add(
                        new PredatoryAnimal(
                            name,
                            weight,
                            ID,
                            foodPerKg
                        )
                    );
                    break;
                }
                case PLANTS: {
                    list.add(
                        new HerbivorousAnimal(
                            name,
                            weight,
                            ID,
                            foodPerKg
                        )
                    );
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Something wrong");
                }
            }
        }
        li.close();
        animals.addAll(list);
    }

    public void write(String file) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        StringBuilder result = new StringBuilder();
        animals.forEach(
            (animal) -> result
                .append(animal.getID()).append("\n")
                .append(animal.getName()).append("\n")
                .append(animal.getFoodType()).append("\n")
                .append(animal.getFoodAmount()).append("\n")
                .append(animal.getWeight()).append("\n")
                .append(animal.getFoodPerKg()).append("\n\n")
        );
        fw.write(result.toString());
        fw.close();
    }

    public static void main(String[] args) {
        SecondLab secondLab = new SecondLab();
        secondLab.animals.add(
            new HerbivorousAnimal(
                "Vasya",
                56,
                2
            )
        );
        secondLab.animals.add(
            new PredatoryAnimal(
                "Misha",
                67,
                4
            )
        );
        secondLab.animals.add(
            new PredatoryAnimal(
                "Lisichka",
                45,
                3
            )
        );
        secondLab.animals.add(
            new OmnivorousAnimal(
                "Enot",
                7,
                1
            )
        );
        secondLab.animals.add(
            new PredatoryAnimal(
                "Volk",
                50,
                3
            )
        );
        secondLab.animals.add(
            new PredatoryAnimal(
                "Leopard",
                55,
                3
            )
        );
        secondLab.animals.add(
            new HerbivorousAnimal(
                "Lyolya",
                20,
                2
            )
        );
        secondLab.animals.add(
            new OmnivorousAnimal(
                "Sharik",
                23,
                1
            )
        );
        secondLab.animals.add(
            new HerbivorousAnimal(
                "Slon",
                200,
                7
            )
        );
        secondLab.animals.add(
            new HerbivorousAnimal(
                "Zayac",
                9,
                1
            )
        );

        try {
//            secondLab.read("in.txt");

            secondLab.sort();

            //Вывод первых 5 имён в списке
            secondLab
                .animals
                .stream()
                .limit(5)
                .forEach((animal) -> System.out.println(animal.getName()));

            System.out.println();

            //Вывод последних 3 ID в списке
            secondLab
                .animals
                .stream()
                .skip(secondLab.animals.size() - 3)
                .limit(3)
                .forEach((animal) -> System.out.println(animal.getID()));

            secondLab.write("out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        animals.sort(
            (animal1, animal2) -> {
                if (animal1.getFoodAmount() != animal2.getFoodAmount()) {
                    return animal2.getFoodAmount() - animal1.getFoodAmount();
                } else return Integer.compare(0, animal1.getName().compareTo(animal2.getName()));
            }
        );
    }

    public List<Animal> secondTask() {
        return animals
            .stream()
            .skip(animals.size() - 3)
            .limit(3)
            .collect(Collectors.toList());
    }

    public List<Animal> firstTask() {
        return animals
            .stream()
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
