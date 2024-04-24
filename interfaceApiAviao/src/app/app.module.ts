import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CabecalhoComponent } from './componentes/cabecalho/cabecalho.component';
import { RodapeComponent } from './componentes/rodape/rodape.component';
import { BuscarPassagensComponent } from './componentes/passagens/buscar-passagens/buscar-passagens.component';
import { ListarPassagensComponent } from './componentes/passagens/listar-passagens/listar-passagens.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ComprarPassagemComponent } from './componentes/passagens/comprar-passagem/comprar-passagem.component';
import { PassagemDisponivelComponent } from './componentes/passagens/passagem-disponivel/passagem-disponivel.component';
import { PassagemDoPassageiroComponent } from './componentes/passagens/passagem-do-passageiro/passagem-do-passageiro.component';

@NgModule({
  declarations: [
    AppComponent,
    CabecalhoComponent,
    RodapeComponent,
    BuscarPassagensComponent,
    ListarPassagensComponent,
    ComprarPassagemComponent,
    PassagemDisponivelComponent,
    PassagemDoPassageiroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
