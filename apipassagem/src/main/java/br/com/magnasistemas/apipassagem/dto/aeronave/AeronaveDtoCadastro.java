package br.com.magnasistemas.apipassagem.dto.aeronave;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AeronaveDtoCadastro {
    @NotNull
    private Long idCompanhia;

    @NotNull
    private Long qtdAssentoEconomico;

    @NotNull
    private Long qtdAssentoVip;

    @NotBlank
    @Size(max = 255)
    private String nsa;

    @NotBlank
    @Size(max = 255)
    private String modelo;

  
    public AeronaveDtoCadastro(Long idCompanhia, Long qtdAssentoEconomico, Long qtdAssentoVip, String nsa, String modelo) {
        this.idCompanhia = idCompanhia;
        this.qtdAssentoEconomico = qtdAssentoEconomico;
        this.qtdAssentoVip = qtdAssentoVip;
        this.nsa = nsa;
        this.modelo = modelo;
    }

	public Long idCompanhia() {
		return idCompanhia;
	}

	public Long qtdAssentoEconomico() {
		return qtdAssentoEconomico;
	}

	public Long qtdAssentoVip() {
		return qtdAssentoVip;
	}

	public String nsa() {
		return nsa;
	}

	public String modelo() {
		return modelo;
	}

	public Long getIdCompanhia() {
		return idCompanhia;
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