import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PassageiroParaComprar, PassagemDisponivel, ResponsePassagens } from '../passagem';
import { HttpClient } from '@angular/common/http';
import { Passageiro } from '../passageiro';

@Injectable({
  providedIn: 'root'
})
export class PassagensService {

  private readonly API = "http://localhost:8080/passagem"
  private readonly APIpassageiro = "http://localhost:8080/passageiro"
  constructor(private http: HttpClient) { }

  listar(idOrigem: string, idDestino: string): Observable<ResponsePassagens> {
    const params = { idOrigem, idDestino };
    return this.http.get<ResponsePassagens>(this.API, { params });
  }

  cadastrarPassageiro(passageiro: Passageiro): Observable<Passageiro>{
   return this.http.post<Passageiro>(this.APIpassageiro, passageiro)
  }

  comprar(passagemId: number, passageiroParaComprar: PassageiroParaComprar): Observable<any> {
    const url = `${this.API}/${passagemId}`;
    return this.http.put(url, passageiroParaComprar);
  }

   buscarPorId(id: number): Observable<PassagemDisponivel>{
    const url = `${this.API}/${id}`
    return this.http.get<PassagemDisponivel>(url)
  }

}
