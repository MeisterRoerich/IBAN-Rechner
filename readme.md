# IBAN-Rechner
Kleines Schulprojekt

Diese App berechnet und Prüft IBANs.

Bedient euch am Code :)

<a href="https://play.google.com/store/apps/details?id=de.merz.iban_rechner">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/de_app_rgb_wo_60.png" />
</a>

IBAN-Rechner

**IBAN-Rechner**

**Aufgabe**

Aufgabe für dieses Projekt ist die Erstellung eines Programms, das die
International Bank Account Number (**IBAN**) aus der Bankleitzahl und
Kontonummer deutscher Konten berechnet.

**Projektziel**

Der IBAN-Rechner soll eine auf Basis des ISO-Standards eine IBAN aus
Bankleitzahl und Kontonummer berechnen. Außerdem soll zusätzlich die
Möglichkeit bestehen, eine IBAN über die Prüfsumme zu überprüfen.

**Projektablauf**

Der IBAN-Rechner wird als Android App erstellt. Als IDE
(=Entwicklungsumgebung) wird Eclipse mit den Android Plug-ins und dem
Android SDK genutzt. Die Oberfläche soll Felder zur Eingabe von
Kontonummer und Bankleitzahl bieten. Die Schaltflächen zum Berechnen der
IBAN und zum Leeren der Felder werden Java Methoden ausführen.

Der Algorithmus zur Berechnung der Prüfsumme und zur Zusammensetzung der
IBAN soll in Java umgesetzt werden:

**Algorithmus zur Berechnung der IBAN**

-   Die Kontonummer und Bankleitzahl müssen aus den Feldern ausgelesen
    und übertragen werden

-   Es muss überprüft werden ob die Kontonummer zwischen 7 und 10
    Zeichen lang ist, sonst muss der Nutzer aufgefordert werden, eine
    gültige Kontonummer anzugeben.

-   Es muss überprüft werden ob die Bankleitzahl acht Stellen besitzt,
    sonst muss der Nutzer aufgefordert werden, eine gültige BLZ
    einzugeben.

-   Die Kontonummer muss um Nullen ergänzt werden, bis sie zehn-stellig
    ist.

-   Dann wird der BBAN aus Bankleitzahl, Kontonummer(zehn-stellig), das
    Länderkürzel DE, in Zahlen umgewandelt also „1314“, und zwei Nullen,
    zusammengesetzt

-   → **BBAN = BLZ + Kontonummer + 1314 + 00**

-   Aus dieser Zahl wird dann der Modulo 97 gebildet.

-   Das Ergebnis wird von 98 subtrahiert.

-   Ist diese Zahl einstellig, wird sie um eine Null ergänzt und somit
    ist die Prüfziffer gebildet.

-   Die IBAN bildet sich dann aus dem Länderkürzel(DE), Prüfziffer,
    Bankleitzahl und der 10-stelligen Kontonummer.

-   → **IBAN= DE + Prüfsumme + BLZ + (Nullen zum Auffüllen der
    Kontonummer) + Kontonummer**

\
\

**Algorithmus zur Überprüfung der IBAN**

-   Zuerst muss sichergestellt werden, dass die eingegebene IBAN 22
    Stellen besitzt

-   Der Ländercode und die Prüfziffer (die ersten 4 Zeichen) werden ans
    Ende gehängt

-   Der Ländercode wird in Ziffern umgewandelt ( DE =1314)

-   Diese Zahl wird durch Modulo 97 geteilt

-   → Ist das Ergebnis „1“ ist die Prüfziffer gültig sonst nicht.

-   Wenn die IBAN gültig ist, soll sich die Überschrift „IBAN“ auf „IBAN
    gültig“ ändern und grün gefärbt werden.

-   Wenn die IBAN ungültig ist, soll die Überschrift „IBAN“ sich auf
    „IBAN ungültig“ ändern und rot gefärbt werden.

**Quellen:**

[http://www.iban.de/iban-pruefsumme.html](http://www.iban.de/iban-pruefsumme.html)

[http://www.pruefziffernberechnung.de/Originaldokumente/IBAN/Prufziffer\_07.00.pdf](http://www.pruefziffernberechnung.de/Originaldokumente/IBAN/Prufziffer_07.00.pdf)
