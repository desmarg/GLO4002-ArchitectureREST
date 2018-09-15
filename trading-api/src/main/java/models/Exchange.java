package models;

class Exchange 
{ 
    // static variable single_instance of type Singleton 
    private static Exchange instance = null; 
  
    // variable of type String 
    public String s; 
  
    // private constructor restricted to this class itself 
    private Exchange(){ 
        s = "Hello I am a string part of Singleton class"; 
    } 
  
    // static method to create instance of Singleton class 
    public static Exchange getInstance() 
    { 
        if (instance == null) 
            instance = new Exchange(); 
  
        return instance; 
    } 
} 