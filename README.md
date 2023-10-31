### 關於本專案
嗯............它不是只擁有一個功能  
裡面包含多個不相關的操作練習，包含詞組搜尋練習、操作 Mongodb、操作 MySQL  
(同時在通個專案操作兩種不同類型資料庫感覺不太好@@)

#### 首先要知道
* 先確認是否安裝JDK   
版本會影響 dependency 能否運行  
因為有用 [spring-dotenv](https://github.com/paulschwarz/spring-dotenv#installation) 用來進行環境設定  
這 project 是使用 JDK 11 以上，所以 dependency 要用 spring-dotenv 規定的版本

* `/src/main/java/com/example/demo` 的 `DemoApplication` 為執行專案入口  
`點選檔案右鍵 > Run 'DemoApplication.main()'` 專案即會啟動

### 詞組搜尋練習
* `.env` 裡的 FILEPATH 是專案 clone 後 `item.txt` 或是 `item-short.txt` 的檔案位置  
  讓我們能夠載入對應的詞組庫進行搜尋詞組
* 裡面有三種搜尋方式，`OriginController` `FindItemController` `ArrayKMPController`

#### API
http://localhost:8080/api/origin?name= (最一開始寫出的暴力解法)  
http://localhost:8080/api/findItem?name= (使用 KMP 串成字串解法)  
http://localhost:8080/api/arraykmp?name= (使用 KMP 做成陣列解法)  
`=` 後面接要傳進去的 pattern 詞組

專案順利打開後，可以試著用 postman 打看看

### 操作 Mongodb Atlas
* 目前只有 `GET/plants` 功能

#### API
http://localhost:8080/api/planets/all

### 操作 MySQL
* 包含了 CRUD 等功能

#### API
* `GET: /api/hamaya/brand` http://localhost:8080/api/hamaya/brand
* `GET: /api/hamaya/brand/{id}` http://localhost:8080/api/hamaya/brand/25
* `POST: /api/hamaya/brand` http://localhost:8080/api/hamaya/brand
  * Body > json  
    `{
    "name":"<品牌名稱>"
    }`
* `PUT: /api/hamaya/brand/{id}` http://localhost:8080/api/hamaya/brand/25
  * Body > json  
      `{
      "name":"<品牌名稱>"
      }`
* `DELETE: /api/hamaya/brand/{id}` http://localhost:8080/api/hamaya/brand/25