import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogSaveIteracaoComponent } from './dialog-save-iteracao.component';

describe('DialogSaveIteracaoComponent', () => {
  let component: DialogSaveIteracaoComponent;
  let fixture: ComponentFixture<DialogSaveIteracaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogSaveIteracaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogSaveIteracaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
