package posjava;

import java.util.List;

import org.junit.Test;

import posjava.dao.DaoGeneric;
import posjava.model.TelefoneUser;
import posjava.model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		HibernateUtil.getEntityManager();
	}
	
	@Test
	public void salvarHibernate() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setNome("paulo");
		pessoa.setIdade(32);
		pessoa.setSenha("admin");
		pessoa.setLogin("paulo@123");
		pessoa.setSobrenome("Da Silva");
		pessoa.setEmail("paulo@email.com");
		
		dao.salvar(pessoa);
	}
	
	@Test
	public void pesquisar() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = dao.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void pesquisar2() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa 	pessoa = dao.pesquisar(3L,UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeMerge() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = dao.pesquisar(3L, UsuarioPessoa.class);
		
		pessoa.setEmail("Email modificado");
		pessoa.setNome("Nome Modificado");
		pessoa.setIdade(100);
		
		pessoa = dao.merge(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeDeletar() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(6L);
		
		dao.deletar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.listarTodos(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("========================================================================");
		}
	}
	
	@Test
	public void testeQuery() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = dao.getEntityManager().createQuery("from UsuarioPessoa where nome like '%Nome%'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeQueryAvancada() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = dao.getEntityManager().
				createQuery(" from UsuarioPessoa order by nome ").setMaxResults(5).getResultList();
	
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeBuscaComParametros() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.getEntityManager().
				createQuery("from UsuarioPessoa where nome = :nome").
					setParameter("nome", "Antonio").
				getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("==========================================================================");
		}
	}
	
	@Test
	public void testeSomaIdade() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		Long idade = (Long) dao.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println(idade);
	}
	
	@Test
	public void testNamedQuery() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.getEntityManager().createNamedQuery("BuscarTodos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("==============================================================================================");
		}
	}
	
	@Test
	public void testNamedQueryParametro() {
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = dao.getEntityManager().createNamedQuery("BuscarPorId").setParameter("id", 3L).getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("==============================================================================================");
		}
	}
	
	@Test
	public void salvarTelefone() {
		DaoGeneric dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa  pessoa = (UsuarioPessoa) dao.pesquisar(5L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setPessoa(pessoa);
		telefoneUser.setTipo("fixo");
		telefoneUser.setNumero("(61)8989898989-60");
		
		dao.salvar(telefoneUser);
	}
	
	@Test
	public void testeBuscarFones() {
		DaoGeneric dao = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) dao.pesquisar(5L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getPessoa().getNome());
			System.out.println("============================================");
		}
	}

}
