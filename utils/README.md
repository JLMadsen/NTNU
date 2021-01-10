# Utils guide


## Timer.cpp

Simple timer for measuring code execution.

Start with `timer.set()` and stop with `timer.stop()`.

`timer.stop()` return duration in milliseconds.

```
Timer timer = Timer();
timer.set();

// do stuff

double duration = timer.stop();
```

## manjaroSetup

credits: [Martorb](https://github.com/MarTorb)

```
chmod 755 ./manjaroSetup
./manjaroSetup
```
