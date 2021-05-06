import { TestBed } from '@angular/core/testing';

import { UsuarioSelecionadoService } from './usuario-selecionado.service';

describe('UsuarioSelecionadoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UsuarioSelecionadoService = TestBed.get(UsuarioSelecionadoService);
    expect(service).toBeTruthy();
  });
});
