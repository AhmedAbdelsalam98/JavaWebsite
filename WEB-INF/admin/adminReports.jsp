<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" import="java.util.*" %>
<%@ page import="system.ServiceRequest" %>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="/templates/admin-top.html" />

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Reports</h1>
          </div>
        <hr>
             
            
              <% LinkedList<ServiceRequest> requests = (LinkedList<ServiceRequest>)request.getAttribute("requests"); 
          
                 int i = 0;
                 int test = 0;
           for(ServiceRequest req : requests)
           {
            
             if(req.getStatus().equalsIgnoreCase(request.getParameter("adminreq"))){ test++;
             %>
            
             <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Title: <%=  req.getTitle() %></h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
      
            <b>
                <% out.print("Category: " +req.getCat()); %>
            </b>
           <p>
              <% out.print("<b>Submitted By: </b>" +req.getUser() + " | " + req.getFirstName() + " " + req.getLastname()); %><br>
              <% out.print("<b>User Contact: </b>" +req.getContact() + " | " + req.getEmail()); %><br>
              <% out.print("<b>Waiting on: </b>" +req.getWait()); %><br>
              <% out.print("<b>Was a restart tried: </b>" +req.getRestart()); %><br>
              <% out.print("<b>Issue Location: </b>" +req.getLocation()); %><br> 
              <% out.print("<b>Issue: </b>" +req.getIssue()); %>
          </p> 
          <% out.print("Status: <b>" +req.getStatus() + "</b><form method='post'> <em>Set Status</em><br><select name='statusUpdate'><option value='New'>New</option><option value='Progress'>Progress</option><option value='Completed'>Completed</option><option value='Reporter'>Waiting on reporter</option></select>&nbsp;&nbsp;<input type='text' name='reqnum' style='display: none;' value='" + i + "'></input> <input type='submit' value='Update' class='btn btn-success'></form><br><button class='btn btn-info' data-toggle='modal' data-target='#comms" + i +"'>View Comments</button>");
         
          if(req.getStatus().equalsIgnoreCase("Resolved")){
          
            out.print("</br></br><form method='post'><button type='submit' class='btn btn-warning' name=knowBaseUpdate>Add to Knowledge Base</button><input type='text' name='reqKB' style='display: none;' value='" + i + "'></input></form>");} 
            out.print("</div></div>");
          } %>
          <% i++;} %>
          <% if(test == 0){
             out.print("<h4>There are no requests in this category</h4>");
          }
          %>
          

          
      </div>
      <!-- End of Main Content -->
<!--Comment Modals -->
    <!-- Modal -->
    <% 
          
      i = 0;
      for(ServiceRequest req : requests)
      {
        
        
          LinkedList<String> comments = req.getComments();

        String modal = "<div class='modal fade' id='comms" + i +"' tabindex='-1' role='dialog' aria-hidden='true'>";
              modal += "<div class='modal-dialog modal-dialog-scrollable' role='document'>";
                  modal += "<div class='modal-content'>";
                      modal += " <div class='modal-header'>";
                          modal += "<h5 class='modal-title' id='exampleModalScrollableTitle'>Comments on Issue</h5>";
                          modal += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>";
                              modal += "<span aria-hidden='true'>&times;</span>";
                              modal += "</button>";
                              modal += "</div>";
                              modal += "<div class='modal-body'>";
                                int commNum = 0;
                                  if(req.getCommentNum() == 0){
                                    modal +="There are no comments on this issue";
                                  }else{
                                    for(String comm : comments)
                                    {
                                      
                                    modal += comm + "<form method='post'><input type='text' name='comNumber' style='display: none;' value='" + commNum + "'></input><input type='text' name='reqComNumber' style='display: none;' value='" + i + "'></input><button type='submit' class='rem-btn' name='PostMes'>Remove Comment</button></form><hr>";
                                    commNum++;
                                    }
                                  }
                                  modal += "</div>";
                                  modal += "<div class='modal-footer'>";
                                    modal +="<div class='mr-auto'><form method='post'><input type='text' name='newComment' id='commbox' placeholder='Enter New Comment Here'></input>&nbsp;<button type='submit' class='btn btn-success' name='Post'>Post</button><input type='text' name='requestNumber' style='display: none;' value='" + i + "'></input></form></div>";
                                      modal += "</div>";
                                      modal += "</div>";
                                      modal += "</div>";
                                      modal += "</div>";
       out.print(modal);
                                    
       i++;
      }

  %>

      <jsp:include page="/templates/admin-bottom.html" />

</html>
