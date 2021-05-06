import { TestBed } from '@angular/core/testing';

import { IteracaoService } from './iteracao.service';

describe('IteracaoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IteracaoService = TestBed.get(IteracaoService);
    expect(service).toBeTruthy();
  });
});
