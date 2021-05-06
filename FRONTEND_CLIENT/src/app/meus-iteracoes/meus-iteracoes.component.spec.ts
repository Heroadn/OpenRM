import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusIteracoesComponent } from './meus-iteracoes.component';

describe('MeusIteracoesComponent', () => {
  let component: MeusIteracoesComponent;
  let fixture: ComponentFixture<MeusIteracoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusIteracoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusIteracoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
