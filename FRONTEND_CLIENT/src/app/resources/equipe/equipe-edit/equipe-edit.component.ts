import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { EquipeService } from '../../../shared/equipe/equipe.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';
import { ProjetoService } from '../../../shared/projeto/projeto.service';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { DialogUsuarioComponent } from '../../../dialog/dialog-usuario/dialog-usuario.component';

@Component({
  selector: 'app-equipe-edit',
  templateUrl: './equipe-edit.component.html',
  styleUrls: ['./equipe-edit.component.css']
})
export class EquipeEditComponent implements OnInit, OnDestroy {
  equipe: any = {};
  membros: Array<any>;
  sub: Subscription;
  dialogRef: MatDialogRef<DialogUsuarioComponent>;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private equipeService: EquipeService,
    private projetoService: ProjetoService,
    private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];//id do usuario
      const id_projeto = params['projeto'];//id do projeto
      
      if (id) {
        this.equipeService.get(id).subscribe((equipe: any) => {
          if (equipe) {
            this.equipe = equipe;
            this.membros = equipe.membros;
            this.equipe.href = equipe._links.self.href;
            this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);

            if(id_projeto){
              this.projetoService.get(id_projeto).subscribe(projeto => this.equipe.id_projeto = projeto);
            }

            for(let membro of this.membros){
              this.giphyService.get(membro.nome).subscribe(url => membro.giphyUrl = url);
            }

          } else {
            console.log(`equipe with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/equipe-list']);
  }

  save(equipe: any) {
    this.equipeService.save(equipe).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  addUsuario(equipe: any) {
    let dialog = this.dialog.open(DialogUsuarioComponent)
      dialog.afterClosed().subscribe((usuario: any) => {
        usuario = dialog.componentInstance.usuario
        this.equipeService.adicionarUsuario(equipe.href,usuario).subscribe((result:any) => {
          //Adiciona a imagem a um usuario
          this.giphyService.get(result.nome).subscribe(url => result.giphyUrl = url);
          this.membros.push(result)
        }, error => console.error(error));
    })
  }

  remove(href) {
    this.equipeService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
