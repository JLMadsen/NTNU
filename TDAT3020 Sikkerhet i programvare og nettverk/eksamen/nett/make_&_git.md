<a href="../README.md">Tilbake</a>

# Make & Git

## Patch og diff

`Diff` finner endringer mellom 2 filer.  
Fra `diff` får man en diff-fil, denne kan man bruke med `patch` for å oppdatere filer.  
Et eksempel er Git, da man sender inn commits med endringer i fila.

## Make

`Make` gjør det lettere å kompilere store prosjekter som består av mange filer og mapper.  
Kan bruke flere kompilatorer da systemer kan bruke flere språk.  
Henter instrukser fra en Makefile.  

```make
fargedemo: demo.c skrift.c skrift.hgcc -O2 -o demo  demo.c skrift.c
fargetest: test.c skrift.c skrift.hgcc -O2 -o test  test.c skrift.c
```

## Versjonskontroll
 
`git pull`  
`git push`  
`git commit`  
`git checkout`  
`git clone`  
`git add`  







