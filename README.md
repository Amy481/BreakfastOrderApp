# BreakfastOrderApp
## 介紹
行動應用程式開發課程，andriod studio的實作專題，簡單的早餐點餐app
可展示不同分類的餐點，並允許用戶查看餐點內容和將餐點添加到購物車中

## UI設計
[UI設計Figma](https://www.figma.com/file/rshcIHcCfaXGw9vubt6ZzD/APP-COURSE-TEMPLATE?type=design&node-id=0%3A1&t=qejkoD3wPbvLixDP-1)
課程限制只能用規定內元件實作

## 功能
- 顯示主餐、小吃和飲料等不同分類的餐點
- 用戶可以點擊餐點列表項，查看該餐點的詳細信息
- 用戶可以將餐點添加到購物車中
- 添加餐點時可以選擇店家提供的客製化選項或添加附註
- 購物車中可刪除餐點和選擇送餐時間
- 用戶可以在購物車中查看已選擇的餐點和總金額，並進行結賬操作

## 程式細節
- 使用 Android 的 Fragment 架構，將菜單頁面分為占位符片段
- 使用 PageViewModel 管理片段的索引值和相關數據，切換不同分類的餐點時會投放該分類的餐點listview和內容
- 使用 MenuDatabase 類操作菜單數據庫，從數據庫中獲取不同分類的餐點數據
- 使用 CartDatabase 類操作購物車數據庫，包括添加餐點到購物車和檢查購物車中的餐點數量
- 通過設置點擊事件監聽器，當用戶點擊餐點列表項時，會打開餐點詳情頁面
- 使用 Intent 在不同活動之間傳遞數據，例如將餐點的 productId 傳遞給餐點詳情頁面
- 通過判斷購物車數據庫中的數據，控制結賬按鈕的顯示和隱藏
- 為結賬按鈕設置點擊事件監聽器，當用戶點擊按鈕時，會打開購物車頁面並顯示購物車中的餐點和總金額
**詳細的代碼解釋和說明可在代碼注釋中找到**

## 使用操作
1. clone或下載該項目的源代碼到本地
2. 使用 Android Studio 打開項目
3. 挑選運行模擬機後運行

## 使用技術
- Android
- Java
- SQLite 數據庫

## 老師點評
1. 建議在應用中添加用戶登入和登出功能
2. 建議增加店家管理功能，如管理菜單、處理訂單等

### [報告ppt](https://www.canva.com/design/DAFksKoA7kU/OUaCaf8jc_nEd2La-bO2tQ/edit?utm_content=DAFksKoA7kU&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
