package solved.builders;

import solved.Ingredient;
import solved.Pizza;
import solved.PizzaBuilder;
import solved.PizzaSize;

public class PepperoniBuilder implements PizzaBuilder {
    private Pizza pizza = new Pizza();

    @Override
    public void buildSize(PizzaSize size) {
        pizza.setSize(size);
    }

    @Override
    public void buildSauce() {
        pizza.addIngredient(Ingredient.TOMATO_SAUCE);
    }

    @Override
    public void buildTopping() {
        pizza.addIngredient(Ingredient.CHEESE);
        pizza.addIngredient(Ingredient.PEPPERONI);
    }

    @Override
    public Pizza getPizza() {
        return pizza;
    }
}
