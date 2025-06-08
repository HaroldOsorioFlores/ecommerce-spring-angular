import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="isOpen" class="fixed inset-0 z-50 overflow-y-auto">
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
      ></div>

      <div class="flex min-h-full items-center justify-center p-4">
        <div
          class="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-full max-w-lg"
        >
          <div class="bg-white px-4 pb-4 pt-5 sm:p-6">
            <div class="flex items-start justify-between">
              <h3 class="text-lg font-semibold leading-6 text-gray-900">
                {{ title }}
              </h3>
              <button
                type="button"
                (click)="onClose()"
                class="text-gray-400 hover:text-gray-500"
              >
                <svg
                  class="h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </button>
            </div>

            <div class="mt-3">
              <ng-content></ng-content>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
})
export class Modal {
  @Input() isOpen = false;
  @Input() title = '';
  @Output() closeModal = new EventEmitter<void>();

  onClose() {
    this.closeModal.emit();
  }
}
