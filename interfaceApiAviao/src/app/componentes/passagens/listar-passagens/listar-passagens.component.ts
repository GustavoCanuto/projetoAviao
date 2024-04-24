import { Component, OnInit } from '@angular/core';
import { PassagensService } from '../passagens.service';
import { PassagemDisponivel, ResponsePassagens } from '../../passagem';

@Component({
  selector: 'app-listar-passagens',
  templateUrl: './listar-passagens.component.html',
  styleUrls: ['./listar-passagens.component.css']
})
export class ListarPassagensComponent implements OnInit {

  static listaPassagens: PassagemDisponivel[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  listaPassagem = ListarPassagensComponent.listaPassagens;

  constructor(private service: PassagensService){}

  ngOnInit(): void {
    this.atualizarListaPassagens('1', '1');
  }


  atualizarListaPassagens(idOrigem: string, idDestino: string): void {
    this.service.listar(idOrigem, idDestino).subscribe((response: ResponsePassagens) => {
      ListarPassagensComponent.listaPassagens = response.content;
      this.totalPages = response.totalPages;
      this.currentPage = response.number;
    });
  }

}
