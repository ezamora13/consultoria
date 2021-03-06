import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import {
  MatCardModule,
  MatGridListModule,
  MatToolbarModule, MatIconModule, MatMenuModule, MatFormFieldModule,
  MatSelectModule,
  MatTooltipModule,
  MatTabGroup,
  MatTabsModule,
  MatNativeDateModule,
} from '@angular/material';
import { PortalMainComponent } from './portal-main/portal-main.component';
import { PortalRoutingModule } from './portal-routing.module';
import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { SwiperModule } from 'angular2-useful-swiper';
import { CatalogoOperadoresComponent } from './catalogo-operadores/catalogo-operadores.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PortalMainCarruselComponent } from './portal-main-carrusel/portal-main-carrusel.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [

    PortalMainComponent,

    CatalogoOperadoresComponent,

    PortalMainCarruselComponent,





  ],
  imports: [
    CommonModule,
    SharedModule,
    PortalRoutingModule,
    LayoutModule,
    MatCardModule, MatGridListModule,
    BrowserAnimationsModule,
    BrowserModule,
    NgbModule,
    CarouselModule,
    SwiperModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    MatTooltipModule,
    MatTabsModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,




  ],
  exports: [PortalMainComponent,
  ],


})
export class PortalModule { }
