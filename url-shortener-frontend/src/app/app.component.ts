import { Component, signal } from '@angular/core';
import { ShortenerService } from './services/shortener.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  url = signal('');
  shortenedUrl = signal('');
  error = signal(false);
  isLoading = signal(false);

  constructor(private shortenerService$: ShortenerService) {}

  createShortenedUrl() {
    this.isLoading.set(true);
    this.shortenerService$.createShortenedUrl(this.url()).subscribe({
      next: (value) => this.shortenedUrl.set(value.short_url),
      error: () => {
        this.error.set(true);
        this.isLoading.set(false);
      },
      complete: () => this.isLoading.set(false),
    });
  }

  closeNotification() {
    this.error.set(false);
  }
}
