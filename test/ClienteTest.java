import Controller.ControllerCliente;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import java.util.Calendar;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author giova
 */
public class ClienteTest {
    private ClientePF pessoa;
    private ClientePJ empresa;
    private ControllerCliente cliente;
            
    @Before
    public void setUp(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, Calendar.MAY, 28);

        pessoa = new ClientePF(5, calendar.getTime(), "Teste Pessoa", "12345678912", 123456789);
        empresa = new ClientePJ(7, calendar.getTime(), "Teste Empresa", "1223456300189", 123456789);
        cliente = new ControllerCliente();
    }
    
    @Test
    public void persistirBancoInsertPFTest(){
        cliente.persistirBanco(pessoa, true, false, false);
        
        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(pessoa.getIdCliente());
        
        // Verificar se os dados do cliente são os esperados
        assertEquals(pessoa.getNome(), result.getNome());
        assertEquals(pessoa.getCPF(), ((ClientePF) result).getCPF());
        assertEquals(pessoa.getIdentidade(), ((ClientePF) result).getIdentidade());
    }
    
    @Test
    public void persistirBancoInsertPJTest(){
        cliente.persistirBanco(empresa, true, false, false);
        
        // Buscar o cliente do banco para verificar se foi inserido corretamente
        Cliente result = cliente.getCliente(empresa.getIdCliente());
        
        // Verificar se os dados do cliente são os esperados
        assertEquals(empresa.getNome(), result.getNome());
        assertEquals(empresa.getCnpj(), ((ClientePJ) result).getCnpj());
        assertEquals(empresa.getInscricaoEstadual(), ((ClientePJ) result).getInscricaoEstadual());
    }
}
