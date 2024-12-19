package solved;

public class PizzaDirector {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }

    public Pizza getPizza() {
        return pizzaBuilder.getPizza();
    }

    public void buildPizza(PizzaSize size) {
        pizzaBuilder.buildSize(size);
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}
