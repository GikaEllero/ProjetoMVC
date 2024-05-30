import Controller.*;
import Model.*;
import Model.DAO.DAOConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author giova
 */
public class VendaTest {
    private ControllerVenda vendaController = new ControllerVenda();
    private Venda venda;
    private ControllerProduto produtoController = new ControllerProduto();
    private ControllerCliente cliente = new ControllerCliente();
    
    @Before
    public void SetUp(){
        // começa o teste com um banco de dados limpo
        limparBancoDados();
        
        // adicionar informações necessárias para a venda
        Calendar calendar = Calendar.getInstance();
        calendar.set(2002, Calendar.MAY, 30);
        ClientePF pessoa = new ClientePF(6, calendar.getTime(), "Teste Pessoa", "12345678912", 123456789);
        cliente.persistirBanco(pessoa, true, false, false);
        
        Produto produto = new Produto(5, "Teste Inserir", (float)6.0);
        produtoController.persistirBanco(produto, true, false, false);
        
        VendaItem vendaItem = new VendaItem(3, 1, (float)6.0);
        vendaItem.setProduto(produto);
        
        venda = new Venda(5, calendar.getTime(), pessoa, (float)1.0, (float)15.0);
        venda.adicionaItem(vendaItem);
    }
    
    @Test
    public void persistirBancoTest(){
        vendaController.persistirBanco(venda);
        
        Venda result = getVendaById(vendaController.getVendas(),venda.getIdVenda());
        
        assertEquals(venda.getCliente().getNome(), result.getCliente().getNome());
        assertEquals(venda.getValorPago(), result.getValorPago(), 0.001);
        assertEquals(venda.getTotalVendaLiquida(), result.getTotalVendaLiquida(), 0.001);
    }
    
    
    public static Venda getVendaById(ArrayList<Venda> listaVenda, int id) {
        for (Venda venda : listaVenda) {
            if (venda.getIdVenda() == id) {
                return venda;
            }
        }
        return null; // Retorna null se o item não for encontrado
    }
    
    public void limparBancoDados() {
        Connection conexao = new DAOConexaoDB().getConexao();
        String[] tabelas = {"tb_venda_item", "tb_venda", "tb_produto", "tb_cliente"};
        try {
            // Desativar as verificações de chave estrangeira
            PreparedStatement disableFK = conexao.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.executeUpdate();
            disableFK.close();

            for (String tabela : tabelas) {
                PreparedStatement pst = conexao.prepareStatement("DELETE FROM " + tabela);
                pst.executeUpdate();
                pst.close();
            }

            // Reativar as verificações de chave estrangeira
            PreparedStatement enableFK = conexao.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.executeUpdate();
            enableFK.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar banco de dados: " + e.getMessage(), e);
        }
    }
}
