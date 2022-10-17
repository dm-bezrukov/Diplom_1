import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.List;

public class DatabaseTest {
    Database database;

    @Before
    public void init(){
        database = new Database();
    }
    @Test
    public void availableBunsReturnsListOfSize3() {
        List<Bun> buns = database.availableBuns();
        Assert.assertFalse("Ожидался не пустой список",buns.isEmpty());
        Assert.assertEquals("Ожидался список из 3-х элементов",3, buns.size());
    }

    @Test
    public void availableIngredientsReturnsListOfSize6() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertFalse("Ожидался не пустой список", ingredients.isEmpty());
        Assert.assertEquals("Ожидался список из 6 элементов", 6, ingredients.size());
    }
}