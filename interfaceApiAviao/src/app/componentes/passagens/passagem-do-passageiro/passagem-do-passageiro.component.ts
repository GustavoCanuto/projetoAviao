import { Component } from '@angular/core';
import { PassagemDisponivel } from '../../passagem';
import { PassagensService } from '../passagens.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-passagem-do-passageiro',
  templateUrl: './passagem-do-passageiro.component.html',
  styleUrl: './passagem-do-passageiro.component.css'
})
export class PassagemDoPassageiroComponent {

  passagemComprada: PassagemDisponivel | undefined;

  constructor(private service: PassagensService,
    private router: Router,
    private route: ActivatedRoute

    ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const passagemId = Number(params.get('id'));

      this.service.buscarPorId(passagemId).subscribe((passagemComprada) => {
        this.passagemComprada = passagemComprada;
    });
  });}
}
