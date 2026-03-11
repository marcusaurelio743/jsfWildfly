package posjava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import posjava.dao.DaoGeneric;
import posjava.model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaBean")
@ViewScoped
public class UsuarioPessoaBean  {
	
	
	private UsuarioPessoa pessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();

	public void setPessoa(UsuarioPessoa pessoa) {
		this.pessoa = pessoa;
	}

	public UsuarioPessoa getPessoa() {
		return pessoa;
	}
	
	public String salvar() {
		dao.salvar(pessoa);
		novo();
		return "";
	}
	
	public String novo() {
		pessoa = new UsuarioPessoa();
		
		return "";
	}
	
	public List<UsuarioPessoa> getList() {
		list = dao.listarTodos(UsuarioPessoa.class);
		return list;
	}

}
