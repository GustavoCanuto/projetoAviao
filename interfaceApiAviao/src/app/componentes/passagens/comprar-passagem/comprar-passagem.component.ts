import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PassageiroParaComprar, PassagemDisponivel } from '../../passagem';
import { PassagensService } from '../passagens.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-comprar-passagem',
  templateUrl: './comprar-passagem.component.html',
  styleUrl: './comprar-passagem.component.css'
})
export class ComprarPassagemComponent {

  mensagemErro: string = '';

  formulario!: FormGroup;

  comprarPassagem(): void {
  const passagemId = this.route.snapshot.paramMap.get('id');
  if (passagemId && this.formulario.valid) {
    this.service.cadastrarPassageiro(this.formulario.value).subscribe((passageiro) => {
      const passageiroId = passageiro ? passageiro.id : undefined;
      if (passageiroId !== undefined) {
        const passageiroParaComprar = { idPassageiro: passageiroId };
        this.service.comprar(Number(passagemId), passageiroParaComprar).subscribe(() => {
          this.mensagemErro = '';
          alert("Passagem comprada com sucesso!");
          this.router.navigate(['/passagemComprada', passagemId]);
        }, error => {
          console.error("Erro ao comprar passagem:", error);
          if (error && error.error && Array.isArray(error.error) && error.error.length > 0) {
            const primeiraMensagemErro = error.error[0].mensagem;
            this.mensagemErro = "Erro ao comprar passagem: " + primeiraMensagemErro;
          } else {
            this.mensagemErro = "Erro ao comprar passagem. Por favor, tente novamente.";
          }
        });
      } else {
        console.error("Erro: O id do passageiro não pôde ser obtido.");
        this.router.navigate(['/']);
      }
    }, error => {
      if (error && error.error && Array.isArray(error.error) && error.error.length > 0) {
        const primeiraMensagemErro = error.error[0].mensagem;
        this.mensagemErro = "Erro ao cadastrar o passageiro: " + primeiraMensagemErro;
      } else if (error.error && typeof error.error === 'string') {
        const primeiraMensagemErro = error.error;
        this.mensagemErro = "Erro ao cadastrar o passageiro: " + primeiraMensagemErro;
      } else {
        this.mensagemErro = "Erro ao cadastrar o passageiro. Por favor, tente novamente.";
      }
      console.error("Erro ao cadastrar passageiro:", error);
    });
  }
}
  habilitarBotao(): string {
    if (this.formulario.valid) {
      return 'botao'
    } else {
      return 'botao__desabilitado'
    }
  }

  constructor(
    private formBuilder: FormBuilder,
    private service: PassagensService,
    private router: Router,
    private route: ActivatedRoute

  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const passagemId = Number(params.get('id'));
      this.formulario = this.formBuilder.group({
        nomeCompleto: ['', Validators.compose([
          Validators.required,
          Validators.pattern(/(.|\s)*\S(.|\s)*/),
          Validators.minLength(5)
        ])],
        cpf: ['', Validators.compose([
          Validators.required,
          Validators.minLength(11)
        ])],
        email: ['', Validators.compose([
          Validators.required,
          Validators.email
        ])]
      });
    });
  }
}
