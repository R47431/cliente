import { TestBed } from '@angular/core/testing';

import { ValidaClienteService } from './valida-cliente.service';

describe('ValidaClienteService', () => {
  let service: ValidaClienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidaClienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
