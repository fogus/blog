At various times I've needed something very similar to `python -m SimpleHTTPServer <port>` readily accessible in my Clojure projects.  Most of the time I just used Python but recently I've wanted to rework some examples from [The Joy of Clojure](http://www.joyofclojure.com) and thought a simple Lein plugin would make a nice addition.  The result: lein-simpleton -- I hope you'll find it useful.

## Usage

See the [lein-simpleton project page](http://www.github.com/fogus/lein-simpleton) for installation instructions for the latest version.

### Running

By default Simpleton provides a file-server in the directory where it's run.  To run:

    $ lein simpleton <port>

Navigate to `localhost:<port>` and see a directory listing.  Click around to navigate directories and download (some) files.

#### Running the echo server

Simpleton can also run an echo server that reflects the incomming HTTP headers back as [EDN](https://github.com/edn-format/edn) data.

    $ lein simpleton <port> echo

Navigating to `localhost:<port>` to download an EDN file.

### Running the hello server

Simpleton can also run a "Hello" server that just returns a canned text string.

    $ lein simpleton <port> hello

Navigating to `localhost:<port>` to see the message.

That's all for now.

:F
