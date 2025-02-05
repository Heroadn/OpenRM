import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatRadioModule, MatDialogModule , MatPaginatorModule, MatGridListModule, MAT_DATE_LOCALE} from '@angular/material';
import { MatDatepickerModule, MatNativeDateModule, MatSidenavModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule }     from '@angular/forms';
import { ParticlesModule } from 'angular-particle';
import { MatIconModule }   from '@angular/material/icon';
import { AppComponent }    from './app.component';
import { DragulaModule } from 'ng2-dragula';
import { JwPaginationComponent } from 'jw-angular-pagination';
import { NgxEditorModule } from 'ngx-editor';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCoffee } from '@fortawesome/free-solid-svg-icons';
import { NgxMasonryModule } from 'ngx-masonry';
import { NgZorroAntdModule, NZ_I18N, en_US } from 'ng-zorro-antd';
import { ContextMenuModule } from '@ctrl/ngx-rightclick';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { AlertModule } from 'ngx-bootstrap/alert';
import { UsuarioListComponent }  from './resources/usuario/usuario-list/usuario-list.component';
import { UsuarioEditComponent }  from './resources/usuario/usuario-edit/usuario-edit.component';
import { UsuarioLoginComponent } from './resources/usuario/usuario-login/usuario-login.component';
import { ShContextMenuModule } from 'ng2-right-click-menu'

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
import { OwlTimerModule } from 'owl-ng';
import { HexagonComponent } from './hexagon/hexagon.component';
import { IteracaoMenuComponent } from './menu/iteracao-menu/iteracao-menu.component';

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
    JwPaginationComponent,
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
    ParticlesModule,
    MatIconModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatGridListModule,
    LayoutModule,
    MatSidenavModule,
    MatPaginatorModule,
    UiModule,
    NgxEditorModule, 
    FontAwesomeModule,
    NgxMasonryModule,
    NgZorroAntdModule,
    ContextMenuModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    OwlTimerModule,
    ShContextMenuModule,
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
  entryComponents: [DialogEquipeComponent,DialogUsuarioComponent,DialogRequisitoComponent,DialogSaveRequisitoComponent,DialogSaveIteracaoComponent,IteracaoMenuComponent],
  bootstrap: [AppComponent]
})

export class AppModule { }
