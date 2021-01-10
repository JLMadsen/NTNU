<a href="../README.md">Tilbake</a>

# shell script

## Linux 

chmod oug+x <filename>

## Oppbygging

Shebang:  
`#!/bin/sh`  
`#!/usr/bin/python`  
`#!/bin/bash` 

```sh
#!/bin/sh
# dette er en kommentar

ls # utfører ls

echo hade `whoami`! #utfører først whomami, og deretter echo
```

Parameter:

følgende script vil printe første parameter

```sh
#!/bin/sh
echo $1
```

## Kommandoer

`find` leter i undermapper eller gitt startpunkt etter ting som navn eller spesifiserte egenskaper.

f.eks: `find . -name "*~" -exec rm {} \;` 

`xargs` begrenser hvor mange filer programmet skal tolke på en gang.

`if` er en vanlig if sjekk.

f.eks: `if noe; then kommando; else kommando; fi`

`wget` laster ned noe fra gitt url

f.eks: `wget http://eksamen.no/løsningsforslag_2020.pdf`

`[` er brukt for å teste, som f.eks om filer finnes.

## Miljøvariabler

`$PATH$`




