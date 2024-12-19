package solved;

import solved.builders.CapricciosaBuilder;
import solved.builders.MargheritaBuilder;

public class BuildPizzaDemo {
    public static void main(String[] args) {
        PizzaDirector director = new PizzaDirector();

        PizzaBuilder margheritaBuilder = new MargheritaBuilder();
        director.setPizzaBuilder(margheritaBuilder);
        director.buildPizza(PizzaSize.MEDIUM);
        Pizza margherita = director.getPizza();
        System.out.println(margherita);

        PizzaBuilder capricciosaBuilder = new CapricciosaBuilder();
        director.setPizzaBuilder(capricciosaBuilder);
        director.buildPizza(PizzaSize.LARGE);
        Pizza capricciosa = director.getPizza();
        System.out.println(capricciosa);
    }
}
