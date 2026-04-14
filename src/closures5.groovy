//замыкание, которое возвраащет квадрат числа
def closure2 = {num1 -> num1*num1}
//создаем массив чисел
def numbers = [1, 3, 6, 15, 5, 100]
//используя each вызываем замыкание для каждого элемента массива numbers
numbers.each {println(closure2(it))}