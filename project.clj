(defproject u_s "0.1.0-SNAPSHOT"
  :description "Tile based word game"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/carmine "2.8.0"] ;; redis client
                 [http-kit "2.1.16"]
                 [compojure "1.3.1"]]
  :main ^:skip-aot u-s.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
