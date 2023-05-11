import { Component } from '@angular/core';
import { Cliente } from '../modelo/Cliente';
import { ClienteService } from '../services/cliente.service';
import { ValidaClienteService } from '../services/valida-cliente.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent {

  cliente = new Cliente();
  clientes: Cliente[] = [];

  campoInvalido = {
    nome: false,
    idade: false,
    telefone: false
  };

  constructor(
    private service: ClienteService

  ) { }

  ngOnInit(): void {
    this.listarCliente();
  }

  validarCampo(campo: string): void {
    if (campo === 'nome') {
      this.campoInvalido.nome = this.cliente.nome === '';
    } else if (campo === 'idade') {
      const idade = Number(this.cliente.idade);
      this.campoInvalido.idade = this.cliente.idade === null || isNaN(idade) || idade <= 0;
    } else if (campo === 'telefone') {
      this.campoInvalido.telefone = this.cliente.telefone === null || this.cliente.telefone.length !== 9;
    }
  }

  limpar(): void {
    this.cliente = new Cliente();
  }

  selecionarCliente(posicao: number): void {
    this.cliente = this.clientes[posicao];
  }

  listarCliente(): void {
    this.service.listarCliente()
      .subscribe(retorno => this.clientes = retorno)
  }

  cadastrarCliente(): void {
    this.service.cadastraCliente(this.cliente)
      .subscribe(retorno => {
        this.clientes.push(retorno);
        this.limpar();
        alert("Cadastrado")
      });

  }

  alterarCliente(): void {
    this.service.alterarCliente(this.cliente)
      .subscribe(retorno => {
        let posicao = this.clientes.findIndex(obj => obj.id === this.cliente.id)
        this.clientes[posicao] = retorno;
        this.limpar();
        alert("Alterado")
      })
  }

  deletaCliente(): void {
    this.service.deletaCliente(this.cliente.id)
      .subscribe(retorno => {
        let posicao = this.clientes.findIndex(obj => obj.id == this.cliente.id);
        this.clientes.splice(posicao, 1);
        this.limpar();
        alert('removido com sucesso');
      })
  }

}
