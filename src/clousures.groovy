//создаем массив и заполняем значениями
def arr = [2, 3, 8, 500, -5, 0 ,101]
//описываем замыкание, которое сортирует по возрастанию
def temp = 0
def mergeSort = { a ->

        a.eachWithIndex {it, index ->
            for( j = a.size()-1; j > index; j-- ){
            if (a[j - 1] > a[j]) {
                temp = a[j-1]
                a[j - 1] = a[j]
                a[j] = temp
            }
            }
        }
    }

//вызываем замыкание
print mergeSort(arr)