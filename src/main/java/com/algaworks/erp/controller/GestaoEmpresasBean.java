package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String termoPesquisa;

	private List<Empresa> listaEmpresas;

	@Inject
	private Empresas empresas;

	@Inject
	private FacesMessages messages;

//	Methods

	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);

		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

//	Methods DAO
	public void todasEmpresas() {
		this.listaEmpresas = empresas.todas();
	}

//	Getters and Setters

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public Empresas getEmpresas() {
		return empresas;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

}
