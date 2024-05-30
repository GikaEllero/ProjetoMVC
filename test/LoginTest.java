import Controller.ControllerLogin;
import Model.Login;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author giova
 */
public class LoginTest {
    private final ControllerLogin loginController = new ControllerLogin();
    
    @Test
    public void validaLoginTest(){
        String login = "paulo";
        String senha = "teste";
        String priv = "Operador";
        String nome = "Paulo da Silva";
        Login teste = new Login(login, senha, nome, priv);
        Login result = loginController.validaLogin(login, senha, priv);
        
        assertEquals(teste.getNome(), result.getNome());
        assertEquals(teste.getLogin(), result.getLogin());
        assertEquals(teste.getSenha(), result.getSenha());
        assertEquals(teste.getPrivilegio(), result.getPrivilegio());
    }
}
