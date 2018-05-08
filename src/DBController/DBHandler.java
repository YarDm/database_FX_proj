package DBController;

/*
Класс реализующий соединение и запросы к базе данных
Все запросы должны быть прописаны заранее для исклюючения SQL-инъекций
Запрос в дочернюю базу должен производиться на основе выбранной строки родительской, то есть:
String parentQuery = categories.name;
Запрос в дочернюю базу будет выглядеть так:
"SELECT p.* FROM categories as c, products as p
WHERE c.name = " + parentQuery + " AND c.name = p.category_name"
 */
public class DBHandler {
    //todo: в SQL запрос должно подставляться значение из таблицы categories.name
}
