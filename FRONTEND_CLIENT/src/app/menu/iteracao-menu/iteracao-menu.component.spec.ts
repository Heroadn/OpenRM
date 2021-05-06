import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IteracaoMenuComponent } from './iteracao-menu.component';

describe('IteracaoMenuComponent', () => {
  let component: IteracaoMenuComponent;
  let fixture: ComponentFixture<IteracaoMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IteracaoMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IteracaoMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
