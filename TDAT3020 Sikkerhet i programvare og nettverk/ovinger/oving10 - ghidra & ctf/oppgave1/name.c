#include <stdio.h>
main()
{
    char name[32];
    printf("Enter your name: ");
    fgets(name, 32, stdin);
    printf("Hello ");
    printf(name);
    printf("\n");
}
