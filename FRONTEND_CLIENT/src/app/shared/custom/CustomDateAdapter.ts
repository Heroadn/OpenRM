import { Injectable } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import { NativeDateAdapter, MatDateFormats } from '@angular/material/core';

export class CustomDateAdapter extends NativeDateAdapter{
    format(date: Date, displayFormat: Object): string {
      if (displayFormat === 'input') {
        let day: string = date.getDate().toString();
        day = +day < 10 ? '0' + day : day;
        let month: string = (date.getMonth() + 1).toString();
        month = +month < 10 ? '0' + month : month;
        let year = date.getFullYear();
        return `${day}-${month}-${year}`;
      }
      return date.toDateString();
    }
  }
  
  export const APP_DATE_FORMATS: MatDateFormats = {
    parse: {
      dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
    },
    display: {
      dateInput: 'input',
      monthYearLabel: { year: 'numeric', month: 'numeric' },
      dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric'
      },
      monthYearA11yLabel: { year: 'numeric', month: 'long' },
    }
  };
