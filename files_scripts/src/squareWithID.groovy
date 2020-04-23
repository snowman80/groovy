//описываем прямоугольник
class Square {
    int length
    int width
    String uuid
}
//папка в которой будем работать
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")

//функция принимает максимальное число, возвращает случайное от 1 до этого числа включительно
static int rand(int x) {(int)(1 + Math.random()*x)}

//Функция генерирует случайный UUID
def uuidGen = {UUID.randomUUID().toString()}

def map = new HashMap()
//создаем 1000 прямоугольников и добавляем в HashMap
1000.times {
    def uuid = uuidGen()
    map.put(uuid, new Square(length: rand(1000), width: rand(1000), uuid: uuid))
}
//5.1 Вывести в файл в 2 колонки длину и ширину параллелограммов у которых длинна больше 400
//дополнительно выводим key в 3 колонке
File length400 = new File (path, "length400.txt")
def sb = new StringBuilder()
map.findAll {it.value.length > 400}each {
        sb << it.value.length + "," + it.value.width + "," + it.key + separator
}
length400 << sb.toString()

//4.2 Вывести в файл в 2 колонки длину и ширину
//параллелограммов и сумму длин и широт у которых сумма длин и широт больше 400
File halfperimert400 = new File (path, "halfperimetr400.txt")
def sb2 = new StringBuilder()
map.findAll {it.value.length + it.value.width > 400}each {
        sb2 << it.value.length + "," + it.value.width + "," + (it.value.length + it.value.width) + separator
}
halfperimert400 << sb2.toString()

//4.3 Вывести на экран индекс элемента в массиве с максимальной площадью
//т.к. для хранения был выбран HashMap,индекс заменю на key
def maxSquare = map.max {it.value.length*it.value.width}
println(maxSquare.key)