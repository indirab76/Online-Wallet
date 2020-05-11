import { TestBed } from '@angular/core/testing';

import { MyWalletService } from './my-wallet.service';

describe('MyWalletService', () => {
  let service: MyWalletService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyWalletService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
