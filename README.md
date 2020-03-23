# blockoid

A thin Clojurescript wrapping of [Blockly](https://developers.google.com/blockly/). 

## Usage

Add to dependencies:

    [org.parkerici/blockoid "0.0.2"] 

See [the example](example/project.clj) for details. 

In your code, add the `require`:

```clojure
(ns ...
  (:require [org.parkerici.blockoid.core :as blockoid]))
```

Usage details are in (a separate document)[doc/blockoid.md].


### Deployment to PICI mvn repo

TODO this is destined for public release, so this and related changes are temporary.

```bash
export GH_USER=mtravers
export GH_TOKEN=<personal token supplied by github>
lein deploy github
```

## License

Copyright © 2020 Parker Institute for Cancer Immunotherapy

This program and the accompanying materials are made available under the
terms of the blah blah TODO
