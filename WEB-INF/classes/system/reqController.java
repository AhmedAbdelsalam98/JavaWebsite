package system;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//sets url
@WebServlet(urlPatterns = {""})
public class reqController extends HttpServlet {

//handles get requests
  public void doGet(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    doUserCheck(req);

   

      if (req.isUserInRole("user")) {
        
        if(req.getParameter("myRep") != null){
          doUserReqView(req,res);
        }else if(req.getParameter("knowledge") != null){
            doUserKnowledgeView(req, res);
          }else{
          doUserView(req, res);
        }
      }
      else if (req.isUserInRole("admin")) {
        if(req.getParameter("adminreq") != null){
          doReqView(req, res);
        }
        else if(req.getParameter("knowledge") != null){
          doAdminKnowledgeView(req, res);
        }else{
          doAdminView(req, res);
        }
      }
      else {
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/loginError.jsp");
          dispatcher.forward(req, res);
          return;
      }
    
    
  }
  public void doUserCheck(HttpServletRequest req){
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
    String username = req.getUserPrincipal().getName();
    boolean test = serviceIT.userCheck(username);
    if(!test){
      serviceIT.newUser(username);
      //serviceIT.getUser(req.getUserPrincipal().getName()).setFirstName(//Put firstname in here);
      //serviceIT.getUser(req.getUserPrincipal().getName()).setlastName(//Put lastname in here);
      //serviceIT.getUser(req.getUserPrincipal().getName()).setContact(//Put contact num in here);
      //serviceIT.getUser(req.getUserPrincipal().getName()).setEmail(//Put email in here)
    }
  }
  

  //handles post requests
  public void doPost(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
    //looks for new comment
    String newCom = req.getParameter("newComment");

    if(newCom != null){
      String fullCom = "<b>"+req.getUserPrincipal().getName() + "</b>: "  + newCom;
      int pos = Integer.parseInt(req.getParameter("requestNumber"));
      serviceIT.addComment(pos, fullCom);
    }


    //Looks for removal of comment request
    String remCom = req.getParameter("reqComNumber");

    if(remCom != null){
      int reqComNum = Integer.parseInt(remCom);
      int commentNum = Integer.parseInt( req.getParameter("comNumber"));
     
      try{serviceIT.removeComment(reqComNum, commentNum);}catch(Exception e){}
      
    }

    //looks for new comment on knowledge base
    String newKBCom = req.getParameter("newKBComment");

    if(newKBCom != null){
      String fullKBCom = "<b>"+req.getUserPrincipal().getName() + "</b>: "  + newKBCom;
      int posKB = Integer.parseInt(req.getParameter("requestKBNumber"));
      serviceIT.addKBComment(posKB, fullKBCom);
    }


    //Looks for removal of comment from knowledge base
    String remKBCom = req.getParameter("reqKBComNumber");

    if(remKBCom != null){
      int reqKBComNum = Integer.parseInt(remKBCom);
      int commentKBNum = Integer.parseInt( req.getParameter("comKBNumber"));
      try{serviceIT.removeKBComment(reqKBComNum, commentKBNum);}catch(Exception e){}
      
    }

    //gets the category parameter
    String inputCat = req.getParameter("category");

    
    // tests to see if a category is present
    if(inputCat != null){
      
      ServiceRequest newReq = new ServiceRequest(inputCat, req.getParameter("IssueDesc"), req.getParameter("IssueName"), req.getUserPrincipal().getName(),req.getParameter("Restart"),req.getParameter("Location"), serviceIT.getUser(req.getUserPrincipal().getName()));
      try{serviceIT.addRequest(newReq);}catch(Exception e){}
    }
    // Sets the new status of the selected IT request
    if(req.getParameter("statusUpdate") != null)
    {
      if(req.getParameter("statusUpdate").equals("Reporter")){
        serviceIT.updateWait(Integer.parseInt(req.getParameter("reqnum")), req.getParameter("statusUpdate"));

      }else{
        serviceIT.updateStatus(Integer.parseInt(req.getParameter("reqnum")), req.getParameter("statusUpdate"));
      }
      
    }

    if(req.getParameter("knowBaseUpdate") != null)
    {
        serviceIT.addKBReq(serviceIT.getReqIn(Integer.parseInt(req.getParameter("reqKB"))));
        serviceIT.removeReq(Integer.parseInt(req.getParameter("reqKB")));
    }
    if (req.getParameter("maintenanceInfo") != null){
    	serviceIT.setWebStatus(req.getParameter("maintenanceStatus"));
    	serviceIT.setMaintenance(req.getParameter("maintenanceInfo"));
    }
      doGet(req, res);
    
  }

 
  //displays the game jsp
  private void doAdminView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
      
      //Sets counter on admin dashboard
      req.setAttribute("new", serviceIT.getNew());
      req.setAttribute("prog", serviceIT.getProg());
      req.setAttribute("comp", serviceIT.getComp());
      req.setAttribute("res", serviceIT.getRes());
      

    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/admin/admin.jsp");

    //forward the request
    view.forward(req, res);
  }

  private void doReqView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
      ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
      req.setAttribute("requests", serviceIT.getNewReq());
    
    
    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/admin/adminReports.jsp");

    //forward the request
    view.forward(req, res);
  }

  private void doUserReqView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
      ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
      req.setAttribute("requests", serviceIT.getNewReq());
    
    
    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/user/userReports.jsp");

    //forward the request
    view.forward(req, res);
  }


   //displays the game jsp
   private void doLoginView(HttpServletRequest req, HttpServletResponse res) 
   throws IOException, ServletException
 {
   //get the game jsp
   RequestDispatcher view = req.getRequestDispatcher("login.jsp");

   //forward the request
   view.forward(req, res);
 }

  //displays the game jsp
  private void doUserView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
    req.setAttribute("requests", serviceIT.getNewReq());
    req.setAttribute("systemStatus", serviceIT.getWebStatus());
    req.setAttribute("systemMaintenance", serviceIT.getMaintenance());
    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/user/user.jsp");

    //forward the request
    view.forward(req, res);
  }

  //displays the game jsp
  private void doAdminKnowledgeView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
    req.setAttribute("requests", serviceIT.getKBReq());
    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/admin/admin-knowledge.jsp");

    //forward the request
    view.forward(req, res);
  }
  //displays the game jsp
  private void doUserKnowledgeView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    ServiceIT serviceIT = (ServiceIT) getServletContext().getAttribute("serviceIT");
    req.setAttribute("requests", serviceIT.getKBReq());
    //get the game jsp
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/user/user-knowledge.jsp");

    //forward the request
    view.forward(req, res);
  }
}

  