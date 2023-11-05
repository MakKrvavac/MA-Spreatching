import { Injectable } from "@angular/core";
import { Language } from "../model/language.enum";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
    providedIn: "root",
})
export class MessageService {
    constructor(private snackBar: MatSnackBar) {}

    public notCompletedForm(language: Language): void {
        if (language === Language.GERMAN) {
            this.snackBar.open("Bitte füllen Sie alle Felder aus", "Okay", {
                duration: 3000,
            });
        } else {
            this.snackBar.open("Please fill in all fields", "Okay", {
                duration: 3000,
            });
        }
    }

    public notEditedPage(language: Language): void {
        if (language === Language.GERMAN) {
            this.snackBar.open("Die Seite wurde noch nicht bearbeitet", "Okay", {
                duration: 3000,
            });
        } else {
            this.snackBar.open("This page has not been edited yet", "Okay", {
                duration: 3000,
            });
        }
    }

    public taskNotFound() {
        this.snackBar.open("Task not found", "Okay", {
            duration: 3000,
        });
    }
}
