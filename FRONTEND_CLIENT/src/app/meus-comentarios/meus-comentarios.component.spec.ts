import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusComentariosComponent } from './meus-comentarios.component';

describe('MeusComentariosComponent', () => {
  let component: MeusComentariosComponent;
  let fixture: ComponentFixture<MeusComentariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusComentariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusComentariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
