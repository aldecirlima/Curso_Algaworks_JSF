package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.repository.RamoAtividades;
import com.algaworks.erp.service.CadastroEmpresaService;
import com.algaworks.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String termoPesquisa;

	private List<Empresa> listaEmpresas;

	private Converter<RamoAtividade> ramoAtividadeConverter;

	private Empresa empresa;

	@Inject
	private CadastroEmpresaService cadastroEmpresaService;

	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private Empresas empresas;

	@Inject
	private FacesMessages messages;

//	Methods

	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}

	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);

		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		return listaRamoAtividades;
	}

	private Boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

//	Methods DAO

	public void salvar() {

		try {
			cadastroEmpresaService.salvar(empresa);
			messages.info("Empresa cadastrada com sucesso");
		} catch (Exception e) {
			messages.info("Erro ao salvar empresa verifique o preenchimento");

		} finally {
			if (jaHouvePesquisa()) {
				pesquisar();
			}
		}

	}

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

	public RamoAtividades getRamoAtividades() {
		return ramoAtividades;
	}

	public void setRamoAtividades(RamoAtividades ramoAtividades) {
		this.ramoAtividades = ramoAtividades;
	}

	public Converter<RamoAtividade> getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public void setRamoAtividadeConverter(Converter<RamoAtividade> ramoAtividadeConverter) {
		this.ramoAtividadeConverter = ramoAtividadeConverter;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
