import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FirstTest {

    @Test
    public void testSayHello() {
        First first = new First();
        assertTrue(first.sayHello().equals("Hello World"));
    }
}