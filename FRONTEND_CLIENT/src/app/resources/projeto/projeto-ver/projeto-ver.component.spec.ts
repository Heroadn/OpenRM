import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetoVerComponent } from './projeto-ver.component';

describe('ProjetoVerComponent', () => {
  let component: ProjetoVerComponent;
  let fixture: ComponentFixture<ProjetoVerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetoVerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetoVerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
