import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequisitoListComponent } from './requisito-list.component';

describe('RequisitoListComponent', () => {
  let component: RequisitoListComponent;
  let fixture: ComponentFixture<RequisitoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequisitoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequisitoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
