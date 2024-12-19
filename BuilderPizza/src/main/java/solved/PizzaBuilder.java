package solved;

public interface PizzaBuilder {
    void buildSize(PizzaSize size);
    void buildSauce();
    void buildTopping();
    Pizza getPizza();
}
