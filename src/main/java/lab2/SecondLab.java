package lab2;

import java.util.ArrayList;
import java.util.List;

public class SecondLab {
    List<Animal> animals = new ArrayList<>();

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

        secondLab.animals.sort(
            (animal1, animal2) -> {
                if (animal1.getFoodAmount() != animal2.getFoodAmount()) {
                    return animal2.getFoodAmount() - animal1.getFoodAmount();
                } else return Integer.compare(0, animal1.getName().compareTo(animal2.getName()));
            }
        );

        //Вывод первых 5 имён в списке
        secondLab
            .animals
            .stream()
            .limit(5)
            .forEach((animal) -> System.out.println(animal.getName()));

        //Вывод последних 3 ID в списке
        secondLab
            .animals
            .stream()
            .skip(secondLab.animals.size() - 3)
            .limit(5)
            .forEach((animal) -> System.out.println(animal.getID()));
    }
}
