package br.edu.iftm.contact.contatos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.contact.contatos.domain.Contato;

@Component
public class ContatoDao {

	@Autowired
	JdbcTemplate db;

	public List<Contato> getContatos() {
		String sql = "select  email,nome, telefone from tb_contato";

		return db.query(sql, (res, rowNum) -> {
			return new Contato(
					res.getString("nome"),
					res.getString("email"),
					res.getString("telefone"));
		});
	}

	public List<Contato> getContatos(String nome) {
		String sql = "select  email,nome, telefone from tb_contato where lower(nome) like ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Contato.class),
				new Object[] { "%" + nome + "%" });
	}

	public Contato getContato(String email) {
		String sql = "select  email,nome, telefone from tb_contato where email = ?";

		List<Contato> contatos = db.query(sql,
				new BeanPropertyRowMapper<>(Contato.class),
				new Object[] { email });
		if (contatos != null && contatos.size() > 0) {
			return contatos.get(0);
		} else {
			return null;
		}
	}

	public void inserirContato(Contato contato) {
		String sql = "insert into tb_contato(email,nome, telefone) values(?,?,?)";

		db.update(sql, new Object[] { contato.getEmail(), contato.getNome(), contato.getTelefone() });
	}

	public void updateContato(Contato contato) {
		String sql = "update tb_contato set nome = ?, telefone= ? where email = ?";

		db.update(sql, new Object[] { contato.getNome(), contato.getTelefone(), contato.getEmail() });
	}

	public void deleteContato(String email) {
		String sql = "delete from tb_contato where email = ?";

		db.update(sql, new Object[] { email });
	}

}