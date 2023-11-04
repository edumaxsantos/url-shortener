import { Component } from '@angular/core';
import {ShortenerService} from "./services/shortener.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  url = '';
  shortenedUrl = '';

  constructor(private shortenerService: ShortenerService) {
  }

  createShortenedUrl() {
    this.shortenerService.createShortenedUrl(this.url)
      .subscribe(value => this.shortenedUrl = value.short_url);
  }
}
