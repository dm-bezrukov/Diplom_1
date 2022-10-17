import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    private Bun bun;

    @Before
    public void init() {
        bun = new Bun("Булочка", 100.25f);
    }

    @Test
    public void getNameReturnsBunName() {
        Assert.assertEquals("Неверное имя", "Булочка", bun.getName());
    }

    @Test
    public void getPriceReturnsBunPrice() {
        Assert.assertEquals("Неверная цена", 100.25f, bun.getPrice(), 0);
    }
}