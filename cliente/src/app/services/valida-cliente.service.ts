import { Injectable } from '@angular/core';
import { Cliente } from '../modelo/Cliente';

@Injectable({
  providedIn: 'root'
})
export class ValidaClienteService {

  constructor() { }
/*
  validaCliente(cliente: Cliente): boolean {
    if (cliente.nome == "") {
      return false;
    }

    const idade = Number(cliente.idade);
    if (cliente.idade == null || idade <= 0) {
      return false;
    }

    if (cliente.telefone == null) {
      return false;
    }
    return true;
  }
*/

}
