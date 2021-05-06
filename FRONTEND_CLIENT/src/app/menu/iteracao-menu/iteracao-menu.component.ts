import { Component, OnInit } from '@angular/core';
import { MenuComponent, MenuPackage, ContextMenuService } from '@ctrl/ngx-rightclick';

@Component({
  selector: 'app-iteracao-menu',
  templateUrl: './iteracao-menu.component.html',
  styleUrls: ['./iteracao-menu.component.css']
})
export class IteracaoMenuComponent extends MenuComponent {
  item: any;

  constructor(public menuPackage: MenuPackage,public contextMenuService: ContextMenuService) {
      super(menuPackage, contextMenuService);
      this.item = menuPackage.context;
  }

  handleClick() {
    this.contextMenuService.closeAll();
  }
}
