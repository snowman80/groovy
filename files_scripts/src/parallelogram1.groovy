//описываем параллелограмм
class Para {
    int length
    int width
}
//папка в которой будем работать
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")

//функция принимает максимальное число, возвращает случайное от 1 до этого числа включительно
static int rand(int x) {(int)(1 + Math.random()*x)}

ArrayList ps = new ArrayList()
//создаем 1000 параллелограммов и добавляем в массив
1000.times {
    ps.add(new Para(length: rand(1000), width: rand(1000)))
}
//4.1 Вывести в файл в 2 колонки длину и ширину параллелограммов у которых длинна больше 400
File length400 = new File (path, "length400.txt")
def sb = new StringBuilder()
ps.each {
    if (it.length > 400)
        sb << it.length + "," + it.width + separator
}
length400 << sb.toString()

//4.2 Вывести в файл в 2 колонки длину и ширину
//параллелограммов и сумму длин и широт у которых сумма длин и широт больше 400
File halfperimert400 = new File (path, "halfperimetr400.txt")
def sb2 = new StringBuilder()
ps.each {
    if (it.length + it.width > 400)
        sb2 << it.length + "," + it.width + "," + (it.length + it.width) + separator
    else sb2 << it.length + "," + it.width + separator
}
halfperimert400 << sb2.toString()

//4.3 Площадь параллелограмма по двум сторонам найти невозможно, поэтому, считаем что всё это прямоугольники
//поэтому площадь находим length*width
//Вывести на экран индекс элемента в массиве с максимальной площадью
int sMax = 0
int ind = 0
ps.eachWithIndex {it, index ->
    if (it.length * it.width > sMax) {
        sMax = it.length * it.width
        ind = index
    }

}
println(ind)