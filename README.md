## NOTICE

I am still interested in this project, but for the time being in an effort to improve iteration time, I have moved my 
efforts to an internal project that will provide these features. Eventually, I hope to resume this project using what 
I've learned there.

## smf4j

The idea is to write a metric collection facade that is as close to the slf4j API/framework as possible. 

The api is heavily inspired by the [Dropwizard Metrics](https://metrics.dropwizard.io/4.0.0/) library. However I have libraries that run in several different execution environments and I need a facade to allow adapting the metrics reporting to those environments.

Things that I want:

 * multiple classes as analogs of "Logger" -- "Timer", "Counter", "Meter", "Accumulator".
 * a way to record instance-info in a context-dependent way (i might have a single DBConnection class, with multiple 
   singleton instances and i want to measure things separately) -- this might mean non-static monitors
   
   
```
  private static final Timer myTimer = MetricFactory.getTimer(MyClass.class, "mine");
```

```
  private static final TimerFactory myTimerFactory = MetricFactory.getTimerFactory(MyClass.class, "mine");
  
  private final Timer myTimer;
  
  public MyClass(final String id) {
    this.myTimer = myTimerFactory.get(id);
  }
```


