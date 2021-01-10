#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "fargeskrift.h"

int main(int argc, char *argv[])
{
     for (int i=-1; i<21; i++)
    {
        int colr = i%8;
        farge_printf(colr, colr, "@", colr);
    }

    printf("\n");
    return 0;
}
