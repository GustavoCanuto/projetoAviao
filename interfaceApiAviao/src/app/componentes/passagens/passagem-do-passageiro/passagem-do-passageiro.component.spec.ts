import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassagemDoPassageiroComponent } from './passagem-do-passageiro.component';

describe('PassagemDoPassageiroComponent', () => {
  let component: PassagemDoPassageiroComponent;
  let fixture: ComponentFixture<PassagemDoPassageiroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PassagemDoPassageiroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PassagemDoPassageiroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
