// This decorator design pattern is taken from medium website
// Lets see the implementation

// Always describe interface for you component to provide DIP
interface Pizza {
    String getDescription();
    double getCost();
    int getCalories(); // Extra method to show delegation
    int getPrepTime(); // Extra method to show delegation
}

// 2. The Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() { return "Thin crust dough"; }

    @Override
    public double getCost() { return 5.0; }

    @Override
    public int getCalories() { return 200; }

    @Override
    public int getPrepTime() { return 10; }
}

// 3. The Abstract Decorator (The "Shield" against boilerplate)
// TRUE MOTIVE: This class implements the interface once. 
// It "delegates" (forwards) every call to the wrapped object. 
// This way, concrete decorators don't have to implement methods they don't change.
// And Decorator is half baked and thats why we make this as abstract to avoid instantiation
abstract class PizzaDecorator implements Pizza {
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza pizza) {
        this.decoratedPizza = pizza;
    }

    // Default delegation: Just pass the request through.
    @Override public String getDescription() { return decoratedPizza.getDescription(); }
    @Override public double getCost() { return decoratedPizza.getCost(); }
    @Override public int getCalories() { return decoratedPizza.getCalories(); }
    @Override public int getPrepTime() { return decoratedPizza.getPrepTime(); }
}

// 4. Concrete Decorators
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Mozzarella Cheese";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
    // NOTICE: We DID NOT implement getCalories() or getPrepTime().
    // The Abstract class handles the delegation for us!
}


class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Spicy Pepperoni";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 2.0;
    }

    @Override
    public int getPrepTime() {
        // Pepperoni takes extra time, so we override ONLY this.
        return decoratedPizza.getPrepTime() + 5;
    }
}


// 5. Client Code
public class DecoratorMain {
    public static void main(String[] args) {
        // true motive: Stack functionality like Russian Dolls at RUNTIME
        Pizza myPizza = new PlainPizza(); // Initial object 
        myPizza = new CheeseDecorator(myPizza);  // Initial object wrapped with cheese properties
        myPizza = new PepperoniDecorator(myPizza); // cheese object wrapped with pepperini properites

        // and reference is being same so that it can accumulate the wrapper properties

        System.out.println("Order: " + myPizza.getDescription());
        System.out.println("Total Cost: $" + myPizza.getCost());
        System.out.println("Total Prep Time: " + myPizza.getPrepTime() + " mins");
        System.out.println("Calories: " + myPizza.getCalories());
    }
}
