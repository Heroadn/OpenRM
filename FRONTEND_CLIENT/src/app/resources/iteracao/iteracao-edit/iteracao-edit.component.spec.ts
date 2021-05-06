import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IteracaoEditComponent } from './iteracao-edit.component';

describe('IteracaoEditComponent', () => {
  let component: IteracaoEditComponent;
  let fixture: ComponentFixture<IteracaoEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IteracaoEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IteracaoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
