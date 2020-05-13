import groovy.xml.*

//вводим строку поиска с консоли
print "Input word: "
String input = System.in.newReader().readLine()
//замыкание, принимает строку, парсит RSS ленту, находит количество вхождений принятой строки
//и выводит это кол-во в консоль.
def finder (str) {
    def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
    def separator = System.getProperty("line.separator")
    def url = "http://rss.cnn.com/rss/edition_world.rss"
    def channel = new XmlParser().parse(url).channel[0]
    //паттерн части description, который нам не нужен
    def pattern = ~"(<img).*?(/>)"
    print("The number of occurrences of your word \'$str\' is: ")
    println channel.item.description.text().replaceAll(pattern, "").count(str)
    def sb = new StringBuilder()
    def sb2 = new StringBuilder()
    //бежим по новостям, находим исходное слово и если оно встречается, берем pubDate
    channel.item.each {
        if (it.description.text().replaceAll(pattern, "").contains(str)) {
            sb << it.pubDate.text() + separator
        }
    }
    //пишем даты в файл, имя файла совпадает с именем запроса (?)
    File outputRss = new File(path, str + ".txt")
    outputRss << sb
    //файл с уникальными датами
    File uniquePubDates = new File (path, "uniquePubDates.txt")
    //файла может не быть, создаем его
    if (!uniquePubDates.exists())
        uniquePubDates.createNewFile()
    //создаем set для хранения уникальных дат
    def set = []
    def mySet = set as Set
    sb.eachLine { sbline ->
        //выделяем только дату и добавляем ее в set
        def searchStr = (sbline =~ /\w{3}, \d{2} \w{3} \d{4}/)
        mySet.add(searchStr[0])
    }
    //в цикле проверяем наличие дат из set в файле, если нет, то добавляем через sb2
    for(i in mySet)
        if (!uniquePubDates.text.contains(i.toString()))
            sb2 << i.toString() + separator
    //добавляем уникальные даты в файл
    uniquePubDates << sb2
}
//вызываем замыкание, прередавая в него введенное слово
finder(input)