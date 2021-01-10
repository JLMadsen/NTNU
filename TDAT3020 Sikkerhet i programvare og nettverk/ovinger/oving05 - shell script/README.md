# Øving 5

> chmod oug+x

## Oppgave 1

Hent noe fra nettet med wget

> wget http://www.iie.ntnu.no/fag/_spn/L05/opg05.pdf

## Oppgave 2

Lag et shell script som zipper gamle filer over 10kB

```sh
#!bin/bash

find /home -atime +7 -size +1k -exec gzip {};
``` 

## Oppgave 3

Problemet som oppstår er at den sjekker ikke om bildene allerede ligger i /jpg mappe.

Hvis vi kjører scriptet flere ganger ender vi opp med /jpg/jpg/jpg...

Fikser dette ved å endre `rydd` scriptet til:

```sh
!#/bin/bash

find . -name "*.jpg" -not -path "*\/jpg*" -exec ./flyttjpg {} \;
```

## Oppgave 4

Ved å bruke find kan vi lage et script som ser slik ut:

```sh
#!/bin/bash

find . -name "*.txt" -exec cp {} {}.kopi \;
```

## Oppgave 5

Ved å bruke `\`` rundt et uttrykk vil dette bli kjørt først.

I vårt program brukes dette til å hente ut resultatet av `whoami` før det blir printet ut av echo. 

## Oppgave 6

Jeg ville lagt til linjen:

```sh
*/2   8-12,13-16 * * 1-6 root killall quake
30/2 12-13       * * 1-6 root killall quake
```

Dette vill ikke stoppe alle da man kan endre navn på prosessen.
