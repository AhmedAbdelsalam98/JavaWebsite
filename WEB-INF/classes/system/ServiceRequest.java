package system;

import java.util.*; 


public class ServiceRequest{

    private String uuid;
    LinkedList<String> comments;
    String category, status, issue, title, restart, location;
    String wait = "Admin";
    String userSub = "";
    User user;

    public ServiceRequest(String cat, String issue, String title,String userSub, String res, String loc, User nU){
        uuid = UUID.randomUUID().toString();
        comments = new LinkedList<String>();
        category = cat;
        this.issue = issue;  
        this.title = title;
        this.userSub = userSub;
        status = "New";
        restart = res;
        location = loc;
        user = nU;
    }

    public void setUUIDString(String uuidStr) {uuid = uuidStr;}
    public String getUUIDstring() {return uuid;}
    public User getUserObj(){ return user; }
    public void setUserObj(User nU){ user = nU;    }
    public String getUsername(){        return user.getUsername();         }
    public String getFirstName(){       return user.getFirstName();    }
    public String getLastname(){        return user.getLastname();     }
    public String getContact(){         return user.getContact();   }
    public String getEmail(){           return user.getEmail();   }
    public void setUsername(String username){    user.setUsername(username);  }
    public void setFirstName(String fName){      user.setFirstName(fName);  }
    public void setLastname(String lName){       user.setLastname(lName);    }
    public void setEmail(String nEm){            user.setEmail(nEm);  }
    public void setContact(String cNum){         user.setContact(cNum);  }
    public String getUser(){ return userSub; }
    public String getRestart(){ return restart; }
    public String getLocation(){ return location; }
    public void setStatus(String stat){ status = stat; }
    public void setWait(String nWait){ wait = nWait; }
    public String getTitle(){ return title; }
    public String getCat(){ return category; }
    public int getCommentNum(){ return comments.size(); }
    public LinkedList<String> getComments(){ return comments; }
    public void addComment(String com){ comments.add(com);}
    public void removeComment(int com){ comments.remove(com);}
    public String getStatus(){ return status; }
    public String getWait(){ return wait; }
    public String getIssue(){ return issue; }


}