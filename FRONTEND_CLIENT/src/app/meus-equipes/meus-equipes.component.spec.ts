import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusEquipesComponent } from './meus-equipes.component';

describe('MeusEquipesComponent', () => {
  let component: MeusEquipesComponent;
  let fixture: ComponentFixture<MeusEquipesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusEquipesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusEquipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
