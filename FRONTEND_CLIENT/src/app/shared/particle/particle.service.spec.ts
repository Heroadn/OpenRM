import { TestBed } from '@angular/core/testing';

import { ParticleService } from './particle.service';

describe('ParticleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParticleService = TestBed.get(ParticleService);
    expect(service).toBeTruthy();
  });
});
