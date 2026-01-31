//This pattern is useful when you want to dynamically change the behavior of a class without modifying its code.

// An Interface, and its implementations provides the strategy 

// A Class which accept the Interface as composition (has a ) relation 

// The actual class which extends the Above class and provides the object of strategy implementation


// Interface
interface SortingStrategy {
    void sort(int[] array);
}

// 1st strategy implementation
class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort");
    }
}

// 2nd Strategy implementation
class MergeSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Merge Sort");
    }
}

// An actual class which provides Dependency injection with help of Composition
class SortingContext {
    private SortingStrategy sortingStrategy;               // Dependency inversion principle
    public SortingContext(SortingStrategy sortingStrategy) {  // We can define our strategy objetc through its constructor to avoid accidental call or perform method
        this.sortingStrategy = sortingStrategy;
    }

  public void setSortingStrategy(SortingStrategy sortingStrategy) { // extra method to set strategy with setter method also
        this.sortingStrategy = sortingStrategy;
    }
  
    public void performSort(int[] array) {
        sortingStrategy.sort(array);
    }
}

 
class Client{

  public static void main(String args[]) {
      SortingContext sc=new SortingContext(new BubbleSort());
      int[] arr={10,9,8,4,5,6,1};
      sc.performSort(arr);
      sc.setSortingStrategy(new MergeSortStrategy());
   }
  
}

