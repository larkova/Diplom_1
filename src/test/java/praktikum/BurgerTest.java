package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test
    public void setBunsReturnBuns() {
        Burger burger = new Burger();
        Bun bun = new Bun("test", 344);
        burger.setBuns(bun);
        Bun actualBun = burger.bun;
        assertEquals(bun, actualBun);
    }
    @Test
    public void addIngredientReturnIngredientOfBurger(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        List<Ingredient> actualIngredients = burger.ingredients;
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(ingredient);
        assertEquals(expectedIngredients, actualIngredients);
    }
    @Test
    public void removeIngredientReturnIndexOfIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        int actualSize = burger.ingredients.size();
        assertEquals(0, actualSize);

    }
    @Test
    public void moveIngredientReturnIndexOfIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient=new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient newIngredient=new Ingredient(IngredientType.FILLING, "hot sauce", 100);
        burger.addIngredient(ingredient);
        burger.addIngredient(newIngredient);
        burger.moveIngredient(0, 1);
        Ingredient actualIngredient = burger.ingredients.get(1);

        assertEquals(ingredient, actualIngredient);
    }

    @Test
    public void getPriceReturnCorrectPriceForBurger() {
        Burger burger = new Burger(bun, new ArrayList<>());
        Mockito.when(bun.getPrice()).thenReturn(200F);
        Mockito.when(ingredient.getPrice()).thenReturn(300F);
        burger.addIngredient(ingredient);
        assertEquals(700, burger.getPrice(), 0);

    }

    @Test
    public void getReceiptReturnReceiptAndPriceOfBurger() {
        Burger burger=new Burger();
        Bun bun= new Bun("test", 2);
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "test2", 100));
        List<Ingredient> ingredients = new ArrayList<>();
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient: ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals("(==== test ====)\n" +
                "= sauce test2 =\n" +
                "(==== test ====)\n" +
                "\n" +
                "Price: 104,000000\n", burger.getReceipt());


    }

}
