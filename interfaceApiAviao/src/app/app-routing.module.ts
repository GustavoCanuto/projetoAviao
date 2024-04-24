import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuscarPassagensComponent } from './componentes/passagens/buscar-passagens/buscar-passagens.component';
import { ComprarPassagemComponent } from './componentes/passagens/comprar-passagem/comprar-passagem.component';
import { ListarPassagensComponent } from './componentes/passagens/listar-passagens/listar-passagens.component';
import { PassagemDoPassageiroComponent } from './componentes/passagens/passagem-do-passageiro/passagem-do-passageiro.component';


const routes: Routes = [

  {
    path:'',
    redirectTo: 'buscarPassagens',
    pathMatch: 'full'
  },
  {
    path:'buscarPassagens',
    component: BuscarPassagensComponent
  },
  {
    path:'comprarPassagem/:id',
    component: ComprarPassagemComponent
  },
  { path: 'passagemComprada/:id', component: PassagemDoPassageiroComponent },
  {
    path:'listarPassagens',
    component: ListarPassagensComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
