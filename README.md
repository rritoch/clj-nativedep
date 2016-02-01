# clj-nativedep

A Clojure library designed to normalize inclusion of native libraries
in projects.

## Usage

Add [clj-nativedep "0.2.1"] to your project.clj dependencies.


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
* Boolean isMac() - Calls (mac?)
* String getArchName() - Calls (get-arch-name)
* void loadResource(String resource_path, 
   String app_id, 
   String app_version, 
   String lib_name) - Calls (load-resource resource_path app_id app_version lib_name)
 
### Install to localrepo from sources

Execute the following from the project directory (containing project.clj)

lein install

Finally add [clj-nativedep "0.2.2-SNAPSHOT"] to your project.clj dependencies

## License

Copyright © 2014-2015 Ralph Ritoch - ALL RIGHTS RESERVED

