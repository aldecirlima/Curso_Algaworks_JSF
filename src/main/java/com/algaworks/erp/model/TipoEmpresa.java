package com.algaworks.erp.model;

public enum TipoEmpresa {

	EIRELI("Empresa Individual de Responsabilidade Limitada"),
	LTDA("Sociedade Limitada"), 
	MEI("Microempreendedor Individual"), 
	SA("Sociedade Anônima");

	private String descricao;

	TipoEmpresa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
