import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusRequisitosComponent } from './meus-requisitos.component';

describe('MeusRequisitosComponent', () => {
  let component: MeusRequisitosComponent;
  let fixture: ComponentFixture<MeusRequisitosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusRequisitosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusRequisitosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
