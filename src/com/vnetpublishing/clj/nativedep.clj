;com.vnetpublishing.clj.nativedep
; <br />Author: Ralph Ritoch <rritoch@gmail.com>
(ns ^{
     :author "Ralph Ritoch <rritoch@gmail.com>"
     :doc "Clojure Native Dependency Support Library"
   } com.vnetpublishing.clj.nativedep
  (:gen-class
     :name com.vnetpublishing.clj.nativedep
     :prefix -
     :methods [
       #^{:static true} [isWin [] Boolean]
       #^{:static true} [^{:static true} getArchName [] String]
        #^{:static true} [^{:static true} loadResource [String String String String] void]
      ]
  )
)

(defn get-system-properties
  "Get System Properties"
  []
  (let [sys-properties (System/getProperties)
       properties-i (.iterator (.keySet sys-properties))
    ]
    (if (.hasNext properties-i)
      (loop [k (.next properties-i) properties {}]
        (if (not (.hasNext properties-i))
          (assoc properties (keyword k) (System/getProperty k)) 
          (recur
            (.next properties-i)
            (assoc properties (keyword k) (System/getProperty k))
          )
        )
      )
      {}
    )
  )
)

(defn get-arch-name
  "Get current nativedep architecture name"
  []
  (let [properties (get-system-properties)
      normal-os (if (:os.name properties) (clojure.string/lower-case (clojure.string/replace (:os.name properties) #"\s+" "-")) "")
      normal-arch (if (:os.arch properties) (clojure.string/lower-case (:os.arch properties)) "")
    ]
    (str normal-os "-" normal-arch)
  )
)

(defn get-home-path
  "Get home path"
  []
  (let [properties (get-system-properties)]
    (if (:user.home properties) (:user.home properties) "")
  )
)

(defn get-file-separator
  "Get file separator"
  []
  (let [properties (get-system-properties)]
    (if (:file.separator properties) (:file.separator properties) "/")
  )
)

(defn win?
  "Is Windows?"
  []
  (let [properties (get-system-properties)]
    (if 
      (and
        (:os.name properties)
        (re-matches 
          #"win.*" 
          (clojure.string/lower-case 
            (:os.name (get-system-properties))
          )
        )
      ) 
      true 
      false
    )
  )
)

(defn load-resource
  "Load native dependency from resource"
  [resource-path app-id app-version lib-name]
  (let [
     arch-name (get-arch-name)
     home-path (get-home-path)
     ds (get-file-separator)
     cache-path (clojure.string/join ds [home-path ".clj-nativedep" app-id app-version arch-name ])
     cache-file (java.io.File. (clojure.string/join ds [cache-path (if (win?) (str lib-name ".dll") (str "lib" lib-name ".so"))]))
     resource (.getResource (type "") resource-path)
     resource-conn (if resource (.openConnection resource))
     pkg-lib-mod-date (if resource-conn (java.util.GregorianCalendar.))
     cache-lib-mod-date (if (.exists cache-file) (java.util.GregorianCalendar.))
    ]
    (try
      
      (let [p (java.io.File. cache-path)]
        (if (.exists p)
          nil
          (.mkdirs p)
        )
      )
      (if cache-lib-mod-date
        (.setTimeInMillis cache-lib-mod-date (.lastModified cache-file))
      )
      
      (if pkg-lib-mod-date
        (.setTimeInMillis pkg-lib-mod-date (.getLastModified resource-conn))
      )
    
      ; check if file in cache
      (if
        (and
          pkg-lib-mod-date
          (or
            (not cache-lib-mod-date)
            (> (.compareTo cache-lib-mod-date pkg-lib-mod-date) 0)
          )
        )
        
          (let[buffer (byte-array 1024)
               in (.getInputStream resource-conn)
               out (java.io.FileOutputStream. cache-file)
             ]
             (try
             (loop [bc (.read in buffer)]
               (if (< bc  0)
                 nil
                 (recur
                   (do
                     (.write out buffer 0 bc)
                     (.read in buffer)
                   )
                 )
               )
             )
             (finally
               (try 
                  (.close in)
                  (catch Throwable t nil)
               )
               (try
                 (.flush out)
                 (catch Throwable t nil)
             )
               (try 
                 (.close out)
                 (catch Throwable t nil)
             )
              )
            )
        )
      )
      (System/load (.toString cache-file))
      (finally 
        (if resource-conn
          (try
            (.close resource-conn)
            (catch Throwable t nil)
          )
        )
      )
    )
  )
)

(defn -isWin
  "Is Windows? (interop)"
  []
  (win?)
)

(defn -getArchName
  "Get Architecture Name (interop)"
  []
  (get-arch-name)
)

(defn -loadResource
  "Load native dependency from resource (interop)"
  [resource-path app-id app-version lib-name]
  (load-resource resource-path app-id app-version lib-name)
)

;; End of namespace com.vnetpublishing.clj.nativedep