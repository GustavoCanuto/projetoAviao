import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarPassagensComponent } from './buscar-passagens.component';

describe('BuscarPassagensComponent', () => {
  let component: BuscarPassagensComponent;
  let fixture: ComponentFixture<BuscarPassagensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BuscarPassagensComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuscarPassagensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
