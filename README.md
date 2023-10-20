* 先確認是否安裝JDK   
版本會影響 dependency 能否運行  
因為有用 [spring-dotenv](https://github.com/paulschwarz/spring-dotenv#installation) 用來進行環境設定  
這 project 是使用 JDK 11 以上，所以 dependency 要用 spring-dotenv 規定的版本


* `.env` 裡的 FILEPATH 是專案 clone 後 `item.txt` 或是 `item-short.txt` 的檔案位置  
讓我們能夠載入對應的詞組庫進行搜尋詞組


* `/src/main/java/com/example/demo` 的 `DemoApplication` 為執行專案入口  
`點選檔案右鍵 > Run 'DemoApplication.main()'` 專案即會啟動


##### API
http://localhost:8080/api/origin?name= (最一開始寫出的暴力解法)  
http://localhost:8080/api/findItem?name= (使用 KMP 串成字串解法)  
http://localhost:8080/api/arraykmp?name= (使用 KMP 做成陣列解法)  
`=` 後面接要傳進去的 pattern 詞組

專案順利打開後，可以試著用 postman 打看看