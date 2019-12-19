# shadow1

ลองใช้ shadow-cljs 

## รัน docker image

```
sudo docker run --rm -it -u 1000 --net=host -v $(pwd):/work -e HOME=/work -w /work veer66/node-jdk bash
```

## สร้าง project 

```
npx create-cljs-project shadow1
```

```
cd shadow1
mkdir src/main/webapp
mkdir public
```

## แก้ไฟล์ src/main/webapp/core.cljs

```Clojure
(ns webapp.core)

(defn init []
  (let [p-main (js/document.querySelector "p#main")
        txt (js/document.createTextNode "FROM shadow1.webapp.core/init")]
    (.appendChild p-main txt)))
``` 

## แก้ไฟล์ public/index.html


```HTML
<!DOCTYPE HTML>
<html>
  <body>
    <h1>DEMO</h1>
    <p id="main"></p>
  </body>
  <script src="js/main.js"></script>
</html>
```

## แก้ไฟล์ shadow-cljs.edn

```Clojure
;; shadow-cljs configuration
{:source-paths
 ["src/dev"
  "src/main"
  "src/test"]

 :dependencies
 []

 :dev-http {8080 "public"}
 :builds
 {:webapp
  {:target :browser
   :modules {:main {:init-fn webapp.core/init}}}}}

```

## รับ shadow-cljs

```
npx shadow-cljs watch webapp
```

## ลอง

เปิดเว็บ localhost:8080

![Web browser screenshot](https://file.veer66.rocks/pic/shadow1.png)
