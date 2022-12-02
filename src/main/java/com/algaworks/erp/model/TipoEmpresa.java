package com.algaworks.erp.model;

public enum TipoEmpresa {

	MEI("Microempreendedor Individual"), 
	EIRELI("Empresa Individual de Responsabilidade Limitada"),
	LTDA("Sociedade Limitada"), 
	SA("Sociedade Anônima");

	private String descricao;

	TipoEmpresa(String descricao) {
	}

	public String gerDescricao() {
		return this.descricao;
	}

}
