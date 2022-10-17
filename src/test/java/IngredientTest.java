import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType ingredientType;
    private final String name;
    private final float price;

    private Ingredient ingredient;

    public IngredientTest(IngredientType ingredientType, String name, float price) {
        this.ingredientType = ingredientType;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {IngredientType.FILLING, "сыр", 10.25f},
                {IngredientType.SAUCE, "кетчуп", 11.65f}
        };
    }

    @Before
    public void initializeIngredient() {
        ingredient = new Ingredient(ingredientType, name, price);
    }
    @Test
    public void getNameReturnsIngredientName() {
        Assert.assertEquals("Ожидалось - " + name, name, ingredient.getName());
    }

    @Test
    public void getPriceReturnIngredientPrice() {
        Assert.assertEquals("Ожидалось - " + price, price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void getTypeReturnsIngredientType() {
        Assert.assertEquals("Ожидалось - " + ingredientType.toString(), ingredientType, ingredient.getType());
    }
}
