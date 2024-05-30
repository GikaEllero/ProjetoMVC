
import Controller.ControllerCliente;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import java.util.Calendar;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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

        pessoa = new ClientePF(5, calendar.getTime(), "Teste Pessoa", "123.456.789-12", 123456789);
        empresa = new ClientePJ(7, calendar.getTime(), "Teste Empresa", "123.456.789-12", 123456789);
        cliente = new ControllerCliente();
    }
    
    @Test
    public void persistirBancoInsertPFTest(){
        cliente.persistirBanco(pessoa, true, false, false);
        
        Cliente result = cliente.getCliente(pessoa.getIdCliente());
        
        assertEquals(pessoa.getNome(), result.getNome());
    }
    
    @Test
    public void persistirBancoInsertPJTest(){
        cliente.persistirBanco(empresa, true, false, false);
        
        Cliente result = cliente.getCliente(empresa.getIdCliente());
        
        assertEquals(empresa.getNome(), result.getNome());
    }
}