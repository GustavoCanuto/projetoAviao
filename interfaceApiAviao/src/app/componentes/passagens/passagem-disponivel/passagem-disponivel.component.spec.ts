import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassagemDisponivelComponent } from './passagem-disponivel.component';

describe('PassagemDisponivelComponent', () => {
  let component: PassagemDisponivelComponent;
  let fixture: ComponentFixture<PassagemDisponivelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PassagemDisponivelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PassagemDisponivelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
