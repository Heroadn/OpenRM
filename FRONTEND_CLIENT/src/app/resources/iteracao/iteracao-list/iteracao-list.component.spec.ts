import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IteracaoListComponent } from './iteracao-list.component';

describe('IteracaoListComponent', () => {
  let component: IteracaoListComponent;
  let fixture: ComponentFixture<IteracaoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IteracaoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IteracaoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
