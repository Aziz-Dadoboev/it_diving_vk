# IT-Дайвинг от ВК.

Подробнее: https://vk.com/itdiving

## Задание
Сверстать экран:

<img src="https://github.com/Aziz-Dadoboev/it_diving_vk/blob/main/ITDivingCase/screens/call_screen.png" width="300" height="600">

Минимальная версия Android - API 23 (6.0)
### Нижняя часть экрана
 - По клику на кнопку с иконкой камеры происходит замена иконки на камеру / зачеркнутую камеру (понадобится 2 иконки камеры) 
 - По клику на кнопку с микрофоном (понадобятся 2 иконки микрофона) - анаогично 
 - По клику на иконку с рукой открывается AlertDialog c текстом “привет” 
 - По клику на красную кнопку происходит выход из приложения 

### Верхняя часть экрана 
 - По клику на сообщения открыть системное приложения для смс 
 - По клику на участников происходит переход на экран со списком контактов. Можно сделать упрощенный вариант как в телефонной книге. С картинкой и именем 
 - По клику на верхнюю правую иконку плитки участников меняются местами 

Нижний ряд кнопок выравнивается так, чтобы расстояние между ними было одинаковым. 
Стандартные отступы по краям - 16d

Строгих требований к соответствию иконок - нет, можно брать любые похожие.
Единственное требование - использовать только векторные иконки.
- Заменить стандартную иконку приложения на любую свою
- Фотки пользователей можно брать любые
- У TextView есть свойства drawableLeft, drawableRight… - для установки иконок
рядом с текстом
Имя пользователя может быть длиннее ширины плитки необходимо сделать
обрезание текста 3-мя точками в конце как на скриншоте


### Дополнительно реализовано
- При нажатии на элемент списка контактов, на главном экране меняется "вторая плитка".
- Иконка микрофона рядом с именем пользователя синхронно меняется c кнопкой микрофона.


## Результат

APK: https://github.com/Aziz-Dadoboev/it_diving_vk/blob/main/ITDivingCase/apk/ITDiving.apk?raw=true

### Главный экран
<img src="https://github.com/Aziz-Dadoboev/it_diving_vk/blob/main/ITDivingCase/screens/1.png"  width="300" height="600">

### Экран с контактами
<img src="https://github.com/Aziz-Dadoboev/it_diving_vk/blob/main/ITDivingCase/screens/contcts.png"  width="300" height="600">

### Звонок с королём Саудовской Аравии
<img src="https://github.com/Aziz-Dadoboev/it_diving_vk/blob/main/ITDivingCase/screens/call_saudi_arabia.png"  width="300" height="600">
