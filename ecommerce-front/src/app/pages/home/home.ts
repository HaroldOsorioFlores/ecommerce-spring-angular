import { Component } from '@angular/core';
import { TablePedidos } from '../../shared/components/tablePedidos/tablePedidos';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TablePedidos],
  templateUrl: './home.html',
})
export class Home {}
