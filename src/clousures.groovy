//создаем массив и заполняем значениями
def arr = [2, 3, 8, 500, -5, 0 ,101]
//описываем замыкание, которое сортирует по возрастанию
def mergeSort(list) {
    mergeSort(list, 0, list.size() - 1) }

def mergeSort(list, start, end) {
    if (start < end) {
        def middle = (int) ((start + end) / 2)
    mergeSort(list, start, middle)
        mergeSort(list, middle + 1, end)
        doMerge(list, start, middle, end)  }
    return list }

def doMerge(list, start, middle, end) {
    def l = list[start..middle]
    def r = list[middle + 1..end]
    l += 10000000
    r += 10000000
    def i = 0
    def j = 0
    for (k in start..end) {
        if (l[i] <= r[j]) {
            list[k] = l[i]
            i += 1    }
        else {
            list[k] = r[j]
            j += 1
        }
    }
}

//вызываем замыкание
print mergeSort(arr)