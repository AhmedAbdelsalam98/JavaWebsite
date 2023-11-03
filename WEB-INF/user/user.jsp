<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" import="java.util.*" %>

<%@ page import="system.ServiceRequest" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="/templates/user-top.html" />
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
            </div>
          </form>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>

        <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <% LinkedList<ServiceRequest> tests = (LinkedList<ServiceRequest>)request.getAttribute("requests"); 
          
              int updated = 0;
              
        for(ServiceRequest req : tests)
        {
         
          if(request.getUserPrincipal().getName().equals(req.getUser())){
              if(req.getStatus().equals("Completed") || req.getWait().equals("Reporter") ){
                updated++;
              }
          }

        } %>

            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter"><%= updated %></span>  <!-- this is the counter -->
              </a>

              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alerts Center
                </h6>
                
            <% if(updated != 0){ %>
               <!-- Alert -->
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">Update to your reports</div>
                    <span class="font-weight-bold">One or more of your reports have progressed and are waiting for your confirmation</span>
                  </div>
    <% } else{ %>
                  <!-- default -->
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">No updates</div>
                    <span class="font-weight-bold">You have no current updates requiring attention</span>
                  </div>
                  
                  <% } %>
                </a>
                <!-- end of alert -->

              </div>
            </li>            
          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          </div>
          <hr>
          <!-- Content Row -->

          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Create A New Report</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                <p>
                  Do you have an issue that you need help with?<br>
                  Submit a new report so that one of our IT Team can assist you in finding a solution.
                </p>
                <a class="btn btn-info" href="#" data-toggle="modal" data-target="#logoutModal">Create a new Report</a>                 
                  
                </div>
              </div>

              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">View My Reports</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                <p>
                  Do you have an issue that we are currently working on?<br>
                  Visit the 'My Reports' Page to view the progress on your report, add additional comments to your report, and accept or reject outcome of a completed report.
                </p>
                <a class="btn btn-info" href="?myRep=true">View My Reports</a>                 
                  
                </div>
              </div>
            </div>

            <!-- Maintanence -->
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Planned Maintanece</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <% out.print("<b> Current System Status: </b><br>" +request.getAttribute("systemStatus")); %>
                        <% out.print("<br><br><b> Next Scheduled Maintenance:</b><br> " +request.getAttribute("systemMaintenance")); %>
                  
                </div>
              </div>
            </div>
          </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <jsp:include page="/templates/user-bottom.html" />
</html>
