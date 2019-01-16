Конфигурация `application.properties`

`com.github.hvits3rk.mail.url=imaps://<логин>:<пароль>@<домен почты>`

Этот параметр нужен для настройки соединения с почтовым сервисом

`com.github.hvits3rk.mail.folder=/INBOX`

Директория куда будет приходить почта

---
`data.sql`

Начальные данные для базы данных компаний. 
```sql
INSERT INTO COMPANY_INFO (ID, COUNT, DESCRIPTION, NAME, NUMBER, PRICE, VENDOR)
    VALUES (1, 'Наличие', 'Описание', 'Hv1ts3rk <romkacg@gmail.com>', 'Каталожный номер', '`Цена, руб.`', 'Бренд');
```
Сопоставление названия колонок фирмы поставщика.

`NAME` - это имя отправителя почты.