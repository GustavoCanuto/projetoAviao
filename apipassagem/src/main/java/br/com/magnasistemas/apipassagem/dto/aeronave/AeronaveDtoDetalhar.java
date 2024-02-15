package br.com.magnasistemas.apipassagem.dto.aeronave;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;

public class AeronaveDtoDetalhar {

	Long id;

	CompanhiaAerea companhia;

	Long qtdAssentoEconomico;

	Long qtdAssentoVip;

	String nsa;

	String modelo;

	public AeronaveDtoDetalhar(Aeronave aeronave) {
		this(aeronave.getId(), aeronave.getCompanhia(), aeronave.getQtdAssentoEconomico(), aeronave.getQtdAssentoVip(),
				aeronave.getNsa(), aeronave.getModelo());

	}

	public AeronaveDtoDetalhar(Long id, CompanhiaAerea companhia, Long qtdAssentoEconomico, Long qtdAssentoVip,
			String nsa, String modelo) {
		this.id = id;
		this.companhia = companhia;
		this.qtdAssentoEconomico = qtdAssentoEconomico;
		this.qtdAssentoVip = qtdAssentoVip;
		this.nsa = nsa;
		this.modelo = modelo;
	}

	public Long id() {
		return id;
	}

	public Long getId() {
		return id;
	}

	public CompanhiaAerea getCompanhia() {
		return companhia;
	}

	public Long getQtdAssentoEconomico() {
		return qtdAssentoEconomico;
	}

	public Long getQtdAssentoVip() {
		return qtdAssentoVip;
	}

	public String getNsa() {
		return nsa;
	}

	public String getModelo() {
		return modelo;
	}

}
