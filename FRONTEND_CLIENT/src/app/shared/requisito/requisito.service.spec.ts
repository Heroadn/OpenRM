import { TestBed } from '@angular/core/testing';

import { RequisitoService } from './requisito.service';

describe('RequisitoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RequisitoService = TestBed.get(RequisitoService);
    expect(service).toBeTruthy();
  });
});
