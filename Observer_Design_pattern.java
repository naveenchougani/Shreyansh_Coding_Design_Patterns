// Question asked at walmart saying
// At amazon iphone stock not available, then Notify Me is to be designed

// Similarly Lets look at below java example to understandb clearly then we can write the above code
// A Job agency is will post the new job news, and that should notify the jobers subscribed to this agency
// The below example is from """"""Hello Byte"""""""" which explains the thing in a easy way.

// Assume A jobAgency have to push new job post to all the job seekers subscribed to it
// So initially there should be a class of Jobpost that contain job details
// Now there should be JobSeeker class which will implements an interface. Why interface?
// Today JobSeekers, tomorrow Researcher, day after tomorrow some other category may evolve but the core onJobPosted will not differ.
// Also we refer them via DIP so.. even if implemntation class category changed.. It will not created problem in the observable class /* important*/
// Like If you didn't have an interface, your EmploymentAgency would need a separate list for every new class:
// LinkedInBot: A class that automatically posts the job to social media.
// EmailService: A class that formats the job as an HTML email and sends it.
// List<JobSeeker>, List<LinkedInBot>, List<EmailService>.

// And every new class is created.. then we need switch or if else while calling their onPostMethod
/*
for(Object obs : observers) {
   if(obs instanceof JobSeeker) ((JobSeeker)obs).onJobPosted(job);
    if(obs instanceof LinkedInBot) ((LinkedInBot)obs).postToFeed(job);
}  */

class JobPost {
  private String title;
  public JobPost(String title){
    this.title=title;
  }
  public String getTitle(){
  return this.title;
  }
}

// Observer should have a method that helps its own implementation about an event 
// so this method can be invoked in observable being a bridge between them to push that event
interface Observer{
  void onJobPosted(JobPost job);  
}

class JobSeeker implements Observer {
  private String name;
  public JobSeeker(String name){
    this.name=name;
  }
  public onJobPosted(JobPost job){
  System.out.println("Hi "+name+"!. New Job Posted+"job.getTitle());
  }
}

interface Observable{
  void add(Observer observer);
  void remove(Observer observer)
  void notifyObservers(JobPost job);  
}

// Even for observable we need interace as EmployeeAgency can extend any thirdparty class or another class like Company
class EmploymentAgency implements Observable{
  private List<Observer> observers=new ArrayList<>(); // This is very important thing
                                                      // This list helps to store observers 

  public void add(Observer observer){
    observers.add(observer);
  }

  public void remove(Observer observer){
    observers.remove(observer);
  }

  public void notifyObservers(Jobpost job){   // This method helps to push its events
    for(Observer observer:observers){        // to its stored observers by accepting 
        observer.onJobPosted(JobPost job);    // required event object to be shared to observers
    }
  } 
}


// Main method to execute this.

class ExecuteObserverPattern{

  public static void main(String args[]) {
    
    Observable empAgency = new EmploymentAgency();
    
    Observer naveen=new JobSeeker("naveen");
    Observer karthik=new JobSeeker("karthik");

   empAgency.add(naveen);
   empAgency.add(karthik);

   JobPost javaDev=new Jobpost("Jave developer");

    empAgency.notifyObservers(javaDev);
  
  }
  
}


