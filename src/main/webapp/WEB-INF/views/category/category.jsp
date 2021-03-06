<%@page import="java.util.Locale"%>
<%@ include file="/WEB-INF/views/includes/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gestion de stock mvc</title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/resources/dist/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- DataTables CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" 
    integrity="sha384-7ynz3n3tAGNUYFZD3cWe5PDcE36xj85vyFkawcF6tIwxvIecqKvfwLiaFdizhPpN" crossorigin="anonymous">
    
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,600;1,600&display=swap" rel="stylesheet">
	
	<style type="text/css">
		<%@ include file="/resources/vendor/bootstrap/css/bootstrap.min.css" %>
		<%@ include file="/resources/vendor/metisMenu/metisMenu.min.css" %>
		<%@ include file="/resources/dist/css/sb-admin-2.css" %>
		<%@ include file="/resources/vendor/font-awesome/css/font-awesome.min.css" %>
		<%@ include file="/resources/vendor/datatables-responsive/dataTables.responsive.css" %>
		<%@ include file="/resources/vendor/datatables-plugins/dataTables.bootstrap.css" %>
		<%@ include file="/resources/dist/css/style.css" %>
 	</style>


</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            
            <%@ include file="/WEB-INF/views/menu_top/topMenu.jsp" %>
			
            <%@ include file="/WEB-INF/views/menu_left/leftMenu.jsp" %>
            <!-- /.navbar-static-side -->
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"><fmt:message code="common.parametrage.category" /></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                 <div id="errorBlock" class="alert alert-danger alert-dismissable" style="display:none;">
                     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">?</button>
                     <span id="errorMsg"></span>
                 </div>
                <div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
						  <li><a href="<c:url value="/category/nouveau" />" ><i class="fa fa-plus">&nbsp;<fmt:message code="common.ajouter" /></i></a></li>
						  <li><a href="#"><i class="fa fa-download">&nbsp;<fmt:message code="common.exporter" /></i></a></li>
						  <li><a href="#"><i class="fa fa-upload">&nbsp;<fmt:message code="common.importer" /></i></a></li>
						</ol>					
					</div>                
                </div>
                
                <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <fmt:message code="category.liste" />
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th><fmt:message code="common.code" /></th>
                                        <th><fmt:message code="common.designation" /></th>
                                        <th><fmt:message code="common.actions" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${categories }" var = "cat">
	                                    <tr class="odd gradeX">
	                                        <td>${cat.getCode() }</td>
	                                        <td>${cat.getDesignation() }</td>
	                                        <td>
	                                        	<c:url value="/category/modifier/${cat.getId() }" var="urlModif" />
	                                        	<a href="${urlModif }"><i class="fa fa-edit"></i></a>
	                                        	&nbsp;|&nbsp;
	                                        	<a href="javascript:void(0);" data-toggle="modal" data-target="#modalCategory${cat.getId() }"><i class="fa fa-trash-o"></i></a>
	                                        	<div class="modal fade" id="modalCategory${cat.getId() }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
																<h4 class="modal-title" id="myModalLabel"><fmt:message code="common.confirm.suppression" /></h4>
															</div>
															<div class="modal-body">
																<fmt:message code="category.confirm.suppression.msg" />
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message code="common.annuler" /></button>
																<c:url value="/category/supprimer/${cat.getId() }" var="urlSuppression" />
																<button type="button" data-dismiss="modal" class="btn btn-danger supprimerArticle" onclick="supprimerArticle(${urlSuppression });"><i class="fa fa-trash-o"></i>&nbsp;<fmt:message code="common.confirmer" /></button>
															</div>
														</div>
														<!-- /.modal-content -->
													</div>
													<!-- /.modal-dialog -->
												</div>
	                                        </td>
	                                    </tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/dist/js/sb-admin-2.js"></script>

    <script src="<%=request.getContextPath() %>/resources/javascript/category.js"></script>
    
</body>

</html>
