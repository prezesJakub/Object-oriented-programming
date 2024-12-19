package unsolved;

import unsolved.Ingredient;
import unsolved.PizzaSize;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private List<Ingredient> ingredients = new ArrayList<>();
    private PizzaSize size;

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Pizza with " + ingredients + ", size: " + size;
    }
}
