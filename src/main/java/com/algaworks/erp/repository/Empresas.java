package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa a JOIN FETCH a.ramoAtividade where a.nomeFantasia like :nomeFantasia "
				+ "or a.razaoSocial like :razaoSocial ORDER BY a.id ASC";

		TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);

		query.setParameter("nomeFantasia", "%" + nome + "%");
		query.setParameter("razaoSocial", "%" + nome + "%");

		return query.getResultList();
	}

	public List<Empresa> todas() {
		return manager.createQuery("from Empresa a JOIN FETCH a.ramoAtividade ORDER BY a.id ASC", Empresa.class)
				.getResultList();
	}

	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}

	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}
}
