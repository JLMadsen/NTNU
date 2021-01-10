#include "replace.c"

int LLVMFuzzerTestOneInput(const unsigned char *data, int size)
{
    // printf("%s\n", data);
    char* res = str_replace((const char *) data);
    free(res);

    return 0;
}


