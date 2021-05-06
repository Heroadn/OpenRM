import { TestBed } from '@angular/core/testing';

import { ErrorInterceptorServiceService } from './error-interceptor-service.service';

describe('ErrorInterceptorServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ErrorInterceptorServiceService = TestBed.get(ErrorInterceptorServiceService);
    expect(service).toBeTruthy();
  });
});
