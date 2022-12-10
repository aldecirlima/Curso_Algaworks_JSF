package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

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

	public void prepararEdicao() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
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

//	private Boolean jaHouvePesquisa() {
//		return termoPesquisa != null && !"".equals(termoPesquisa);
//	}

//	Methods DAO

	public void salvar() {

		try {
			cadastroEmpresaService.salvar(empresa);
			messages.info("Empresa salva com sucesso!");
			if (listaEmpresas != null && !listaEmpresas.contains(empresa)) {
				listaEmpresas.add(empresa);
			} else {
				this.todasEmpresas();
			}

			PrimeFaces.current().ajax().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));

		} catch (Exception e) {
			messages.info("Erro ao salvar empresa verifique o preenchimento");
		}

	}

	public void excluir() {
		try {
			cadastroEmpresaService.excluir(empresa);
			if (listaEmpresas != null) {
				this.todasEmpresas();
			}
			empresa = null;
			messages.info("Empresa excluída com sucesso!");
		} catch (Exception e) {
			messages.info("Erro ao excluir empresa");
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

	public boolean isEmpresaSelecionada() {
		return empresa != null && empresa.getId() != null;
	}

}
