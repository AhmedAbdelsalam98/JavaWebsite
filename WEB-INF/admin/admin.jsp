<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="/templates/admin-top.html" />



        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          </div>
            <hr>
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h4 mb-0 text-gray-800">Admin Tasks</h1>
          </div>

          <!-- Content Row -->
          <div class="row">

            
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">New Reports</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("new") %></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-plus-circle fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">In Progress Reports</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("prog") %></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-spinner fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

           
           <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
              <div class="card-body">
                <div class="row no-gutters align-items-center">
                  <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Completed Reports</div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("comp") %></div>
                  </div>
                  <div class="col-auto">
                    <i class="fas fa-check fa-2x text-gray-300"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>

            
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Resolved Reports</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("res") %></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-check-double fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <hr>
          <!-- Content Row -->

          
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <jsp:include page="/templates/admin-bottom.html" />

</html>
