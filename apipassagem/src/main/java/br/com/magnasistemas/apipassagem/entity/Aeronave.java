package br.com.magnasistemas.apipassagem.entity;

import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoCadastro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_aeronave")
@Entity(name = "Aeronave")
public class Aeronave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_companhia")
	private CompanhiaAerea companhia;
	


	Long qtdAssentoEconomico;
	

	Long qtdAssentoVip;

	 public Aeronave(Long id, CompanhiaAerea companhia, Long qtdAssentoEconomico, Long qtdAssentoVip, String nsa,
			String modelo) {

		
		this.companhia = companhia;
		this.qtdAssentoEconomico = qtdAssentoEconomico;
		this.qtdAssentoVip = qtdAssentoVip;
		this.nsa = nsa;
		this.modelo = modelo;
	}

	public Aeronave() {
		
	}

	public Aeronave(AeronaveDtoCadastro dados, CompanhiaAerea companhia) {
	
		this.companhia = companhia;
		this.qtdAssentoEconomico = dados.qtdAssentoEconomico();
		this.qtdAssentoVip = dados.qtdAssentoVip();
		this.nsa = dados.nsa();
		this.modelo = dados.modelo();
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



	 String nsa;

	 String modelo;
	 
	 
		public void atualizarInformacoes(AeronaveDtoAtualizar dados, CompanhiaAerea companhiaAerea) {

			if (dados.qtdAssentoVip() != null) {
				this.qtdAssentoVip = dados.qtdAssentoVip();
			}

			if (dados.qtdAssentoEconomico() != null) {
				this.qtdAssentoEconomico = dados.qtdAssentoEconomico();
			}

			if (companhiaAerea != null) {
				this.companhia = companhiaAerea;
			}

		}
	 
	 
}
