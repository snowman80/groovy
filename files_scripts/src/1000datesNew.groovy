import java.text.SimpleDateFormat

//папка, куда будет сохранен файл
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//итоговый файл
File file = new File(path, '1000datesnew.txt')

def sb = new StringBuilder()
def separator = System.getProperty("line.separator")
//функция генерирует дату в заданном диапазоне
def start = Date.parse('yyyy-MM-dd', '0000-01-01')
def end   = Date.parse('yyyy-MM-dd', '2020-01-01')

static Date randomDate(Range<Date> range) {
    range.from + new Random().nextInt(range.to - range.from + 1)
}
//функция генерирует время
static String randomTime() {
    def hours = String.format("%02d", new Random().nextInt(24))
    def minsec = String.format("%02d", new Random().nextInt(60))
    return hours+":"+minsec+":"+minsec
}

//задаем формат даты
def sdf = new SimpleDateFormat("yyyy-MM-dd")
//создаем даты и записываем в файл
1000.times {sb << sdf.format(randomDate(start..end))+"T"+randomTime() + separator}
file << sb

//Принимаем это время за время по Central European Time,
//переводим в GMT и выводим в другой файл в формате 20130225T1825
sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
sdf.setTimeZone(TimeZone.getTimeZone('CET'))
File file2 = new File (path, "1000datesGMT.txt")
def sb2 = new StringBuilder()

file.eachLine {def date =  sdf.parse(it)
     sb2 << date.format("yyyyMMdd'T'HHmm",TimeZone.getTimeZone('GMT')) + separator}

file2 << sb2