import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetoEditComponent } from './projeto-edit.component';

describe('ProjetoEditComponent', () => {
  let component: ProjetoEditComponent;
  let fixture: ComponentFixture<ProjetoEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetoEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
