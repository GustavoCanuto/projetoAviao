package br.com.magnasistemas.apipassagem.dto.aeronave;

public class AeronaveDtoAtualizar{
		
		 Long idCompanhia;

		 Long qtdAssentoEconomico;

		 Long qtdAssentoVip;

		public AeronaveDtoAtualizar(Long idCompanhia, Long qtdAssentoEconomico, Long qtdAssentoVip) {
			this.idCompanhia = idCompanhia;
			this.qtdAssentoEconomico = qtdAssentoEconomico;
			this.qtdAssentoVip = qtdAssentoVip;
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

		public Long getIdCompanhia() {
			return idCompanhia;
		}

		public Long getQtdAssentoEconomico() {
			return qtdAssentoEconomico;
		}

		public Long getQtdAssentoVip() {
			return qtdAssentoVip;
		}
		 
		 

		
		 

}
