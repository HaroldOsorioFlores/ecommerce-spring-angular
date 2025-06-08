import { ClienteInterface } from './cliente.interface';
import { ProductoInterface } from './producto.interface';

export interface PedidoInterface {
  id: string;
  cliente: ClienteInterface;
  productos: ProductoInterface[];
  total: number;
}

export interface CreatePedido {
  clienteId: number;
  productosId: number[];
}


export interface EditarPedido{
  id: string;
  clienteId: number;
  productosId: number[];
  total: number;
}
