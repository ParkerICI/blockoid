# blockoid

A thin Clojurescript wrapping of [Blockly](https://developers.google.com/blockly/). 

The combination of Blockly and Clojure allows for the easy creation of powerful combinatorial user interfaces and visual languages. See (this presentation)[https://drive.google.com/file/d/1Jfc94u42BDqmwSFDazTplDfVjzc1eVej/view?usp=sharing] for a real-world example of use.

file:doc/image1.png


## Usage

Add to dependencies:

    [org.parkerici/blockoid "0.3.5"] 

See [the example](example/project.clj) for details. 

In your code, add the `require`:

```clojure
(ns ...
  (:require [org.parkerici.blockoid.core :as blockoid]))
```

Usage details are in (a separate document)[doc/blockoid.md].

Blockoid loads Blockly via [a cljsjs package](https://github.com/cljsjs/packages/tree/master/blockly). If you need a newer version of Blockly, you will need to update cljsjs.

## License

Relased under MIT license. See the [LICENSE](LICENSE.md) file for details.
