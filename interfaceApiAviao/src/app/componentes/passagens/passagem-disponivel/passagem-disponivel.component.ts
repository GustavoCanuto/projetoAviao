import { Component, Input } from '@angular/core';
import { PassagemDisponivel } from '../../passagem';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-passagem-disponivel',
  templateUrl: './passagem-disponivel.component.html',
  styleUrl: './passagem-disponivel.component.css'
})
export class PassagemDisponivelComponent {

  constructor(
    private router: Router,
    private route: ActivatedRoute

    ){}
  @Input() passagem: PassagemDisponivel | undefined;

  isPassagemDisponivel(passagem: any): boolean {
    if (passagem.tipo === 'ECONOMICO') {
      return passagem.idAeronave.qtdAssentoEconomico > 0;
    } else if (passagem.tipo === 'VIP') {
      return passagem.idAeronave.qtdAssentoVip > 0;
    } else {
      return false;
    }
  }
  comprarPassagemDisponivel(passagem: PassagemDisponivel | undefined): void {
    if (passagem && passagem.id) {
      this.router.navigate(['/comprarPassagem', passagem.id]);
    } else {
      console.error("Erro: Passagem não definida ou ID não encontrado.");
    }
  }
}
