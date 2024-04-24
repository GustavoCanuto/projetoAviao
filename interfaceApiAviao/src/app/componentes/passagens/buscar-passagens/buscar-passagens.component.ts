import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PassagensService } from '../passagens.service';
import { Router } from '@angular/router';
import { PassagemDisponivel, ResponsePassagens } from '../../passagem';
import { ListarPassagensComponent } from '../listar-passagens/listar-passagens.component';


@Component({
  selector: 'app-buscar-passagens',
  templateUrl: './buscar-passagens.component.html',
  styleUrl: './buscar-passagens.component.css'
})
export class BuscarPassagensComponent {
formulario!: FormGroup;


  constructor(
    private service: PassagensService,
    private router: Router,
    private formBuilder: FormBuilder

  ) { }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      origem: ['1', Validators.required],
      destino: ['2', Validators.required]
    });
  }

  buscarPassagem() {
    if (this.formulario.valid) {
      const idOrigem = this.formulario.get('origem')?.value;
      const idDestino = this.formulario.get('destino')?.value;
      this.service.listar(idOrigem, idDestino).subscribe((response: ResponsePassagens) => {
        ListarPassagensComponent.listaPassagens = response.content;
        this.router.navigate(['/listarPassagens'])
      } );

    }
  }

  habilitarBotao(): string {
    return this.formulario.valid ? 'botao' : 'botao__desabilitado';
  }
}
