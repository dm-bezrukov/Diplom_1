import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {
    @Mock
    Bun bun;

    @Mock
    Ingredient cheese;

    @Mock
    Ingredient onion;

    @Mock
    Ingredient ketchup;

    Burger burger;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.addIngredient(cheese);
        burger.addIngredient(onion);
        burger.addIngredient(ketchup);

        Mockito.when(bun.getPrice()).thenReturn(1.25f);
        Mockito.when(cheese.getPrice()).thenReturn(2.5f);
        Mockito.when(onion.getPrice()).thenReturn(3.0f);
        Mockito.when(ketchup.getPrice()).thenReturn(0.5f);

        Mockito.when(bun.getName()).thenReturn("Булочка с кунжутом");
        Mockito.when(cheese.getName()).thenReturn("Сыр чеддар");
        Mockito.when(onion.getName()).thenReturn("Лук репчатый");
        Mockito.when(ketchup.getName()).thenReturn("Кетчуп");

        Mockito.when(cheese.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(onion.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ketchup.getType()).thenReturn(IngredientType.SAUCE);
    }

    @After
    public void shutdown() {
        burger.ingredients.clear();
        burger.bun = null;
    }

    @Test
    public void setBunSetsBunNotNull() {
        burger.setBuns(bun);
        Assert.assertNotNull("Ожидалось, что булочка будет добавлена", burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        Assert.assertEquals("Ожидалось, что в списке будет 3 ингредиента", 3, burger.ingredients.size());
    }

    @Test
    public void removeIngredientDecreasesListSize() {
        burger.removeIngredient(0);

        Assert.assertEquals("Ожидалось, что в списке будет 2 ингредиента", 2, burger.ingredients.size());
        Assert.assertFalse("Ожидалось, что в списке не будет найден сыр", burger.ingredients.contains(cheese));
    }

    @Test
    public void moveIngredientChangesIngredientIndex() {
        burger.moveIngredient(0, 2);

        Assert.assertEquals("Ожидалось, что индекс ингредиента СЫР будет равен 2",
                2, burger.ingredients.indexOf(cheese));
    }

    @Test
    public void getPriceReturnsSumOfAllIngredients() {
        float ingredientsSum = bun.getPrice() * 2 + cheese.getPrice() + onion.getPrice() + ketchup.getPrice();
        burger.setBuns(bun);
        Assert.assertEquals("Ожидалось, что цена будет равна " + ingredientsSum, ingredientsSum,
                burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptReturnsAllIngredientsString() {
        String expectedReceipt = "(==== Булочка с кунжутом ====)\n" +
                "= filling Сыр чеддар =\n" +
                "= filling Лук репчатый =\n" +
                "= sauce Кетчуп =\n" +
                "(==== Булочка с кунжутом ====)\n" +
                "\n" +
                "Price: 8.500000\n";

        burger.setBuns(bun);
        Assert.assertEquals("Не получен ожидаемый рецепт", expectedReceipt, burger.getReceipt());
    }
}