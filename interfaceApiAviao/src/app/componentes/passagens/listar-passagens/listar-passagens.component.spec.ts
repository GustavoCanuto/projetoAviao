import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarPassagensComponent } from './listar-passagens.component';

describe('ListarPassagensComponent', () => {
  let component: ListarPassagensComponent;
  let fixture: ComponentFixture<ListarPassagensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarPassagensComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListarPassagensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
