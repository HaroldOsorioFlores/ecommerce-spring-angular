import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  CreatePedido,
  EditarPedido,
  PedidoInterface,
} from '../interfaces/pedido.interface';

@Injectable({
  providedIn: 'root',
})
export class PedidosService {
  url = 'http://localhost:8085/api/pedidos';

  constructor(private readonly http: HttpClient) {}

  getPedidos(): Observable<PedidoInterface[]> {
    return this.http.get<PedidoInterface[]>(this.url);
  }

  createPedido(pedido: Partial<CreatePedido>): Observable<PedidoInterface> {
    return this.http.post<PedidoInterface>(this.url, pedido);
  }

  updatePedido(
    idPedido: string,
    pedido: Partial<EditarPedido>
  ): Observable<PedidoInterface> {
    return this.http.put<PedidoInterface>(`${this.url}/${idPedido}`, pedido);
  }

  deletePedido(id: string): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
