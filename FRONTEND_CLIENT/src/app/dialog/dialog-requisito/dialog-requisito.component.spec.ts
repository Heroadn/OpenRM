import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogRequisitoComponent } from './dialog-requisito.component';

describe('DialogRequisitoComponent', () => {
  let component: DialogRequisitoComponent;
  let fixture: ComponentFixture<DialogRequisitoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogRequisitoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogRequisitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
