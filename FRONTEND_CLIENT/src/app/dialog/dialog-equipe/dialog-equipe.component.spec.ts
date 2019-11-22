import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEquipeComponent } from './dialog.component-equipe';

describe('DialogEquipeComponent', () => {
  let component: DialogEquipeComponent;
  let fixture: ComponentFixture<DialogEquipeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEquipeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEquipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
