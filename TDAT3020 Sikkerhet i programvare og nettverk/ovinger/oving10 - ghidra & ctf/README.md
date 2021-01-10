# Ghidra



```c
undefined8 main(void)

{
  char local_28 [32];
  
  printf("Enter your name: ");
  fgets(local_28,0x20,stdin);
  printf("Hello ");
  printf(local_28);  // <-- unsafe
  putchar(10);
  return 0;
}
```



On the `printf(local_28)` we print user input, this is not safe. could be replaced with: `fprintf("%s", local_28)`

# CTF

## Flag 1

We have 2 pages, indexed 1 and 2. Creating a new page gives us the id 8.

After seeing this i looked thru the other id's, where most gave 404, id 5 gave 403.

This page was restriced and did not show up on `page/5`, however by editing a other page and editing the url to `page/edit/5` I was able to see the flag.

![flag1](./oppgave2/flag1.png)

## Flag 2

Appending a `'` to the previous url gave me a flag in plaintext.

![flag2](./oppgave2/flag2.png)

## Flag 3

Markdown scrubbs `<script>` tags, but the title doesnt.

Creating a page with title `<script  src="alert('h')" />` and returning to the main page alerts us with the flag

![flag3](./oppgave2/flag3.png)

## Flag 4

Creating a markdown image with `<img>` tag and a binary xss alert resulted in a flag being put on the html element

```html
<img src=x onerror="&#0000106&#0000097&#0000118&#0000097&#0000115&#0000099&#0000114&#0000105&#0000112&#0000116&#0000058&#0000097&#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041">
```

![flag4](./oppgave2/flag4.png)
