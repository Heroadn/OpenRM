import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusUsuariosComponent } from './meus-usuarios.component';

describe('MeusUsuariosComponent', () => {
  let component: MeusUsuariosComponent;
  let fixture: ComponentFixture<MeusUsuariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusUsuariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
