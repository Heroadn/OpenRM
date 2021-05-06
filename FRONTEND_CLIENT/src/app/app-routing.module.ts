import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './shared/auth/auth.guard';

import { UsuarioEditComponent } from './resources/usuario/usuario-edit/usuario-edit.component';
import { UsuarioLoginComponent } from './resources/usuario/usuario-login/usuario-login.component';
import { EquipeEditComponent } from './resources/equipe/equipe-edit/equipe-edit.component';
import { ProjetoEditComponent } from './resources/projeto/projeto-edit/projeto-edit.component';
import { RequisitoListComponent } from './resources/requisito/requisito-list/requisito-list.component';
import { RequisitoEditComponent } from './resources/requisito/requisito-edit/requisito-edit.component';
import { MeusProjetosComponent } from './meus-projetos/meus-projetos.component';
import { MeusEquipesComponent } from './meus-equipes/meus-equipes.component';
import { MeusUsuariosComponent } from './meus-usuarios/meus-usuarios.component';
import { MeusIteracoesComponent } from './meus-iteracoes/meus-iteracoes.component';
import { IteracaoEditComponent } from './resources/iteracao/iteracao-edit/iteracao-edit.component';
import { MeusRequisitosComponent } from './meus-requisitos/meus-requisitos.component';
import { ComentarioListComponent } from './resources/comentario/comentario-list/comentario-list.component';
import { ComentarioEditComponent } from './resources/comentario/comentario-edit/comentario-edit.component';
import { PostListComponent } from './resources/post/post-list/post-list.component';
import { PostEditComponent } from './resources/post/post-edit/post-edit.component';
import { MeusPostsComponent } from './meus-posts/meus-posts.component';
import { ProjetoVerComponent } from './resources/projeto/projeto-ver/projeto-ver.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  //{
    //path: 'usuario-list',
    //component: MeusUsuariosComponent,
    //canActivate: [AuthGuard]
  //},
  {
    path: 'usuario-add',
    component: UsuarioEditComponent
  },
  {
    path: 'usuario-edit/:id',
    component: UsuarioEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'usuario-login',
    component: UsuarioLoginComponent
  },{
    path: 'usuario-logout',
    component: UsuarioLoginComponent
  },//EQUIPE
  {
    path: 'equipe-list',
    component: MeusEquipesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'equipe-add',//Criar a equipe
    component: EquipeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'equipe-edit/:id/:projeto',//Adiciona a um projeto
    component: EquipeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'equipe-edit/:id',
    component: EquipeEditComponent,
    canActivate: [AuthGuard]
  },//PROJETO
  {
    path: 'projeto-list',
    component: MeusProjetosComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projeto-add',
    component: ProjetoEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projeto-ver/:id',
    component: ProjetoVerComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projeto-edit/:id',
    component: ProjetoEditComponent,
    canActivate: [AuthGuard]
  },//REQUISITO
  {
    path: 'requisito-list',
    component: MeusRequisitosComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'requisito-add',
    component: RequisitoEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'requisito-edit/:id',
    component: RequisitoEditComponent,
    canActivate: [AuthGuard]
  },//HOME
  {
    path: 'home',
    component: HomeComponent
  },//Iteracao
  {
    path: 'iteracao-list/:id',
    component: MeusIteracoesComponent,
    //canActivate: [AuthGuard]
  },
  {
    path: 'iteracao-add',
    component: IteracaoEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'iteracao-edit/:id',
    component: IteracaoEditComponent,
    canActivate: [AuthGuard]
  },//Comentario
  {
    path: 'comentario-list/:id',
    component: ComentarioListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'comentario-add',
    component: ComentarioEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'comentario-edit/:id',
    component: ComentarioEditComponent,
    canActivate: [AuthGuard]
  },//Post
  {
    path: 'post-list/:id',
    component: MeusPostsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'post-add/:id',
    component: PostEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'post-edit/:id',
    component: PostEditComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }