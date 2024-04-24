export interface PassagemDisponivel {
  id?: number;
  timestampCompra: null;
  timestampPartida: string;
  timestampChegada: string;
  idOrigem: {
    id: number;
    nome: string;
    email: string;
    endereco: {
      id: number;
      cidade: {
        id: number;
        nome: string;
        estado: {
          id: number;
          nome: string;
          uf: string;
          pais: {
            id: number;
            nome: string;
            sigla: string;
          }
        }
      }
      logradouro: string;
      numero: string;
      complemento: string;
      cep: string;
    }
  };
  idDestino: {
    id: number;
    nome: string;
    email: string;
    endereco: {
      id: number;
      cidade: {
        id: number;
        nome: string;
        estado: {
          id: number;
          nome: string;
          uf: string;
          pais: {
            id: number;
            nome: string;
            sigla: string;
          }
        }
      }
      logradouro: string;
      numero: string;
      complemento: string;
      cep: string;
    }
  };
  idAeronave: {
    id: number;
    companhia: {
      id: number;
      nome: string;
      cnpj: string;
      email: string;
    };
    nsa: string;
    modelo: string;
    qtdAssentoEconomico: number;
    qtdAssentoVip: number;
  };
  idPassageiro: {
    id?: number,
    nomeCompleto: string,
    cpf: string,
    email: string
  }
  valorPassagem: number;
  tipo: string;
}

export interface PassageiroParaComprar{
  idPassageiro: number
 }

export interface ResponsePassagens {
  content: PassagemDisponivel[];
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      sorted: boolean;
      unsorted: boolean;
      empty: boolean;
    };
    offset: number;
    unpaged: boolean;
    paged: boolean;
  };
  last: boolean;
  totalPages: number;
  totalElements: number;
  sort: {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  };
  first: boolean;
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
}
