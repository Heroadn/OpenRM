import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequisitoEditComponent } from './requisito-edit.component';

describe('RequisitoEditComponent', () => {
  let component: RequisitoEditComponent;
  let fixture: ComponentFixture<RequisitoEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequisitoEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequisitoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
