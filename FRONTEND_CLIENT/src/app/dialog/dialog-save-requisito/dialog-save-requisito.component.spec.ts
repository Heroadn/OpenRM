import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogSaveRequisitoComponent } from './dialog-save-requisito.component';

describe('DialogSaveRequisitoComponent', () => {
  let component: DialogSaveRequisitoComponent;
  let fixture: ComponentFixture<DialogSaveRequisitoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogSaveRequisitoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogSaveRequisitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
