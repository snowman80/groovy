//создаем массив и заполняем значениями
def arr = [2, 3, 8, 500, -5, 0 ,101]
//описываем замыкание, которое сортирует по возрастанию
def temp = 0
def Sort = { a ->

    a.each {                        //для каждого элемента в массиве
        (a.size()-1..1).each {      //range индексов с конца, для каждого из них
            if (a[it - 1] > a[it]) {//собственно перестановка
                temp = a[it-1]
                a[it - 1] = a[it]
                a[it] = temp
            }
            }
        }
    }

//вызываем замыкание
print Sort(arr)