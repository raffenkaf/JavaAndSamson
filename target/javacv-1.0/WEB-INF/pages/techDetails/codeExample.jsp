<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div> 
	<H1>Пример алгоритмов сортировки.</H1>
	<pre>
		public class MySorts{			<br/>
			//Сортировка вставками		<br/>
			public int[] insertionSort(int[] data){ <br/>
<br/>
			//Если массив имеет меньше 2 значений, то возврат без изменений <br/>
			if (data.length &lt 2) { <br/>
				return data; <br/>
			}<br/>
<br/>
			//Внешний перебор от 1 элемента до последнего 	<br/>
			for (int outerIterator = 1; outerIterator &lt data.length; outerIterator++) { 		<br/>
				//Внутренний перебор от (текущий элемент внешнего перебора - 1) до 0 элемента 	<br/>
				for (int innerIterator = outerIterator-1; innerIterator &gt= 0; innerIterator--) {<br/> 
					//Если текущий элемент внутреннего перебора меньше	<br/>
					//следующего элемента то они меняются местами		<br/>
					if (data[innerIterator+1] &gt data[innerIterator]) {	<br/>
						change(data, innerIterator, innerIterator+1);	<br/>
					}	<br/>
				}<br/>
			}<br/>
<br/>
			return data;<br/>
		}<br/>
<br/>
		//Сортировка пузырьком<br/>
		public int[] bubbleSort(int[] data){<br/>	
			<br/>	
			//Если массив имеет меньше 2 значений, то возврат без изменений<br/>	
			if (data.length &lt 2) {<br/>	
				return data;<br/>	
			}<br/>	
			<br/>	
			//Внешний перебор от 0 элемента до последнего <br/>	
			for (int outerIterator = 0; outerIterator &lt data.length; outerIterator++) {<br/>	
				//Внутренний перебор от 1 элемента до последнего 	<br/>
				for (int innerIterator = 0; innerIterator &lt data.length-1; innerIterator++) {<br/>	
					//Если текущий элемент внутреннего перебора меньше	<br/>
					//следующего элемента то они меняются местами		<br/>
					if (data[innerIterator] &lt data[innerIterator+1]) {<br/>	
						change(data, innerIterator, innerIterator+1);	<br/>	
					}	<br/>
				}		<br/>
			}			<br/>
<br/>
			return data;<br/>
		}				<br/>
<br/>
		//Сортировка выборкой
		public int[] selectionSort(int[] data){
			int indexOfLesser = 0;
			
			//Внешний перебор от 0 элемента до последнего
			for (int outerIterator = 0; outerIterator &lt data.length; outerIterator++) {
				//Индекс наименьшего элемента, по умолчанию = значение внешнего итератора
				indexOfLesser=outerIterator;
				//Внутренний перебор от текущего элемента внешнего перебора 
				//до последнего элемента массива
				for (int innerIterator = outerIterator; innerIterator &lt data.length; innerIterator++) {
					//Если элемент, с индексом = текущему итератору внутреннего перебора, 
					//меньше элемента, с индексом наименьшего элемента, 
					//то индекс наименьшего элемента становиться = 
					//текущему итератору внутреннего перебора 		
					if (data[indexOfLesser] &gt data[innerIterator]) {
						indexOfLesser=innerIterator;
					}
				}
				//Замена элемента, с индексом внешнего итератора
				//на элемент, с индексом наименьшего элемента
				change(data, outerIterator, indexOfLesser);			
			}
			return data;
		}

		//Быстрая сортировка, сделано рекурсивно
		public int[] quickSort(int[] data){
			
			//Если массив из 1 элемента, то просто вернуть массив
			if (data.length == 1) {
				return data;
			}//Если массив из 2 элементов, 
			else if (data.length == 2) {
				//сравнить элементы, и если 1 элемент меньше 2, то просто вернуть массив
				if (data[0] &lt data[1]) {
					return data;
				}//если 2 элемент меньше 1, поменять элементы местами и вернуть массив
				else{
					change(data, 1, 0);
					return data;
				}
		
			}//Если массив больше чем из 2 элементов 
			else if (data.length &gt 2) {
				//ищем среднее для ускорения поиска
				int posOfAverageValue = findAverage(data);
				//делим массив пополам, для этого сначала создаем 2 временных массива 
				//такой же длины, как и первичный, поскольку нельзя предсказать
				//сколько будет значений больше или меньше среднего
				int tmpFirstHalfOfData[] = new int[data.length];
				int tmpSecondHalfOfData[] = new int[data.length];
				
				//создаем итераторы для первого и второго массива
				//потом сравниваем каждый элемент со средним значением
				//и те, что меньше или равны копируем в 1 массив + увеличиваем 1 итератор
				//все иные значения копируем во 2 массив + увеличиваем 2 итератор
				int firstIterator = 0, secondIterator = 0;
				for (int outerIterator = 0; outerIterator &lt data.length; outerIterator++) {
					if (data[outerIterator] &lt= data[posOfAverageValue]) {
						tmpFirstHalfOfData[firstIterator++] = data[outerIterator];
					}
					else{
						tmpSecondHalfOfData[secondIterator++] = data[outerIterator];
					}
				}
				
				//создаем 2 массива по уже известным размерам для ускорения работы сортировщика
				//потом копируем значения из временных массивов в постоянные
				int firstHalfOfData[] = new int[firstIterator];
				int secondHalfOfData[] = new int[secondIterator];
				for (int i = 0; i &lt firstHalfOfData.length; i++) {
					firstHalfOfData[i] = tmpFirstHalfOfData[i];				
				}
				for (int i = 0; i &lt secondHalfOfData.length; i++) {
					secondHalfOfData[i] = tmpSecondHalfOfData[i];				
				}
			
				//вызываем функцию quickSort для первой части массива
				//потом копируем результат в массив, который служил аргументом при вызове функции 
				//вызываем функцию quickSort для второй части массива
				//потом копируем результат во вторую часть массива, который служил аргументом при вызове функции
				System.arraycopy(quickSort(firstHalfOfData), 0, data, 0, firstIterator);
				System.arraycopy(quickSort(secondHalfOfData), 0, data,  firstIterator, secondIterator);
				
			}
		
		
			return data;
		}
		
		//Поиск индекса элемента = среднему значению всех элементов массива 
		private int findAverage(int[] data) {
			int posOfAverageValue = 0;
			int sum=0;
		
			for (int i = 0; i &lt data.length; i++) {
				sum+=data[i];
			}
		
			int difference = Integer.MAX_VALUE;
			
			for (int i = 0; i &lt data.length; i++) {
				if ((data[i]-(sum/data.length)) &lt difference) {
					difference = data[i]-(sum/data.length);
					posOfAverageValue = i;
				}
			}
		
			return posOfAverageValue;
		}
		
		//Поменять значения элементов массива местами
		private void change(int[] data, int valueOne, int valueTwo) {
			int tmp;
			tmp = data[valueOne];
			data[valueOne] = data[valueTwo] ; 
			data[valueTwo] = tmp;
		}
}
	</pre>
</div>