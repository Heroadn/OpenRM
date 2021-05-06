import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-hexagon',
  templateUrl: './hexagon.component.html',
  styleUrls: ['./hexagon.component.css']
})
export class HexagonComponent {
  @Input() image:any;
  @Input() width:any
  height:any

  constructor() { 
    this.height = (0.57 * this.width) + 1
  }
}
