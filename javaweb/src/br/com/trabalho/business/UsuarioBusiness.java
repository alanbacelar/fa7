package br.com.trabalho.business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.trabalho.dao.UsuarioDao;
import br.com.trabalho.model.Usuario;

@ApplicationScoped
@ManagedBean(name = "usuarioBusiness")
public class UsuarioBusiness {

	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Usuario autenticarUsuario(String cpf, String senha)
			throws UsuarioInvalidoException {
		Usuario usuario = usuarioDao.consultarUsuarioCPF(cpf);

		if (usuario == null || !usuario.getSenha().equals(senha)) {
			throw new UsuarioInvalidoException();
		}

		return usuario;
	}

	public void incluirUsuario(Usuario usuario) throws UsuarioCadastradoException {	
		if (usuarioDao.consultarUsuarioCPF(usuario.getCpf()) == null )
			usuarioDao.salvarUsuario(usuario);
		else
			throw new UsuarioCadastradoException();
	}
	
	public void alterarUsuario(Usuario usuario){
		usuarioDao.salvarUsuario(usuario);
	}
	
	public void excluirUsuario(Usuario usuario) {
		usuarioDao.excluirUsuario(usuario);
	}

	public Usuario consultarUsuario(String cpf) {
		return usuarioDao.consultarUsuarioCPF(cpf);
	}

	public List<Usuario> listarUsuarios() {
		return usuarioDao.listarUsuarios();
	}
}
