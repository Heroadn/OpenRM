import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

// MAT_DATE_LOCALE for Datepicker localization
import { MAT_DATE_LOCALE } from '@angular/material/core';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatInputModule } from '@angular/material/input'; // Needed for datepicker input
import { MatButtonModule } from '@angular/material/button';

import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatRadioModule } from '@angular/material/radio';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatGridListModule } from '@angular/material/grid-list';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule }     from '@angular/forms';
import { MatIconModule }   from '@angular/material/icon';
import { AppComponent }    from './app.component';
import { DragulaModule } from 'ng2-dragula';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { NgxMasonryModule } from 'ngx-masonry';
import { NgxEditorModule } from 'ngx-editor';
import { NgxPaginationModule } from 'ngx-pagination';

import { ContextMenuModule } from '@ctrl/ngx-rightclick';
import { AlertModule } from 'ngx-bootstrap/alert';
import { UsuarioListComponent }  from './resources/usuario/usuario-list/usuario-list.component';
import { UsuarioEditComponent }  from './resources/usuario/usuario-edit/usuario-edit.component';
import { UsuarioLoginComponent } from './resources/usuario/usuario-login/usuario-login.component';

import { EquipeListComponent  } from './resources/equipe/equipe-list/equipe-list.component';
import { EquipeEditComponent  } from './resources/equipe/equipe-edit/equipe-edit.component';

import { ProjetoListComponent } from './resources/projeto/projeto-list/projeto-list.component';
import { ProjetoEditComponent } from './resources/projeto/projeto-edit/projeto-edit.component';

import { DialogUsuarioComponent } from './dialog/dialog-usuario/dialog-usuario.component';
import { DialogRequisitoComponent } from './dialog/dialog-requisito/dialog-requisito.component';

import { RequisitoEditComponent } from './resources/requisito/requisito-edit/requisito-edit.component';
import { RequisitoListComponent } from './resources/requisito/requisito-list/requisito-list.component';

import { IteracaoEditComponent } from './resources/iteracao/iteracao-edit/iteracao-edit.component';
import { IteracaoListComponent } from './resources/iteracao/iteracao-list/iteracao-list.component';

import { LayoutModule } from '@angular/cdk/layout';
import { DialogEquipeComponent } from './dialog/dialog-equipe/dialog.component-equipe';
import { UiModule } from './ui/ui.module';
import { HomeComponent } from './home/home.component';
import { JwtInterceptorService } from './shared/JWT/jwt-interceptor.service';
import { ErrorInterceptor } from './shared/error/error-interceptor-service.service';
import { ProjetoVerComponent } from './resources/projeto/projeto-ver/projeto-ver.component';
import { UsuarioVerComponent } from './resources/usuario/usuario-ver/usuario-ver.component';
import { MeusProjetosComponent } from './meus-projetos/meus-projetos.component';
import { MeusEquipesComponent } from './meus-equipes/meus-equipes.component';
import { MeusUsuariosComponent } from './meus-usuarios/meus-usuarios.component';
import { MeusIteracoesComponent } from './meus-iteracoes/meus-iteracoes.component';
import { MeusRequisitosComponent } from './meus-requisitos/meus-requisitos.component';
import { ParticleComponent } from './particle/particle.component';
import { DialogSaveRequisitoComponent } from './dialog/dialog-save-requisito/dialog-save-requisito.component';
import { DialogSaveIteracaoComponent } from './dialog/dialog-save-iteracao/dialog-save-iteracao.component';
import { PostEditComponent } from './resources/post/post-edit/post-edit.component';
import { PostListComponent } from './resources/post/post-list/post-list.component';
import { ComentarioListComponent } from './resources/comentario/comentario-list/comentario-list.component';
import { ComentarioEditComponent } from './resources/comentario/comentario-edit/comentario-edit.component';
import { MeusComentariosComponent } from './meus-comentarios/meus-comentarios.component';
import { MeusPostsComponent } from './meus-posts/meus-posts.component';
import { DatepickerModule, BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { HexagonComponent } from './hexagon/hexagon.component';
import { IteracaoMenuComponent } from './menu/iteracao-menu/iteracao-menu.component';

import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioListComponent,
    UsuarioEditComponent,
    UsuarioLoginComponent,
    EquipeListComponent,
    EquipeEditComponent,
    ProjetoListComponent,
    ProjetoEditComponent,
    DialogEquipeComponent,
    DialogUsuarioComponent,
    RequisitoEditComponent,
    RequisitoListComponent,
    IteracaoEditComponent,
    IteracaoListComponent,
    DialogRequisitoComponent,
    HomeComponent,
    ProjetoVerComponent,
    UsuarioVerComponent,
    MeusProjetosComponent,
    MeusEquipesComponent,
    MeusUsuariosComponent,
    MeusIteracoesComponent,
    MeusRequisitosComponent,
    ParticleComponent,
    DialogSaveRequisitoComponent,
    DialogSaveIteracaoComponent,
    PostEditComponent,
    PostListComponent,
    ComentarioListComponent,
    ComentarioEditComponent,
    MeusComentariosComponent,
    MeusPostsComponent,
    HexagonComponent,
    IteracaoMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatRadioModule,
    FormsModule,
    RouterModule,
    MatIconModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatGridListModule,
    LayoutModule,
    MatSidenavModule,
    MatPaginatorModule,
    UiModule,
    FontAwesomeModule,
    NgxMasonryModule,
    NgxEditorModule,
    NgxPaginationModule,
    ContextMenuModule, 
    MatDatepickerModule,
    BsDatepickerModule.forRoot(),
    DatepickerModule.forRoot() ,
    AlertModule.forRoot(),
    DragulaModule.forRoot()
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  entryComponents: [
    DialogEquipeComponent,
    DialogUsuarioComponent,
    DialogRequisitoComponent,
    DialogSaveRequisitoComponent,
    DialogSaveIteracaoComponent,
    IteracaoMenuComponent
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
