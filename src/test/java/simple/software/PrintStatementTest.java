package simple.software;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PrintStatementTest {

    @Mock
    Console console;

    @BeforeEach
    public void initialize() {
        Account cut = new AccountImpl();
    }

    @Test
    void print_statement_containing_all_transactions() {
        final var inOrder = inOrder(console);

       inOrder.verify(console).printLine("Date || Amount || Balance");
       inOrder.verify(console).printLine("14/01/2012 || -500   || 2500");
       inOrder.verify(console).printLine("13/01/2012 || 2000   || 3000");
       inOrder.verify(console).printLine("10/01/2012 || 1000   || 1000");
    }

}