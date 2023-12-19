A while back I came across an interesting snippet of Clojure code[^attribution] controlling iTunes.  I played around with it and eventually morphed it into the code posted on my [@learnclojure](http://twitter.com/learnclojure/status/25020783577) account.  This morning I came across a similarly themed post about [Scala's interaction with Applescript](http://blog.getintheloop.eu/2010/9/22/calling-applescript-from-scala/) and it inspired me to create the following:

[sourcecode lang="scala" gist="591683"]object iTunes {
  val mgr = new ScriptEngineManager
  val engine = mgr.getEngineByName("AppleScript")

  def apply(cmd:String) = {
    this.engine.eval("tell application \"iTunes\" to " + cmd)
    this
  }

  def ?() = this("pause")

  def !() = this("play")

  def ->|() = this("next track")

  def |<-() = this("previous track")
}

iTunes ?
iTunes ! 
iTunes ->|
iTunes |<-
[/sourcecode]

They can also be composed as `iTunes.->|().?`.  Nothing ground-breaking of course, but the next step is to create a complete iTunes DSL with command compositions allowing things like fast-forward, rewind, jumping to time segments, moving around by time increments, etc.  Hopefully someone else has/will done/do this for me.

:f

[^attribution]: I have looked deeply, but cannot find the original author.  If you know then please relay.
