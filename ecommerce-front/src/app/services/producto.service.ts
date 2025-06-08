import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoInterface } from '../interfaces/producto.interface';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  url = 'http://localhost:8085/api/productos';
  constructor(private readonly http: HttpClient) {}

  getProductos(): Observable<ProductoInterface[]> {
    return this.http.get<ProductoInterface[]>(this.url);
  }
}
