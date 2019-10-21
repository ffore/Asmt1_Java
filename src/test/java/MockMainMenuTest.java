import main.java.MainMenu;
import main.java.SqlDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockMainMenuTest {

    @Mock
    private static SqlDatabase database = mock(SqlDatabase.class);

    @Test
    public void testCanConnectToDatabase() {
        MainMenu menu = new MainMenu(database);
        menu.openDatabaseConnection();
        verify(database, times(1)).connectToDatabase();
    }

    @Test
    public void testCanCloseConnectionToDatabase() throws Exception {
        MainMenu menu = new MainMenu(database);
        menu.closeDatabaseConnection();
        verify(database, times(1)).closeConnection();
    }

}
