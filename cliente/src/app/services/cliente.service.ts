import { Injectable } from '@angular/core';
import { Cliente } from '../modelo/Cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private url: String = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  listarCliente(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.url}`);
  }

  cadastraCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.url}`, cliente);
  }

  alterarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.url}`, cliente);
  }

  deletaCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
