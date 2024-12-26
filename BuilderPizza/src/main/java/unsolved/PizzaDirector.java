package unsolved;

import unsolved.Pizza;
import unsolved.PizzaBuilder;
import unsolved.PizzaSize;

public class PizzaDirector {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }

    public Pizza getPizza() {

        return pizzaBuilder.getPizza();
    }

    public void buildPizza(PizzaSize size) {

    }
}
