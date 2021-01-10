# Øving 6

## Oppgave 1

> clang -g -O1 -fsanitize=fuzzer -o fuzz fuzzing.c

> ./fuzz -only_ascii=1 -max_total_time=60

```sh
###### Recommended dictionary. ###### 
"\x00\x00" # Uses: 78004
"\x14\x00" # Uses: 78243 
"A\x01\x00\x00" # Uses: 72428
"\x01\x00" # Uses: 78005 
"\xff\xff" # Uses: 78077 
"\x09\x00\x00\x00\x00\x00\x00\x00" # Uses: 67224 
"\xff\xff\xff\xff\xff\xff\xff\xa9" # Uses: 67348 
"\xab\x00\x00\x00\x00\x00\x00\x00" # Uses: 66842 
"\xff\xff\x02N" # Uses: 72414 
"\x01\x00\x00\x00\x00\x00\x01\xd8" # Uses: 67030 
".\x00" # Uses: 77885 
"\x00\x00\x00\xb5" # Uses: 72194 
"\x0c\x03" # Uses: 77846 
"\x06\x00\x00\x00" # Uses: 72550 
"\xff\xff\xff\x04" # Uses: 72333 
"\xff\xff\xff\xff\xff\xff\xff\x19" # Uses: 66455 
"<\x01" # Uses: 77867 
"\xea\x03" # Uses: 76331 
",\x00\x00\x00" # Uses: 70924 
"\xff\xff\x04\xlb" # Uses: 70834 
"\xab\x05\x00\x00\x00\x00\x00\x00" # Uses: 63850
###### End of recommended dictionary. ######
Done 17830187 runs in 61 second(s) 
```

## Oppgave 2

repo: https://gitlab.stud.iie.ntnu.no/jakoblm/fuzzing-ci

pipe: https://gitlab.stud.idi.ntnu.no/jakoblm/fuzzing-ci/pipelines/84847

Gitlab CI yml

```yaml
image: ubuntu:latest

stages:
    - fuzzing

fuzzing:
    stage: fuzzing
    script:
        - apt-get update
        - apt-get upgrade -y
        - apt-get install clang build-essential -y
        - clang -g -O1 -fsanitize=fuzzer,address -o fuzz fuzzing.c
        - ./fuzz -only_ascii=1 -max_total_time=60
```

