import { TestBed } from '@angular/core/testing';

import { ShortenerService } from './shortener.service';

describe('ShortenerService', () => {
  let service: ShortenerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShortenerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
