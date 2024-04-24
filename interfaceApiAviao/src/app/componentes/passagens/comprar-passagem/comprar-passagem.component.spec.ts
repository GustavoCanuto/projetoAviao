import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarPassagemComponent } from './comprar-passagem.component';

describe('ComprarPassagemComponent', () => {
  let component: ComprarPassagemComponent;
  let fixture: ComponentFixture<ComprarPassagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComprarPassagemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ComprarPassagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
