# clj-nativedep

A Clojure library designed to normalise inclusion of native libraries
in projects.

## Usage

Add [clj-nativedep "0.1.0"] to your project.clj dependencies.



(use 'com.vnetpublishing.clj.nativedep)<br />
(load-resource resource_path app_id app_version lib_name)<br />
<br />
Example:<br />
(load-resource "/mylib.dll" "my-app-name" "1.0.0" "mylib")<br />

See API documentation in doc/ for more information

### From Java

The following java interoperability methods are available for Java 
applications in the namespace com.vnetpublishing.clj.nativedep

* Boolean isWin() - Calls (win?)
* String getArchName() - Calls (get-arch-name)
* void loadResource(String resource_path, 
   String app_id, 
   String app_version, 
   String lib_name) - Calls (load-resource resource_path app_id app_version lib_name)

### Install to localrepo

Execute the following

lein uberjar <br />
lein localrepo install target/clj-nativedep-0.1.8-standalone.jar com.vnetpublishing.clj/clj-nativedep 0.1.0

Finally add [com.vnetpublishing.clj/clj-nativedep "0.1.0"] to your project.clj dependencies

## License

Copyright © 2014 Ralph Ritoch - ALL RIGHTS RESERVED

