(defproject u_s "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/carmine "2.8.0"] ;; redis client
                 ]
  :main ^:skip-aot u-s.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
