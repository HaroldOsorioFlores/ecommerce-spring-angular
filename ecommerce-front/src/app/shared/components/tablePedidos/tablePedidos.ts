import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Modal } from '../modal/modal';
import {
  CreatePedido,
  PedidoInterface,
} from '../../../interfaces/pedido.interface';
import { PedidosService } from '../../../services/pedido.service';
import { ClienteInterface } from '../../../interfaces/cliente.interface';
import { ClienteService } from '../../../services/cliente.service';
import { ProductoInterface } from '../../../interfaces/producto.interface';
import { ProductoService } from '../../../services/producto.service';

@Component({
  selector: 'app-table-pedidos',
  standalone: true,
  imports: [CommonModule, FormsModule, Modal],
  templateUrl: './tablePedidos.html',
})
export class TablePedidos implements OnInit {
  pedidos: PedidoInterface[] = [];
  loading = false;
  isModalOpen = false;
  newPedido: Partial<CreatePedido> = {};
  clientes: ClienteInterface[] = [];
  productos: ProductoInterface[] = [];
  selectedProducts: Array<{ id: number | null }> = [{ id: null }];
  selectedClientId: number | null = null;

  isViewModalOpen = false;
  selectedPedidoView: PedidoInterface | null = null;

  isEditModalOpen = false;
  selectedPedidoEdit: PedidoInterface | null = null;
  editClientId: number | null = null;
  editProducts: Array<{ id: number | null }> = [];

  constructor(
    private readonly pedidosService: PedidosService,
    private readonly clienteService: ClienteService,
    private readonly productoService: ProductoService
  ) {}

  ngOnInit() {
    this.LoadOrders();
  }

  LoadOrders() {
    this.loading = true;
    this.pedidosService.getPedidos().subscribe({
      next: (pedidos) => {
        this.pedidos = pedidos;
      },
      error: (error) => {
        console.error('Error al cargar pedidos:', error);
      },
      complete: () => {
        this.loading = false;
      },
    });
    this.clienteService.getClientes().subscribe({
      next: (clientes) => {
        this.clientes = clientes;
      },
      error: (error) => {
        console.error('Error al cargar clientes:', error);
      },
      complete: () => {
        this.loading = false;
      },
    });
    this.productoService.getProductos().subscribe({
      next: (productos) => {
        this.productos = productos;
      },
      error: (error) => {
        console.error('Error al cargar productos:', error);
      },
      complete: () => {
        this.loading = false;
      },
    });
  }

  openModal() {
    this.isModalOpen = true;
    this.newPedido = {};
  }

  closeModal() {
    this.isModalOpen = false;
  }

  openViewModal(pedido: PedidoInterface) {
    this.selectedPedidoView = pedido;
    this.isViewModalOpen = true;
  }

  closeViewModal() {
    this.isViewModalOpen = false;
    this.selectedPedidoView = null;
  }

  onSubmit() {
    if (!this.selectedClientId) {
      console.error('Debe seleccionar un cliente');
      return;
    }

    const productIds = this.selectedProducts
      .map((item) => item.id)
      .filter((id): id is number => id !== null);

    if (productIds.length === 0) {
      console.error('Debe seleccionar al menos un producto');
      return;
    }

    const pedido: CreatePedido = {
      clienteId: this.selectedClientId,
      productosId: productIds,
    };

    this.pedidosService.createPedido(pedido).subscribe({
      next: (response) => {
        this.pedidos.push(response);
        this.closeModal();
      },
      error: (error) => {
        console.error('Error al crear pedido:', {
          status: error.status,
          message: error.message,
          error: error.error,
        });
      },
    });
  }

  addProductSelect() {
    this.selectedProducts.push({ id: null });
  }

  removeProductSelect(index: number) {
    if (this.selectedProducts.length > 1) {
      this.selectedProducts.splice(index, 1);
    }
  }

  openEditModal(pedido: PedidoInterface) {
    this.selectedPedidoEdit = pedido;
    this.editClientId = pedido.cliente.id;
    this.editProducts = pedido.productos.map((p) => ({ id: p.id }));
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
    this.selectedPedidoEdit = null;
    this.editClientId = null;
    this.editProducts = [];
  }

  removeEditProductSelect(index: number) {
    if (this.editProducts.length > 1) {
      this.editProducts.splice(index, 1);
    }
  }

  addEditProductSelect() {
    this.editProducts.push({ id: null });
  }

  deletePedido(pedido: PedidoInterface) {
    if (confirm('¿Está seguro que desea eliminar este pedido?')) {
      this.pedidosService.deletePedido(pedido.id).subscribe({
        next: () => {
          this.pedidos = this.pedidos.filter(p => p.id !== pedido.id);
        },
        error: (error) => {
          console.error('Error al eliminar pedido:', error);
        }
      });
    }
  }

  onEditSubmit() {
    if (!this.editClientId || !this.selectedPedidoEdit) {
      console.error('Datos de edición inválidos');
      return;
    }

    const productIds = this.editProducts
      .map(item => item.id)
      .filter((id): id is number => id !== null);

    if (productIds.length === 0) {
      console.error('Debe seleccionar al menos un producto');
      return;
    }

    const updatedPedido = {
      id: this.selectedPedidoEdit.id,
      clienteId: this.editClientId,
      productosId: productIds
    };

    console.log('Actualizando pedido:', updatedPedido);

    this.pedidosService.updatePedido(updatedPedido.id, updatedPedido).subscribe({
      next: (response) => {
        if (response) {
          const index = this.pedidos.findIndex(p => p.id === response.id);
          if (index !== -1) {
            this.pedidos[index] = response;
          }
          this.closeEditModal();
        }
      },
      error: (error) => {
        console.error('Error al actualizar pedido:', error);
      }
    });
  }
}
