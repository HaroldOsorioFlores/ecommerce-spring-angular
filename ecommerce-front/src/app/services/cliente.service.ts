import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClienteInterface } from '../interfaces/cliente.interface';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  url = 'http://192.168.1.6:8085/api/clientes';
  constructor(private readonly http: HttpClient) {}

  getClientes(): Observable<ClienteInterface[]> {
    return this.http.get<ClienteInterface[]>(this.url);
  }
}
