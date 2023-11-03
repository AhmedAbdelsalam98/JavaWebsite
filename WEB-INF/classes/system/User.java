package system;

import java.util.*; 


public class User{

    String userName = "";
    String firstName ="John";
    String lastName ="Smith";
    String email ="test@uon.edu.au";
    String contactNum ="0412345678";
    

    public User(String u){
        userName = u;
    }

    // Getters
    public String getUsername(){        return userName;         }
    public String getFirstName(){       return firstName;    }
    public String getLastname(){        return lastName;     }
    public String getEmail(){           return email;   }
    public String getContact(){         return contactNum;   }

    // Setters
    public void setUsername(String username){    userName = username;  }
    public void setFirstName(String fName){      firstName = fName;  }
    public void setLastname(String lName){       lastName = lName;  }
    public void setEmail(String nEm){         email = nEm;  }
    public void setContact(String cNum){         contactNum = cNum;  }
}