import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipeEditComponent } from './equipe-edit.component';

describe('EquipeEditComponent', () => {
  let component: EquipeEditComponent;
  let fixture: ComponentFixture<EquipeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EquipeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EquipeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
